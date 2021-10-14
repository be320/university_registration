package com.ejada.university.service;

import com.ejada.university.dao.StudentDAO;
import com.ejada.university.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Transactional
    public List<Student> findAll(){
        return studentDAO.findAll();
    }

    @Transactional
    public Student findById(int id){
        return studentDAO.findById(id);
    }

    @Transactional
    public void save(Student student){
        studentDAO.save(student);
    }

    @Transactional
    public void deleteById(int id){
        studentDAO.deleteById(id);
    }
}
