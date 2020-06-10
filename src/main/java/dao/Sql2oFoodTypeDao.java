package dao;

import models.FoodType;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oFoodTypeDao implements FoodTypeDao {
    private Sql2o sql2o;

    public Sql2oFoodTypeDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(FoodType foodType) {
        String sql = "INSERT INTO foodtypes (name) VALUES (:name)";
        try(Connection connection = sql2o.open()) {
            int id = (int) connection.createQuery(sql, true)
                    .bind(foodType)
                    .executeUpdate()
                    .getKey();
            foodType.setId(id);
        }catch (Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public List<FoodType> getAll() {
        try(Connection connection = sql2o.open()) {
            return connection.createQuery("SELECT * FROM foodtypes")
                    .executeAndFetch(FoodType.class);
        }
    }

    @Override
    public FoodType findById(int id) {
        try(Connection connection = sql2o.open()){
            return connection.createQuery("SELECT * FROM foodtypes WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(FoodType.class);
        }
    }

    @Override
    public void update(int id, String name) {
        String sql = "UPDATE foodtypes SET name=:name WHERE id=:id";
        try(Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM foodtypes WHERE id=:id";
        try(Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch(Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM foodtypes";
        try(Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .executeUpdate();
        }catch (Sql2oException e){
            System.out.println(e);
        }
    }
}
