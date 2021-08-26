package dao;

import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oNewsDao implements NewsDao {
    public Sql2oNewsDao(){}

    @Override
    public void add(News news) {
        String checkedType = crossCheckDepartments(news.getType());

        String sql = "INSERT INTO news (title,description,type,author) VALUES (:title,:description,:type,:author)";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql,true)
                    .addParameter("title",news.getTitle())
                    .addParameter("description",news.getDescription())
                    .addParameter("type",checkedType)
                    .addParameter("author",news.getAuthor())
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void addNewsToDepartment(int deptid, int newsid, int userid) {
        String sql = "INSERT INTO departments_news (deptid,newsid,userid) VALUES (:deptid,:newsid,:userid)";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("deptid",deptid)
                    .addParameter("newsid",newsid)
                    .addParameter("userid",userid)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public News findById(int id) {
        String sql = "SELECT * from news WHERE id=:id;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(News.class);
        }
    }

    @Override
    public List<News> allNews() {
        String sql = "SELECT * from news;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public List<News> allGeneralNews() {
        String sql = "SELECT * from news where type='General';";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public List<News> allDepartmentalNews() {
        String sql = "SELECT * from news where type!='General';";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from news WHERE id=:id;";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println("Delete by id error: "+ex);
        }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE from news;";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    private String crossCheckDepartments(String deptName) {
        String sql = "SELECT name from departments;";
        try (Connection con = DB.sql2o.open()) {
            List<String> allNames = con.createQuery(sql)
                    .executeAndFetch(String.class);

            if(deptName.equalsIgnoreCase("general")){
                deptName = "General";
            }
            else {
                for(String name:allNames){
                    if(deptName.equalsIgnoreCase(name)){
                        deptName = name;
                        break;
                    }
                }
            }
        }
        return deptName;
    }
}
