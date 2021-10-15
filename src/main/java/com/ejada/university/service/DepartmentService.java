package com.ejada.university.service;

import com.ejada.university.dao.DepartmentDAO;
import com.ejada.university.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentDAO departmentDAO;

    @Transactional
    public List<Department> findAll(){
        return departmentDAO.findAll();
    }

    @Transactional
    public Department findById(int id){
        return departmentDAO.findById(id);
    }

    @Transactional
    public void save(Department department){
        departmentDAO.save(department);
    }

    @Transactional
    public void addManager(Department department){
        departmentDAO.addManager(department);
    }

    @Transactional
    public void deleteById(int id){
        departmentDAO.deleteById(id);
    }
}
