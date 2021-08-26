package dao;

import models.Department;
import models.News;
import models.User;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("ConstantConditions")
public class Sql2oNewsDaoTest {
    private Sql2oDeptDao deptDao = new Sql2oDeptDao();
    private static Sql2oNewsDao newsDao = new Sql2oNewsDao();
    private static Sql2oUserDao userDao = new Sql2oUserDao();

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();
    
    private News altNews(){
        News news = new News("New Album","Kanye drop new album");
        news.setAuthor(newUser().getName());
        newsDao.add(news);
        return news;
    }
    private News altNews2(){
        News news = new News("New Album Yet Again","Kanye drop new album again");
        news.setType("entertainment");
        news.setAuthor(newUser2().getName());
        newsDao.add(news);
        return news;
    }

    private Department newDept(){
        Department department = new Department("Entertainment","We the best");
        deptDao.add(department);
        return department;
    }

    private User newUser(){
        User user = new User("Kanye West","Manager","Artist","Media");
        userDao.add(user);
        return user;
    }

    private User newUser2(){
        User user = new User("John Cena","Wrestler","Invisible","Wrestler");
        userDao.add(user);
        return user;
    }

    @Test
    public void newsGetsSavedToDb(){
        News news = altNews();
        assertNotEquals(0,news.getId());
    }

    @Test
    public void findNewsById(){
        News news = altNews();
        News news2 = altNews2();
        assertTrue(news.equals(newsDao.findById(news.getId())));
    }


    @Test
    public void getDepartmentAfterCrosscheck_String(){
        Department department = newDept();
        News news2 = altNews2();
        assertEquals("Entertainment",newsDao.findById(news2.getId()).getType());
    }

    @Test
    public void findAllNewsPosts_int(){
        News news = altNews();
        News news2 = altNews2();
        assertEquals(2,newsDao.allNews().size());
    }

    @Test
    public void simpleDeleteNewsById_true(){
        News news = altNews();
        News news2 = altNews2();
        newsDao.deleteById(news.getId());
        assertEquals(1,newsDao.allNews().size());
    }

    @Test
    public void deleteAllNewsPosts(){
        News news = altNews();
        News news2 = altNews2();
        newsDao.deleteAll();
        assertEquals(0,newsDao.allNews().size());
    }

    @Test
    public void addNewsToGeneralDepartment(){
        User user = newUser();
        News news = altNews();
        newsDao.addNewsToDepartment(0,news.getId(),user.getId());
        assertEquals(1,deptDao.allDepartmentNews(0).size());
        assertEquals("General",deptDao.allDepartmentNews(0).get(0).getType());
    }

    @Test
    public void addNewsToSpecificDepartment(){
        Department department = newDept();
        User user = newUser();
        News news = altNews2();
        newsDao.addNewsToDepartment(department.getId(),news.getId(),user.getId());
        assertEquals(1,deptDao.allDepartmentNews(department.getId()).size());
    }

}