package com.ejada.university.dao;

import com.ejada.university.entity.Department;
import com.ejada.university.entity.Instructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class DepartmentDAO {

    @Autowired
    private EntityManager entityManager;

    public List<Department> findAll(){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Department> query = currentSession.createQuery("from Department", Department.class);
        List<Department> departments = query.getResultList();
        return departments;
    }

    public Department findById(int id){
        Session currentSession = entityManager.unwrap(Session.class);
        Department department = currentSession.get(Department.class, id);
        return department;
    }

    public void save(Department department){
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(department);
    }

    public void addManager(Department department){
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(department);
    }

    public boolean checkIfManagerIsUnique(int managerId){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Department> query = currentSession.createQuery("SELECT D from Department D JOIN D.manager M WHERE M.personId = :manager_id", Department.class);
        query.setParameter("manager_id", managerId);
        List<Department> departments = query.getResultList();
        if (departments.size() > 0)
            return false;
        return true;
    }

    public void deleteById(int id){
        Session currentSession = entityManager.unwrap(Session.class);
        Department department = currentSession.get(Department.class, id);
        currentSession.delete(department);
    }
}
