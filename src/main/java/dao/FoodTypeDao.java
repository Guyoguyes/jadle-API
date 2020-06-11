package dao;

import models.FoodType;
import models.Restaurant;

import java.util.List;

public interface FoodTypeDao {

    //add foodType
    void add(FoodType foodType);
    void addFoodtypeToRestaurant(FoodType foodType, Restaurant restaurant);

    //get all
    List<FoodType> getAll();
    List<Restaurant> getAllRestaurantsForAFoodtype(int id);

    // find by id
    FoodType findById(int id);

    //update
    void update(int id, String name);

    //delete
    void deleteById(int id);
    void clearAll();

}
