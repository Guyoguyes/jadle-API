package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RestaurantTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getsNameReturnsCorrectly() throws Exception{
        Restaurant restaurant = setUpAssistant();
        assertEquals("Alma", restaurant.getName());
    }


    @Test
    public void getsAddressReturnsCorrectly() throws Exception{
        Restaurant restaurant = setUpAssistant();
        assertEquals("234 General-Kago", restaurant.getAddress());
    }

    @Test
    public void getsZipCodeReturnsCorrectly() throws Exception{
        Restaurant restaurant = setUpAssistant();
        assertEquals("010000", restaurant.getZipcode());
    }

    @Test
    public void getsPhoneReturnsCorrectly() throws Exception{
        Restaurant restaurant = setUpAssistant();
        assertEquals("+25478153823", restaurant.getPhone());
    }

    @Test
    public void getsWebsiteReturnsCorrectly() throws Exception{
        Restaurant restaurant = setUpAssistant();
        assertEquals("www.alma.com", restaurant.getWebsite());
    }

    @Test
    public void getsUnAvailableWebsiteCorrectly() throws Exception{
        Restaurant restaurant = setUpAltAssistant();
        assertEquals("no website available", restaurant.getWebsite());
    }

    @Test
    public void getsEmailReturnsCorrectcly() throws Exception{
        Restaurant restaurant = setUpAssistant();
        assertEquals("alma234@gmail.com", restaurant.getEmail());
    }

    @Test
    public void  getsUnProvidedEmailReturnsCorrectly() throws Exception{
        Restaurant restaurant = setUpAltAssistant();
        assertEquals("no email available", restaurant.getEmail());
    }

    @Test
    public void setNameSetsCorrectly() throws Exception{
        Restaurant restaurant = setUpAssistant();
        restaurant.setName("Cabana");
        assertNotEquals("alma", restaurant.getName());
    }

    @Test
    public void setAddressSetsCorrectly() throws Exception{
        Restaurant restaurant = setUpAssistant();
        restaurant.setAddress("234 Bidco");
        assertNotEquals("234 General-Kago", restaurant.getAddress());
    }

    @Test
    public void setZipCodeSetsCorrectly() throws  Exception{
        Restaurant restaurant = setUpAssistant();
        restaurant.setZipcode("60300");
        assertNotEquals("010000", restaurant.getZipcode());
    }

    @Test
    public void setPhoneSetsCorrectly() throws Exception{
        Restaurant restaurant = setUpAssistant();
        restaurant.setPhone("+254781563452");
        assertNotEquals("+25478153823", restaurant.getPhone());
    }

    @Test
    public void setWebsiteSetsCorrectly() throws Exception{
        Restaurant restaurant = setUpAssistant();
        restaurant.setWebsite("www.alma.ac.ke");
        assertNotEquals("www.alma.com", restaurant.getWebsite());
    }

    @Test
    public void setEmailSetsCorrectly() throws Exception{
        Restaurant restaurant = setUpAssistant();
        restaurant.setEmail("alma43@gmail.com");
        assertNotEquals("alma234@gmail.com", restaurant.getEmail());
    }

    public Restaurant setUpAssistant(){
        return new Restaurant("Alma", "234 General-Kago", "010000", "+25478153823", "www.alma.com", "alma234@gmail.com");
    }



    public Restaurant setUpAltAssistant(){
        return new Restaurant("Alma", "234 General-Kago", "010000", "+25478153823");
    }
}