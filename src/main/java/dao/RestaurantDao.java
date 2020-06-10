package dao;

import models.Restaurant;

import java.util.List;

public interface RestaurantDao {

    //Add restaurant
    void add(Restaurant restaurant);

    //Get all
    List<Restaurant> getAll();
}
