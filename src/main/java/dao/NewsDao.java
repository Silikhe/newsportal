package dao;

import models.News;
import java.util.List;

public interface NewsDao {
    //create
    void add(News news);
    void addNewsToDepartment(int deptid, int newsid, int userid);

    //read
    News findById(int id);
    List<News> allNews();
    List<News> allGeneralNews();
    List<News> allDepartmentalNews();

    //update

    //delete
    void deleteById(int id);
    void deleteAll();
}
