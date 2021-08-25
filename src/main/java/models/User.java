package models;

import java.util.Objects;

public class User {
    private String name;
    private String position;
    private String role;
    private String department;
    private int id;

    public User(String name, String position, String role, String department) {
        this.name = name;
        this.position = position;
        this.role = role;
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User users = (User) o;
        return Objects.equals(name, users.name) &&
                Objects.equals(position, users.position) &&
                Objects.equals(role, users.role) &&
                Objects.equals(department, users.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, role, department);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
