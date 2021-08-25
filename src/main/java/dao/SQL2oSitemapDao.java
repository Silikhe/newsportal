package dao;

import models.Sitemap;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;

public class SQL2oSitemapDao {
    public SQL2oSitemapDao() {
    }

    public void add(Sitemap sitemap) {
        String sql = "INSERT INTO users (id,descr,path,) VALUES (:id,:descr,:path);";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql,true)
                    .bind(sitemap)
                    .executeUpdate()
                    .getKey();
            sitemap.setId(id);
        } catch (Sql2oException ex){
            System.out.println("User not added: "+ex);
        }
    }

    public List<Sitemap> allPaths() {
        String sql = "SELECT * from sitemap;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Sitemap.class);
        }
    }


//    public Sitemap findById(int id) {
//    }
}
