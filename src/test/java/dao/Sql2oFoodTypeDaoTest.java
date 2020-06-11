package dao;

import models.FoodType;
import models.Restaurant;
import models.Review;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oFoodTypeDaoTest {
    private Connection con;
    private Sql2oFoodTypeDao foodTypeDao;
    private Sql2oRestaurantDao restaurantDao;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        foodTypeDao = new Sql2oFoodTypeDao(sql2o);
        restaurantDao = new Sql2oRestaurantDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void add() {
        FoodType foodType = setUpAssistant();
        assertEquals(1, foodTypeDao.getAll().size());
    }

    @Test
    public void getAll() {
        FoodType foodType = setUpAssistant();
        FoodType foodType1 = setUpAssistant();
        assertEquals(2, foodTypeDao.getAll().size());
    }

//    @Test
//    public void update() {
//        FoodType foodType = setUpAssistant();
//        foodTypeDao.update(foodType.getId(), "Pilau");
//        FoodType foodType1 = foodTypeDao.findById(foodType.getId());
//        assertEquals("Pilau", foodType1.getName());
//    }

    @Test
    public void deleteById() {
        FoodType foodType = setUpAssistant();
        FoodType foodType1 = setUpAssistant();
        foodTypeDao.deleteById(foodType.getId());
        assertEquals(1, foodTypeDao.getAll().size());
    }

    @Test
    public void clearAll() {
        FoodType foodType = setUpAssistant();
        FoodType foodType1 = setUpAssistant();
        foodTypeDao.clearAll();
        assertEquals(0, foodTypeDao.getAll().size());
    }

    @Test
    public void addFoodTypeToRestaurantAddsTypeCorrectly() throws Exception {

        Restaurant testRestaurant = setupRestaurant();
        Restaurant altRestaurant = setupAltRestaurant();

        restaurantDao.add(testRestaurant);
        restaurantDao.add(altRestaurant);

        FoodType testFoodtype = setUpAssistant();

        foodTypeDao.add(testFoodtype);

        foodTypeDao.addFoodtypeToRestaurant(testFoodtype, testRestaurant);
        foodTypeDao.addFoodtypeToRestaurant(testFoodtype, altRestaurant);

        assertEquals(2, foodTypeDao.getAllRestaurantsForAFoodtype(testFoodtype.getId()).size());
    }

    //helper method
    public FoodType setUpAssistant(){
        return new FoodType("Biriani");
    }

    public Restaurant setupRestaurant (){
        Restaurant restaurant = new Restaurant("Fish Omena", "214 NE Safaricom", "97232", "254-402-9874", "http://fishwitch.com", "hellofishy@fishwitch.com");
        restaurantDao.add(restaurant);
        return restaurant;
    }

    public Restaurant setupAltRestaurant (){
        Restaurant restaurant = new Restaurant("Fish Omena", "214 NE Safaricom", "97232", "254-402-9874");
        restaurantDao.add(restaurant);
        return restaurant;
    }
}