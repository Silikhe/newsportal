package models;

import dao.DatabaseRule;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("ConstantConditions")
public class UserTest {

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    private User newUser(){
        return new User("Kanye West","Manager","Artist","Media");
    }

    @Test
    public void initializeCorrectly(){
        User user = newUser();
        assertTrue(user instanceof User);
    }

    @Test
    public void getFunctionsWorkWell(){
        User user = newUser();
        assertEquals(user.getName(),newUser().getName());
        assertEquals(user.getPosition(),newUser().getPosition());
        assertEquals(user.getRole(),newUser().getRole());
        assertEquals(user.getDepartment(),newUser().getDepartment());
    }


}