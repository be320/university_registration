package com.ejada.university.controller;

import com.ejada.university.entity.Course;
import com.ejada.university.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public List<Course> findAll(){
        return courseService.findAll();
    }

    @GetMapping("/courses/{courseId}")
    public Course getCourse(@PathVariable int courseId){
        Course course = courseService.findById(courseId);

        if(course == null){
            throw new RuntimeException("Course id not found: "+ courseId);
        }

        return course;
    }

    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course){

        // I set id by zero in case that id is passed to force saving
        course.setCourseId(0);
        courseService.save(course);

        return course;
    }

    @PutMapping("/courses")
    public Course updateCourse(@RequestBody Course course){

        courseService.save(course);
        return course;
    }

    @DeleteMapping("/courses/{courseId}")
    public String deleteCourse(@PathVariable int courseId){
        Course course = courseService.findById(courseId);

        if(course == null){
            throw new RuntimeException("Course id not found: "+ courseId);
        }

        courseService.deleteById(courseId);

        return "Deleted Course with id : "+courseId;
    }



}
