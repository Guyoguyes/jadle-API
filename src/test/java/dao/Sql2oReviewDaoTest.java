package dao;

import models.Restaurant;
import models.Review;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oReviewDaoTest {
    private Connection con;
    private Sql2oReviewDao reviewDao;
    private Sql2oRestaurantDao restaurantDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        reviewDao = new Sql2oReviewDao(sql2o);
        restaurantDao = new Sql2oRestaurantDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void addingReviewSetsId() {
        Review review = setUpAssistant();
        assertEquals(1, review.getId());
    }

    @Test
    public void getAll() {
        Review review = setUpAssistant();
        Review review1 = setUpAssistant();
        assertEquals(2, reviewDao.getAll().size());
    }

    @Test
    public void getAllReviewByRestaurant() throws Exception {
        Restaurant restaurant = setUpRestaurant();
        Restaurant restaurant1 = setUpRestaurant();
        Review review1 = setUpReviewForRestaurant(restaurant);
        Review review2 = setUpReviewForRestaurant(restaurant);
        assertEquals(2, reviewDao.getAllReviewByRestaurant(restaurant.getId()).size());
    }

    @Test
    public void deleteById() {
        Review review1 = setUpAssistant();
        Review review2 = setUpAssistant();
        assertEquals(2, reviewDao.getAll().size());
        reviewDao.deleteById(review1.getId());
        assertEquals(1, reviewDao.getAll().size());
    }

    @Test
    public void clearAll() {
        Review review = setUpAssistant();
        Review review1 = setUpAssistant();
        reviewDao.clearAll();
        assertEquals(0, reviewDao.getAll().size());
    }

    //helper methods
    public Review setUpAssistant(){
        Review review = new Review("great", "kim", 4, 555);
        reviewDao.add(review);
        return review;
    }

    public Restaurant setUpRestaurant(){
        Restaurant restaurant = new Restaurant("alma", "234 General-Kago", "010000", "+254789345121", "www.alma.com", "alma234@gmail.com");
        restaurantDao.add(restaurant);
        return restaurant;
    }

    public Review setUpReviewForRestaurant(Restaurant restaurant){
        Review review = new Review("great", "john", 4, restaurant.getId());
        reviewDao.add(review);
        return review;
    }
}