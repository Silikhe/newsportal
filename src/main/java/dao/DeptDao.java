package dao;

import models.Department;
import models.News;
import models.User;

import java.util.List;

public interface DeptDao {
    //create
    void add(Department department);
    void addUserToDept(Department department,User user);

    //read
    Department findById(int id);
    List<Department> allDepartments();
    List<User> allDepartmentEmployees(int deptId);
    List<News> allDepartmentNews(int deptId);

    //update
    void updateEmployeeCount(Department department);

    //delete
    void deleteDepartmentById(int id);
    void deleteEmployeeFromDept(Department department,User user);
    void deleteDeptNewsById(int deptId, int newsId);
    void deleteAll();
}
