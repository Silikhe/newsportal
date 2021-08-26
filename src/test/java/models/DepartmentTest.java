package models;

import dao.DatabaseRule;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("ConstantConditions")
public class DepartmentTest {
    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    private Department newDept(){
        return new Department("Producers","We the best");
    }

    @Test
    public void initializeCorrectly(){
        Department department = newDept();
        assertTrue(department instanceof Department);
    }

    @Test
    public void getMethodsWorkCorrectly(){
        Department department = newDept();
        assertEquals("Producers",department.getName());
        assertEquals("We the best",department.getDescription());
    }

}