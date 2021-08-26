package dao;

import models.User;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("SimplifiableJUnitAssertion")
public class Sql2oUserDaoTest {
    private static Sql2oUserDao userDao = new Sql2oUserDao();

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    private User newUser(){
        User user = new User("Kanye West","Manager","Artist","Media");
        userDao.add(user);
        return user;
    }
    private User newUser2(){
        User user = new User("John Cena","Wrestler","Invisible","Human Resource");
        userDao.add(user);
        return user;
    }

    @Test
    public void userSavedToDatabase(){
        User user = newUser();
        assertNotEquals(0,user.getId());
    }

    @Test
    public void findingParticularUser(){
        User user = newUser();
        User user2 = newUser2();
        User foundUser = userDao.findById(user.getId());
        assertTrue(user.equals(foundUser));
    }

    @Test
    public void getAllUsers(){
        User user = newUser();
        User user2 = newUser2();
        assertTrue(userDao.allUsers().contains(user));
        assertTrue(userDao.allUsers().contains(user2));
    }

    @Test
    public void deleteUserFromDatabase_int(){
        User user = newUser();
        User user2 = newUser2();
        userDao.deleteById(user.getId());
        assertEquals(1, userDao.allUsers().size());
    }

    @Test
    public void deleteAllUsers(){
        User user = newUser();
        User user2 = newUser2();
        userDao.deleteAll();
        assertEquals(0, userDao.allUsers().size());
    }



}