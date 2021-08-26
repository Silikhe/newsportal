package dao;
import org.sql2o.*;
import java.net.URI;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DB {

//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/news_portal", "moringa", "star2030");
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-3-227-44-84.compute-1.amazonaws.com:5432/d1htma1u288veo", "wcazoxrhtknfuh", "cdcc7bcffebf84e13f78e8aaa05cfbfda95509ed3f7c487889f1e58f0df70903");


//    private static URI dbUri;
//    public static Sql2o sql2o;
//
//
//
//    static Logger logger = LoggerFactory.getLogger(DB.class);
//    static {
//
//        try {
//            if (System.getenv("DATABASE_URL") == null) {
//                dbUri = new URI("postgres://localhost:5432/news_portal");
//            } else {
//                dbUri = new URI(System.getenv("DATABASE_URL"));
//            }
//            int port = dbUri.getPort();
//            String host = dbUri.getHost();
//            String path = dbUri.getPath();
//            String username = (dbUri.getUserInfo() == null) ? "moringa" : dbUri.getUserInfo().split(":")[0];
//            String password = (dbUri.getUserInfo() == null) ? "star2030" : dbUri.getUserInfo().split(":")[1];
//            sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + port + path, username, password);
//        } catch (URISyntaxException e ) {
//            logger.error("Unable to connect to database.");
//        }
//    }


}
