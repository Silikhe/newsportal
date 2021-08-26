package dao;

import org.junit.rules.ExternalResource;
import org.sql2o.*;


public class DatabaseRule extends ExternalResource {

    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/news_portal_test","moringa","star2030");
    }

    protected void after() {
        try(Connection con = DB.sql2o.open()) {
            String deleteUsers = "DELETE FROM users *;";
            String deleteDepartments = "DELETE FROM departments *;";
            String deleteNews = "DELETE FROM news *;";
            String deleteDepartmentNews = "DELETE FROM departments_news *;";
            String deleteDepartmentEmployees = "DELETE FROM departments_users *;";
            con.createQuery(deleteUsers).executeUpdate();
            con.createQuery(deleteDepartments).executeUpdate();
            con.createQuery(deleteNews).executeUpdate();
            con.createQuery(deleteDepartmentNews).executeUpdate();
            con.createQuery(deleteDepartmentEmployees).executeUpdate();
        }
    }

}