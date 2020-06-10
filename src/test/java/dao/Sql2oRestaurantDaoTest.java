package dao;

import models.Restaurant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oRestaurantDaoTest {
    private Connection con;
    private RestaurantDao restaurantDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        restaurantDao = new Sql2oRestaurantDao(sql2o);
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

    //helper method
    public Restaurant setUpAssistant(){
        Restaurant restaurant = new Restaurant("alma", "234 General-Kago", "010000", "+254789345121", "www.alma.com", "alma234@gmail.com");
        restaurantDao.add(restaurant);
        return restaurant;
    }
}