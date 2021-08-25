package dao;

import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

public class Sql2oUserDao {
    public Sql2oUserDao(){}


    @Override
    public void add(User user) {
        String sql = "INSERT INTO users (name,position,role,department) VALUES (:name,:position,:role,:department);";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql,true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        } catch (Sql2oException ex){
            System.out.println("User not added: "+ex);
        }
    }


    @Override
    public User findById(int id) {
        String sql = "SELECT * from users WHERE id=:id;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }
}
