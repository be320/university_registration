package com.ejada.university.controller;

import com.ejada.university.entity.Course;
import com.ejada.university.entity.Instructor;
import com.ejada.university.entity.Registration;
import com.ejada.university.entity.Student;
import com.ejada.university.service.CourseService;
import com.ejada.university.service.RegistrationService;
import com.ejada.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/registrations")
    public List<Registration> findAll(){
        return registrationService.findAll();
    }

    @GetMapping("/registrations/{registrationId}")
    public Registration getRegistration(@PathVariable int registrationId){
        Registration registration = registrationService.findById(registrationId);

        if(registration == null){
            throw new RuntimeException("Registration id not found: "+ registrationId);
        }

        return registration;
    }

    @GetMapping("/registrations/students/{studentId}")
    public List<Registration> getAllRegistrationByStudentId(@PathVariable int studentId) {
        return registrationService.findByStudentId(studentId);
    }

    @PostMapping("/registrations/students/{studentId}/courses/{courseId}")
    public Registration addRegistration(@PathVariable int studentId, @PathVariable int courseId, @RequestBody Registration registration){

        // I set id by zero in case that id is passed to force saving
        registration.setRegistrationId(0);
        Course course = courseService.findById(courseId);
        Student student = studentService.findById(studentId);
        registration.setStudent(student);
        registration.setCourse(course);
        registrationService.save(registration);

        return registration;
    }

    @PutMapping("/registrations/students/{studentId}/courses/{courseId}")
    public Registration updateRegistration(@PathVariable int studentId, @PathVariable int courseId, @RequestBody Registration registration){

        Course course = courseService.findById(courseId);
        Student student = studentService.findById(studentId);
        registration.setStudent(student);
        registration.setCourse(course);
        registrationService.save(registration);

        return registration;
    }

    @DeleteMapping("/registrations/{registrationId}")
    public String deleteRegistration(@PathVariable int registrationId){
        Registration registration = registrationService.findById(registrationId);

        if(registration == null){
            throw new RuntimeException("Registration id not found: "+ registrationId);
        }

        registrationService.deleteById(registrationId);

        return "Deleted registration with id : "+registrationId;
    }



}
