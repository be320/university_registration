package com.ejada.university.controller;

import com.ejada.university.entity.Student;
import com.ejada.university.exception.NotFoundException;
import com.ejada.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * get all the students in the university
     * @return students
     */
    @GetMapping("/students")
    public List<Student> findAll(){
        return studentService.findAll();
    }

    /**
     * get a specific student by passing his id
     * @param studentId
     * @return student
     */
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        Student student = studentService.findById(studentId);

        if (student == null) {
            throw new NotFoundException("Student id not found: " + studentId);
        }

        return student;
    }

    /**
     * add new student
     * @param student
     * @return student
     */
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){

        // I set id by zero in case that id is passed to force saving
        student.setPersonId(0);
        studentService.save(student);

        return student;
    }

    /**
     * update student information
     * @param student
     * @return student
     */
    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student student){

        studentService.save(student);
        return student;
    }

    /**
     * delete a student by passing his id
     * @param studentId
     * @return
     */
    @DeleteMapping("/students/{studentId}")
    public String deleteStudent(@PathVariable int studentId){
        Student student = studentService.findById(studentId);

        if (student == null) {
            throw new NotFoundException("Student id not found: " + studentId);
        }

        studentService.deleteById(studentId);

        return "Deleted student with id : "+studentId;
    }



}
