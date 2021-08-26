package dao;

import models.Department;
import models.User;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("unused")
public class Sql2oDeptDaoTest {
    private Sql2oDeptDao deptDao = new Sql2oDeptDao();
    private static Sql2oUserDao userDao = new Sql2oUserDao();

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    private Department newDept(){
        Department department = new Department("Tech-one","We help you learn fast");
        deptDao.add(department);
        return department;
    }

    private Department newDept2(){
        Department department = new Department("Tech","Only code");
        deptDao.add(department);
        return department;
    }

    private User newUser(){
        User user = new User("Tevin Issac","techy","Mentor","Tech-one");
        userDao.add(user);
        return user;
    }

    private User newUser2(){
        User user = new User("Silas Silikhe","developer","coding","Tech");
        userDao.add(user);
        return user;
    }

    @Test
    public void departmentAddedToDatabase(){
        Department department = newDept();
        System.out.println(department.getId());
        assertNotEquals(0,department.getId());
    }

    @Test
    public void getDepartmentUsingId(){
        Department department = newDept();
        Department department2 = newDept2();
        assertTrue(deptDao.allDepartments().contains(department));
        assertTrue(deptDao.allDepartments().contains(department2));
    }

    @Test
    public void addUserToDepartment(){
        Department department = newDept();
        User user = newUser();
        deptDao.addUserToDept(department,user);
        assertEquals("Tech-one",user.getDepartment());
    }

    @Test
    public void getUsersInADepartment(){
        Department department = newDept();
        User user = newUser();
        User user2 = newUser2();
        deptDao.addUserToDept(department,user);
        deptDao.addUserToDept(department,user2);
        int posOneId = deptDao.allDepartmentEmployees(department.getId()).get(0).getId();
        assertEquals(user.getId(),posOneId);
    }

    @Test
    public void deletingEmployeeById(){
        Department department = newDept();
        User user = newUser();
        User user2 = newUser2();
        deptDao.addUserToDept(department,user);
        deptDao.addUserToDept(department,user2);

        deptDao.deleteEmployeeFromDept(department,user);
        assertEquals(1,department.getTotalEmployees());
        assertEquals("None",user.getDepartment());
    }

    @Test
    public void deletingAllDepartments(){
        Department department = newDept();
        Department department2= newDept2();
        deptDao.deleteAll();
        assertEquals(0,deptDao.allDepartments().size());
    }

    @Test
    public void deletingDepartmentById(){
        Department department = newDept();
        Department department2= newDept2();
        deptDao.deleteDepartmentById(department.getId());
        assertEquals(1,deptDao.allDepartments().size());
    }



}