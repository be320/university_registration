package com.ejada.university.dao;

import com.ejada.university.entity.Instructor;
import com.ejada.university.entity.Registration;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RegistrationDAO {

    @Autowired
    private EntityManager entityManager;

    public List<Registration> findAll(){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Registration> query = currentSession.createQuery("from Registration", Registration.class);
        List<Registration> registrations = query.getResultList();
        return registrations;
    }

    public Registration findById(int id){
        Session currentSession = entityManager.unwrap(Session.class);
        Registration registration = currentSession.get(Registration.class, id);
        return registration;
    }

    public List<Registration> findByStudentId(int studentId){
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Registration> query = currentSession.createQuery("select R from Registration R JOIN R.student S WHERE S.personId = :student_id", Registration.class);
        query.setParameter("student_id",studentId);
        List<Registration> registrations = query.getResultList();
        return registrations;
    }

    public void save(Registration registration){
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(registration);
    }

    public void deleteById(int id){
        Session currentSession = entityManager.unwrap(Session.class);
        Registration registration = currentSession.get(Registration.class, id);
        currentSession.delete(registration);
    }
}
