package com.ejada.university.service;

import com.ejada.university.dao.CourseDAO;
import com.ejada.university.entity.Course;
import com.ejada.university.entity.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseDAO courseDAO;

    @Transactional
    public List<Course> findAll() {
        return courseDAO.findAll();
    }

    @Transactional
    public Course findById(int id) {
        return courseDAO.findById(id);
    }

    @Transactional
    public List<Course> findByDepartmentId(int id) {
        return courseDAO.findByDepartmentId(id);
    }

    @Transactional
    public void save(Course course) {
        courseDAO.save(course);
    }

    @Transactional
    public void deleteById(int id) {
        courseDAO.deleteById(id);
    }
}
