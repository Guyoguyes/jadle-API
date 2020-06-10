package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReviewTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getContent() throws Exception {
        Review review = setUpAssist();
        assertEquals("Good Service", review.getContent());
    }

    @Test
    public void setContent() throws Exception {
        Review review = setUpAssist();
        review.setContent("good");
        assertNotEquals("Good Service", review.getContent());
    }

    @Test
    public void getWrittenBy() {
        Review review = setUpAssist();
        assertEquals("John", review.getWrittenBy());
    }

    @Test
    public void setWrittenBy() {
        Review review = setUpAssist();
        review.setWrittenBy("Doe");
        assertNotEquals("John", review.getWrittenBy());
    }

    @Test
    public void getRating() {
        Review review = setUpAssist();
        assertEquals(5, review.getRating());
    }

    @Test
    public void setRating() {
        Review review = setUpAssist();
        review.setRating(4);
        assertNotEquals(5, review.getRating());
    }



    @Test
    public void setId() {
        Review review = setUpAssist();
        review.setId(1);
        assertEquals(1, review.getId());
    }

    @Test
    public void getRestaurantId() {
        Review review = setUpAssist();
        assertEquals(1, review.getRestaurantId());
    }

    @Test
    public void setRestaurantId() {
        Review review = setUpAssist();
        review.setRestaurantId(2);
        assertNotEquals(1, review.getRestaurantId());
    }

    public Review setUpAssist(){
        return new Review("Good Service", "John", 5, 1);
    }
}