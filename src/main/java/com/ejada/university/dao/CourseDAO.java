package com.ejada.university.dao;

import com.ejada.university.entity.Course;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CourseDAO {

    @Autowired
    private EntityManager entityManager;

    public List<Course> findAll(){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Course> query = currentSession.createQuery("from Course", Course.class);
        List<Course> courses = query.getResultList();
        return courses;
    }

    public Course findById(int id){
        Session currentSession = entityManager.unwrap(Session.class);
        Course course = currentSession.get(Course.class, id);
        return course;
    }

    public void save(Course course){
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(course);
    }

    public void deleteById(int id){
        Session currentSession = entityManager.unwrap(Session.class);
        Course course = currentSession.get(Course.class, id);
        currentSession.delete(course);
    }
}
