package dao;

import models.FoodType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oFoodTypeDaoTest {
    private Connection con;
    private Sql2oFoodTypeDao foodTypeDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        foodTypeDao = new Sql2oFoodTypeDao(sql2o);
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

    //helper method
    public FoodType setUpAssistant(){
        FoodType foodType = new FoodType("Biriani");
        foodTypeDao.add(foodType);
        return foodType;
    }
}