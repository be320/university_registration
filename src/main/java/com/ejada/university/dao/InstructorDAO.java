package com.ejada.university.dao;

import com.ejada.university.entity.Course;
import com.ejada.university.entity.Instructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class InstructorDAO {

    @Autowired
    private EntityManager entityManager;

    public List<Instructor> findAll(){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Instructor> query = currentSession.createQuery("from Instructor", Instructor.class);
        List<Instructor> instructors = query.getResultList();
        return instructors;
    }

    public Instructor findById(int id){
        Session currentSession = entityManager.unwrap(Session.class);
        Instructor instructor = currentSession.get(Instructor.class, id);
        return instructor;
    }

    public List<Instructor> findByDepartmentId(int departmentId){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Instructor> query = currentSession.createQuery("select I from Instructor I JOIN I.department D WHERE D.departmentId = :department_id", Instructor.class);
        query.setParameter("department_id",departmentId);
        List<Instructor> instructors = query.getResultList();
        return instructors;
    }

    public boolean isInstructorInDepartment(int instructorId, int departmentId){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Instructor> query = currentSession.createQuery("select I from Instructor I JOIN I.department D WHERE D.departmentId = :department_id AND I.personId = :instructor_id", Instructor.class);
        query.setParameter("department_id", departmentId);
        query.setParameter("instructor_id", instructorId);
        if(query.getResultList().size() > 0)
            return true;
        return false;
    }

    public void save(Instructor instructor){
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(instructor);
    }

    public void deleteById(int id){
        Session currentSession = entityManager.unwrap(Session.class);
        Instructor instructor = currentSession.get(Instructor.class, id);
        currentSession.delete(instructor);
    }

}
