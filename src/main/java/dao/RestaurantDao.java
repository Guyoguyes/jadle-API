package dao;

import models.Restaurant;

import java.util.List;

public interface RestaurantDao {

    //Add restaurant
    void add(Restaurant restaurant);

    //Get all
    List<Restaurant> getAll();

    // find by id
    Restaurant findById(int id);

    //update
    void update(int id, String name, String address, String zipcode, String phone, String website, String email);

    //delete
    void deleteById(int id);
    void clearAll();

}
