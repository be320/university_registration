package com.ejada.university.dao;

import com.ejada.university.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class StudentDAO {

    @Autowired
    private EntityManager entityManager;

    public List<Student> findAll(){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Student> query = currentSession.createQuery("from Student", Student.class);
        List<Student> students = query.getResultList();
        return students;
    }

    public Student findById(int id){
        Session currentSession = entityManager.unwrap(Session.class);
        Student student = currentSession.get(Student.class, id);
        return student;
    }

    public void save(Student student){
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(student);
    }

    public void deleteById(int id){
        Session currentSession = entityManager.unwrap(Session.class);
        Student student = currentSession.get(Student.class, id);
        currentSession.delete(student);
    }




}
