package dao;

import models.Review;

import java.util.List;

public interface ReviewDao {

    //Add
    void add(Review review);

    //get All
    List<Review> getAll();

    //getallReviewsByRestaurant
    List<Review> getAllReviewByRestaurant(int restaurantId);

    //delete
    void deleteById(int id);
    void clearAll();
}
