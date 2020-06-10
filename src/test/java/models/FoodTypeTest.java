package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class FoodTypeTest {

    @Test
    public void getName() {
        FoodType foodType = setUpAssistant();
        assertEquals("Biriani", foodType.getName());
    }

    @Test
    public void setName() {
        FoodType foodType = setUpAssistant();
        foodType.setName("Mshkaki");
        assertNotEquals("Biriani", foodType.getName());
    }



    @Test
    public void setId() {
        FoodType foodType = setUpAssistant();
        foodType.setId(1);
        assertEquals(1, foodType.getId());
    }

    public FoodType setUpAssistant(){
        return new FoodType("Biriani");
    }
}