package dao;

import models.Sitemap;
import models.User;
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



}
