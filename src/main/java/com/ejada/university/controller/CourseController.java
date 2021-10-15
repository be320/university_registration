package com.ejada.university.controller;

import com.ejada.university.entity.Course;
import com.ejada.university.entity.Department;
import com.ejada.university.entity.Instructor;
import com.ejada.university.service.CourseService;
import com.ejada.university.service.DepartmentService;
import com.ejada.university.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private DepartmentService departmentService;

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

    @GetMapping("/courses/departments/{departmentId}")
    public List<Course> getAllCoursesByDepartmentId(@PathVariable int departmentId){
        return courseService.findByDepartmentId(departmentId);
    }

    @PostMapping("/courses/departments/{departmentId}/instructors/{instructorId}")
    public Course addCourse(@PathVariable int departmentId, @PathVariable int instructorId,@RequestBody Course course){

        // I set id by zero in case that id is passed to force saving
        course.setCourseId(0);
        Department department = departmentService.findById(departmentId);
        Instructor instructor = instructorService.findById(instructorId);
        course.setDepartment(department);
        course.setInstructor(instructor);
        courseService.save(course);

        return course;
    }

    @PutMapping("/courses/departments/{departmentId}/instructors/{instructorId}")
    public Course updateCourse(@PathVariable int departmentId, @PathVariable int instructorId,@RequestBody Course course){

        Department department = departmentService.findById(departmentId);
        Instructor instructor = instructorService.findById(instructorId);
        course.setDepartment(department);
        course.setInstructor(instructor);
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
