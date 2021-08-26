import static spark.Spark.*;

import com.google.gson.Gson;
import dao.SQL2oSitemapDao;
import models.*;
import dao.Sql2oNewsDao;
import dao.Sql2oDeptDao;
import dao.Sql2oUserDao;

public class App {
    public static void main(String[] args) {
        Sql2oDeptDao deptDao = new Sql2oDeptDao();
        Sql2oUserDao userDao = new Sql2oUserDao();
        Sql2oNewsDao newsDao = new Sql2oNewsDao();
        SQL2oSitemapDao sitemapDao = new SQL2oSitemapDao();
        Gson gson = new Gson();

        ProcessBuilder process = new ProcessBuilder();
        int port;

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);




        get("/departments","application/json",(request, response) -> gson.toJson(deptDao.allDepartments()));

        post("/departments/new","application/json",(request, response) -> {
            Department department = gson.fromJson(request.body(),Department.class);
            deptDao.add(department);
            response.status(201);
            return gson.toJson(department);
        });

//        post("/sitemap/new","application/json",(request, response) -> {
//            Sitemap sitemap = gson.fromJson(request.body(),Sitemap.class);
//            sitemapDao.add(sitemap);
//            response.status(201);
//            return gson.toJson(sitemap);
//        });

        get("/departments/:deptId/users/:userId/details","application/json",(request, response) -> {
            int userId = Integer.parseInt(request.params("userId"));
            User foundUser = userDao.findById(userId);

            if (foundUser != null) {
                return gson.toJson(foundUser);
            }
            else {
                return "{\"Error 404!\":\"User not found\"}";
            }
        });

        get("/departments/:deptId/details","application/json",(request, response) -> {
            int deptId = Integer.parseInt(request.params("deptId"));
            return gson.toJson(deptDao.findById(deptId));
        });

        post("/departments/:deptId/users/new","application/json",(request, response) -> {
            int deptId = Integer.parseInt(request.params("deptId"));
            Department department = deptDao.findById(deptId);

            if(department != null){
                User employee = gson.fromJson(request.body(),User.class);
                employee.setDepartment(department.getName());
                userDao.add(employee);
                deptDao.addUserToDept(department,employee);
                response.status(201);
                return gson.toJson(employee);
            } else {
                throw new ApiException(404,"Department not found");
            }
        });

        get("/departments/:deptId/users","application/json",(request, response) -> {
            int deptId = Integer.parseInt(request.params("deptId"));
            return gson.toJson(deptDao.allDepartmentEmployees(deptId));
        });



        get("/departments/:deptId/users/:userId/news","application/json",(request, response) -> {
            int userId = Integer.parseInt(request.params("userId"));
            User foundUser = userDao.findById(userId);

            if (foundUser != null) {
                return gson.toJson(userDao.myNews(userId));
            }
            else {
                return "{\"Error 404!\":\"User not found\"}";
            }
        });

        post("/departments/:deptId/users/:userId/news/new","application/json",(request, response) -> {
            int userId = Integer.parseInt(request.params("userId"));
            int deptId = Integer.parseInt(request.params("deptId"));
            User foundUser = userDao.findById(userId);
            Department foundDept = deptDao.findById(deptId);

            if (foundUser != null && foundDept != null) {
                News news = gson.fromJson(request.body(),News.class);
                news.setType(foundDept.getName());
                news.setAuthor(foundUser.getName());
                newsDao.add(news);
                newsDao.addNewsToDepartment(deptId,news.getId(),userId);
                response.status(201);
                return gson.toJson(news);
            }
            else {
                return "{\"Error 404!\":\"User or Department not found\"}";
            }
        });

        get("/departments/:deptId/news","application/json",(request, response) -> {
            int deptId = Integer.parseInt(request.params("deptId"));
            return gson.toJson(deptDao.allDepartmentNews(deptId));
        });

//        Users
        get("/users","application/json",(request, response) -> gson.toJson(userDao.allUsers()));

        get("/users/:userId/details","application/json",(request, response) -> {
            int userId = Integer.parseInt(request.params("userId"));
            User foundUser = userDao.findById(userId);
            if (foundUser != null) {
                return gson.toJson(userDao.findById(userId));
            }
            else {
                return "{\"Error 404!\":\"User not found.\"}";
            }
        });

        get("/users/:userId/news","application/json",(request, response) -> {
            int userId = Integer.parseInt(request.params("userId"));
            return gson.toJson(userDao.myNews(userId));
        });

        post("/users/:userId/news/new","application/json",(request, response) -> {
            int userId = Integer.parseInt(request.params("userId"));
            User foundUser = userDao.findById(userId);

            if (foundUser != null) {
                News news = gson.fromJson(request.body(),News.class);
                news.setAuthor(foundUser.getName());
                newsDao.add(news);
                newsDao.addNewsToDepartment(0,news.getId(),userId);
                response.status(201);
                return gson.toJson(news);
            }
            else {
                return "{\"Error 404!\":\"User not found. News cannot be posted without an actual user as the author\"}";
            }
        });



//        News
        get("/news","application/json",(request, response) -> gson.toJson(newsDao.allNews()));
        get("/news/general","application/json",(request, response) -> gson.toJson(newsDao.allGeneralNews()));
        get("/news/departments","application/json",(request, response) -> gson.toJson(newsDao.allDepartmentalNews()));
        get("/news/:newsId/details","application/json",(request, response) -> {
            int newsId = Integer.parseInt(request.params("newsId"));
            return gson.toJson(newsDao.findById(newsId));
        });

        get("/sitemap","application/json",(request, response) ->{
            return gson.toJson(sitemapDao.allPaths());
        });



        //filter
        after((req, res) -> res.type("application/json"));
    }
}
