package dao;

import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oReviewDao implements ReviewDao{
    private Sql2o sql2o;

    public Sql2oReviewDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Review review) {
        String sql = "INSERT INTO reviews (writtenby, content, rating, restaurantId) VALUES (:writtenBy, :content, :rating, :restaurantId)";
        try(Connection connection = sql2o.open()) {
            int id = (int) connection.createQuery(sql, true)
                    .bind(review)
                    .executeUpdate()
                    .getKey();
            review.setId(id);
        }catch (Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public List<Review> getAll() {
       try(Connection connection = sql2o.open()) {
           return connection.createQuery("SELECT * FROM reviews")
                   .executeAndFetch(Review.class);
       }
    }

    @Override
    public List<Review> getAllReviewByRestaurant(int restaurantId) {
        try(Connection connection = sql2o.open()) {
            return connection.createQuery("SELECT * FROM reviews WHERE restaurantId=:restaurantId")
                    .addParameter("restaurantId", restaurantId)
                    .executeAndFetch(Review.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM reviews WHERE id=:id";
        try(Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM reviews";
        try(Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .executeUpdate();
        }catch (Sql2oException e){
            System.out.println(e);
        }
    }
}
