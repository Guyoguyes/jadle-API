package dao;

import models.FoodType;

import java.util.List;

public interface FoodTypeDao {

    //add foodType
    void add(FoodType foodType);

    //get all
    List<FoodType> getAll();

    // find by id
    FoodType findById(int id);

    //update
    void update(int id, String name);

    //delete
    void deleteById(int id);
    void clearAll();

}
