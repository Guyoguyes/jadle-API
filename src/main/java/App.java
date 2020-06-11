import com.google.gson.Gson;
import dao.Sql2oFoodTypeDao;
import dao.Sql2oRestaurantDao;
import dao.Sql2oReviewDao;
import models.FoodType;
import models.Restaurant;
import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
    Sql2oFoodTypeDao foodTypeDao;
    Sql2oRestaurantDao restaurantDao;
        Sql2oReviewDao reviewDao;
        Connection conn;
        Gson gson = new Gson();

        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/jadle.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        restaurantDao = new Sql2oRestaurantDao(sql2o);
        foodTypeDao = new Sql2oFoodTypeDao(sql2o);
        reviewDao = new Sql2oReviewDao(sql2o);
        conn = sql2o.open();


        post("/restaurants/new", "application/json", (req, res) ->{
            Restaurant restaurant  = gson.fromJson(req.body(), Restaurant.class);
            restaurantDao.add(restaurant);
            res.status(201);
            return  gson.toJson(restaurant);
        });

        get("/restaurants", "application/json", (req, res) ->{
            return  gson.toJson(restaurantDao.getAll());
        });

        get("/restaurants", "application/json", (req, res) ->{
            int restaurantId = Integer.parseInt(req.params("id"));
            return  gson.toJson(restaurantDao.findById(restaurantId));
        });

        post("/restaurants/:restaurantId/reviews/new", "application/json", (req, res) ->{
            int restaurantId = Integer.parseInt(req.queryParams("restaurantId"));
            Review review = gson.fromJson(req.body(), Review.class);
            review.setRestaurantId(restaurantId);
            reviewDao.add(review);
            res.status(201);
            return gson.toJson(review);
        });

        get("/reviews", "application/json", (req, res) ->{
            return gson.toJson(reviewDao.getAll());
        });

        post("/restaurants/:restaurantId/foodtype/:foodtypeId", "application/json", (req, res) ->{
            int restaurantId = Integer.parseInt(req.params("restaurantId"));
            int foodtypeId = Integer.parseInt(req.params("foodtypeId"));
            Restaurant restaurant = restaurantDao.findById(restaurantId);
            FoodType foodType = foodTypeDao.findById(foodtypeId);

            if(restaurant != null && foodType != null){
                foodTypeDao.addFoodtypeToRestaurant(foodType, restaurant);
                res.status(201);
                return gson.toJson(String.format("Restaurant '%s' and Foodtype '%s%' have been associated", restaurant.getName(), foodType.getName()));
            }else {
                throw new ApiException(404, String.format("Restaurant or foodtype does not exist"));
            }
        });

        get("/restaurants/:id/foodtypes", "application/json", (req, res) ->{
            int restaurantId = Integer.parseInt(req.params("id"));
            Restaurant restaurantToFindId = restaurantDao.findById(restaurantId);
            if(restaurantToFindId == null){
                throw new ApiException(404, String.format("No restaurant with the id: \"%s\" exists", req.params("id")));
            }else  if (restaurantDao.Restaurant(restaurantId).size() == 0){
                return "{\"message\":\"I'm sorry, but no foodtypes are listed for this restaurant.\"}";
            }else{
                return  gson.toJson(restaurantDao.getAllFoodtypesByRestaurant(restaurantId));
            }
        });

        get("/foodtypes/:id/restaurants", "application/json", (req, res) ->{
            int foodtypeId = Integer.parseInt(req.params("id"));
            FoodType foodTypeToFind = foodTypeDao.findById((foodtypeId));
            if(foodTypeToFind == null){
                throw new ApiException(404, String.format("No foodtype with the is"));
            }else if(foodTypeDao.getAllRestaurantsForAFoodtype(foodtypeId).size() == 0){
                return "{\"message\":\"I'm sorry, but no restaurants are listed for this foodtype.\"}";
            }else{
                return  gson.toJson(foodTypeDao.getAllRestaurantsForAFoodtype(foodtypeId));
            }
        });

        //Filters
        exception(ApiException.class, (exception, req, res) -> {
            ApiException err = exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });


        after((req, res) ->{
            res.type("application/json");
        });
    }


}
