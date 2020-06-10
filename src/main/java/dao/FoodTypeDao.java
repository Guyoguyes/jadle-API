package dao;

import models.FoodType;

import java.util.List;

public interface FoodTypeDao {

    //add foodType
    void add(FoodType foodType);

    //get all
    List<FoodType> getAll();
}
