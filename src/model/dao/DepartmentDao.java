package model.dao;

import model.entities.Department;

import java.util.List;

public interface DepartmentDao {

    void insert(Department obj);
    void update(Department obj);
    void deleteById(Integer obj);
    Department findyById(Integer id);
    List<Department> findAll();



}
