package dao;

import models.FoodType;
import models.Restaurant;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oRestaurantDao implements RestaurantDao{
    private Sql2o sql2o;

    public Sql2oRestaurantDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Restaurant restaurant) {
        String sql = "INSERT INTO restaurants (name, address, zipcode, phone, website, email) VALUES (:name, :address, :zipcode, :phone, :website, :email)";
        try(Connection connection = sql2o.open()){
            int id = (int) connection.createQuery(sql, true)
                    .bind(restaurant)
                    .executeUpdate()
                    .getKey();
            restaurant.setId(id);
        }catch (Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public List<Restaurant> getAll() {
       try (Connection connection = sql2o.open()){
           return connection.createQuery("SELECT * FROM restaurants")
                   .executeAndFetch(Restaurant.class);
       }
    }

    @Override
    public void update(int id, String name, String address, String zipcode, String phone, String website, String email) {
        String sql = "UPDATE restaurants SET name=:name, address=:address, zipcode=:zipcode, phone=:phone, website=:website, email=:email WHERE id=:id";
        try(Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .addParameter("address", address)
                    .addParameter("zipcode", zipcode)
                    .addParameter("phone", phone)
                    .addParameter("website", website)
                    .addParameter("email", email)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public Restaurant findById(int id) {
        try(Connection connection = sql2o.open()){
            return connection.createQuery("SELECT  * FROM  restaurants WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Restaurant.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM restaurants WHERE id=:id";
        try(Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM restaurants";
        try(Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .executeUpdate();
        }catch (Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public void addRestaurantToFoodType(Restaurant restaurant, FoodType foodType) {
        String sql = "INSERT INTO restaurants_foodtypes (restaurantid, foodtypeid) VALUES (:restaurantId, :foodtypeId)";
        try(Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .addParameter("restaurantId", restaurant.getId())
                    .addParameter("foodtypeId", foodType.getId())
                    .executeUpdate();
        }catch (Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public List<FoodType> getAllFoodtypesByRestaurant(int restaurantId) {
       List<FoodType> foodTypes = new ArrayList<>();

       String joinQuery = "SELECT foodtypeid FROM restaurants_foodtypes WHERE restaurantid=:restaurantsId";
       try(Connection connection = sql2o.open()){
           List<Integer> allFoodtypesIds = connection.createQuery(joinQuery)
                   .addParameter("restaurantid", restaurantId)
                   .executeAndFetch(Integer.class);
           for(Integer foodId : allFoodtypesIds){
               String foodtypeQuery = "SELECT * FROM foodtypes WHERE id=:foodtypeId";
               foodTypes.add(
                       connection.createQuery(foodtypeQuery)
                       .addParameter("foodtypeId", foodId)
                       .executeAndFetchFirst(FoodType.class)
               );
           }
       }
    return foodTypes;
    }
}
