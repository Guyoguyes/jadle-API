package dao;

import models.FoodType;
import models.Restaurant;

import java.util.List;

public interface RestaurantDao {

    //Add restaurant
    void add(Restaurant restaurant);
    void addRestaurantToFoodType(Restaurant restaurant, FoodType foodType);

    //Get all
    List<Restaurant> getAll();
    List<FoodType> getAllFoodtypesByRestaurant(int restaurantId);

    // find by id
    Restaurant findById(int id);

    //update
    void update(int id, String name, String address, String zipcode, String phone, String website, String email);

    //delete
    void deleteById(int id);
    void clearAll();

}
