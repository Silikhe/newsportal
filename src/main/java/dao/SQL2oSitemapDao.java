package dao;

import models.Sitemap;
import org.sql2o.Connection;
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
