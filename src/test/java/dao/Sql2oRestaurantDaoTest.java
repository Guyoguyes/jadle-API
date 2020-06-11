package dao;

import models.FoodType;
import models.Restaurant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oRestaurantDaoTest {
    private Connection con;
    private Sql2oRestaurantDao restaurantDao;
    private Sql2oFoodTypeDao foodTypeDao;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        restaurantDao = new Sql2oRestaurantDao(sql2o);
        foodTypeDao = new Sql2oFoodTypeDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void add() {
        Restaurant restaurant = setUpAssistant();
        assertEquals(1, restaurantDao.getAll().size());
    }

    @Test
    public void getAll() {
        Restaurant restaurant = setUpAssistant();
        Restaurant restaurant1 = setUpAssistant();
        assertEquals(2, restaurantDao.getAll().size());
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteById() {
        Restaurant restaurant = setUpAssistant();
        Restaurant restaurant1 =setUpAssistant();
        restaurantDao.deleteById(restaurant.getId());
        assertEquals(1, restaurantDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        Restaurant restaurant = setUpAssistant();
        Restaurant restaurant1 = setUpAssistant();
        restaurantDao.clearAll();
        assertEquals(0, restaurantDao.getAll().size());
    }

    @Test
    public void RestaurantReturnsFoodTypesCorrectly() throws Exception{
        FoodType foodType = new FoodType("Seafood");
        foodTypeDao.add(foodType);

        FoodType otherFoodType = new FoodType("Bar Food");
        foodTypeDao.add(otherFoodType);

        Restaurant restaurant = setUpAssistant();
        restaurantDao.add(restaurant);

        restaurantDao.addRestaurantToFoodType(restaurant, foodType);
        restaurantDao.addRestaurantToFoodType(restaurant, otherFoodType);

        FoodType[] foodTypes = {foodType, otherFoodType};
        assertEquals(Arrays.asList(foodTypes), restaurantDao.getAllFoodtypesByRestaurant(restaurant.getId()));
    }


    //helper method
    public Restaurant setUpAssistant(){
        Restaurant restaurant = new Restaurant("alma", "234 General-Kago", "010000", "+254789345121", "www.alma.com", "alma234@gmail.com");
        restaurantDao.add(restaurant);
        return restaurant;
    }
}