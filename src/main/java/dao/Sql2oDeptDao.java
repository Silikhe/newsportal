package dao;

import models.Department;
import models.News;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oDeptDao implements DeptDao {
    public Sql2oDeptDao(){}

    @Override
    public void add(Department department) {
        String sql = "INSERT INTO departments (name,description,totalemployees) VALUES (:name,:description,:totalemployees)";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql,true)
                    .bind(department)
                    .addParameter("totalemployees",department.getTotalEmployees())
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void addUserToDept(Department department,User user) {
        String sql = "INSERT INTO departments_users(deptid,userid) values (:deptid,:userid);";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("deptid",department.getId())
                    .addParameter("userid",user.getId())
                    .executeUpdate();
            user.setDepartment(department.getName());
            department.incrementTotalEmployees();
            updateEmployeeCount(department);
        } catch (Sql2oException ex){
            System.out.println("Couldn't insert user into department: "+ex);
        }
    }


    @Override
    public Department findById(int id) {
        String sql = "SELECT * from departments WHERE id=:id;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Department.class);
        }
    }

    @Override
    public List<Department> allDepartments() {
        String sql = "SELECT * from departments;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Department.class);
        }
    }

    @Override
    public List<User> allDepartmentEmployees(int deptId) {
        List<User> employees = new ArrayList<>();

        String joinQuery = "SELECT userid FROM departments_users WHERE deptid = :deptid";
        try (Connection con = DB.sql2o.open()) {
            List<Integer> userIds = con.createQuery(joinQuery)
                    .addParameter("deptid",deptId)
                    .executeAndFetch(Integer.class);

            for(int uId:userIds){
                String sql = "SELECT * FROM users WHERE id = :id";
                employees.add(con.createQuery(sql)
                        .addParameter("id",uId)
                        .executeAndFetchFirst(User.class));
            }

        } catch (Sql2oException ex){
            System.out.println("Couldn't recover employees: " +ex);
        }

        return employees;
    }

    @Override
    public List<News> allDepartmentNews(int deptId) {
        List<News> news = new ArrayList<>();

        String joinQuery = "SELECT newsid FROM departments_news WHERE deptid = :deptid";
        try (Connection con = DB.sql2o.open()) {
            List<Integer> newsIDs = con.createQuery(joinQuery)
                    .addParameter("deptid",deptId)
                    .executeAndFetch(Integer.class);

            for(int nId:newsIDs){
                String sql = "SELECT * FROM news WHERE id = :id";
                news.add(con.createQuery(sql)
                        .addParameter("id",nId)
                        .executeAndFetchFirst(News.class));
            }

        } catch (Sql2oException ex){
            System.out.println("Couldn't recover news articles: " +ex);
        }
        return news;
    }

    @Override
    public void deleteDepartmentById(int id) {
        String sql = "DELETE from departments WHERE id = :id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteEmployeeFromDept(Department department,User user) {
        String sql = "DELETE from departments_users WHERE deptid = :deptid AND userid = :userid";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("deptid", department.getId())
                    .addParameter("userid", user.getId())
                    .executeUpdate();
            user.setDepartment("None");
            department.decrementTotalEmployees();
            updateEmployeeCount(department);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteDeptNewsById(int deptId, int newsId) {
        String sql = "DELETE from departments_news WHERE deptid = :deptid AND newsid = :newsid";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("deptid", deptId)
                    .addParameter("newsid", newsId)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE from departments";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void updateEmployeeCount(Department department) {
        String sql = "UPDATE departments SET totalemployees = :totalemployees WHERE id = :id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("totalemployees",department.getTotalEmployees())
                    .addParameter("id",department.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
