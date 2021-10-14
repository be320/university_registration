package com.ejada.university.service;

import com.ejada.university.dao.InstructorDAO;
import com.ejada.university.entity.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InstructorService {

    @Autowired
    private InstructorDAO instructorDAO;

    @Transactional
    public List<Instructor> findAll(){
        return instructorDAO.findAll();
    }

    @Transactional
    public Instructor findById(int id){
        return instructorDAO.findById(id);
    }

    @Transactional
    public void save(Instructor instructor){
        instructorDAO.save(instructor);
    }

    @Transactional
    public void deleteById(int id){
        instructorDAO.deleteById(id);
    }
}
