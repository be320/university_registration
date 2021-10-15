package com.ejada.university.controller;

import com.ejada.university.entity.Course;
import com.ejada.university.entity.Instructor;
import com.ejada.university.entity.Registration;
import com.ejada.university.entity.Student;
import com.ejada.university.exception.EntityNotFoundException;
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

    /**
     * get all university course registrations
     * @return registrations
     */
    @GetMapping("/registrations")
    public List<Registration> findAll() {
        return registrationService.findAll();
    }

    /**
     * get a specific registration by passing its id
     * @param registrationId
     * @return registration
     */
    @GetMapping("/registrations/{registrationId}")
    public Registration getRegistration(@PathVariable int registrationId) {
        Registration registration = registrationService.findById(registrationId);

        if (registration == null) {
            throw new EntityNotFoundException("Registration id not found: " + registrationId);
        }

        return registration;
    }


    /**
     * get all the registrations of specific student by passing his id
     * @param studentId
     * @return registrations
     */
    @GetMapping("/registrations/students/{studentId}")
    public List<Registration> getAllRegistrationByStudentId(@PathVariable int studentId) {
        return registrationService.findByStudentId(studentId);
    }

    /**
     * add new registration by passing student id and the course id
     * @param studentId
     * @param courseId
     * @param registration
     * @return registration
     */
    @PostMapping("/registrations/students/{studentId}/courses/{courseId}")
    public Registration addRegistration(@PathVariable int studentId, @PathVariable int courseId, @RequestBody Registration registration) {

        // I set id by zero in case that id is passed to force saving
        registration.setRegistrationId(0);
        Course course = courseService.findById(courseId);
        if (course == null) {
            throw new EntityNotFoundException("Course id not found: " + courseId);
        }
        Student student = studentService.findById(studentId);
        if (student == null) {
            throw new EntityNotFoundException("Student id not found: " + studentId);
        }
        registration.setStudent(student);
        registration.setCourse(course);
        registrationService.save(registration);

        return registration;
    }

    /**
     * update student registration like changing the student marks in the course
     * @param studentId
     * @param courseId
     * @param registration
     * @return registration
     */
    @PutMapping("/registrations/students/{studentId}/courses/{courseId}")
    public Registration updateRegistration(@PathVariable int studentId, @PathVariable int courseId, @RequestBody Registration registration) {

        Course course = courseService.findById(courseId);
        if (course == null) {
            throw new EntityNotFoundException("Course id not found: " + courseId);
        }
        Student student = studentService.findById(studentId);
        if (student == null) {
            throw new EntityNotFoundException("Student id not found: " + studentId);
        }
        registration.setStudent(student);
        registration.setCourse(course);
        registrationService.save(registration);

        return registration;
    }

    /**
     * delete registration by passing its id
     * @param registrationId
     * @return
     */
    @DeleteMapping("/registrations/{registrationId}")
    public String deleteRegistration(@PathVariable int registrationId) {
        Registration registration = registrationService.findById(registrationId);

        if (registration == null) {
            throw new EntityNotFoundException("Registration id not found: " + registrationId);
        }

        registrationService.deleteById(registrationId);

        return "Deleted registration with id : " + registrationId;
    }


}
