package dao;

import models.Sitemap;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;

public class SQL2oSitemapDao {
    public SQL2oSitemapDao() {
    }

    public List<Sitemap> allPaths() {
        String sql = "SELECT * from sitemap;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Sitemap.class);
        }
    }

//    public static addPaths(int id, cha){
//
//    }

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
}
