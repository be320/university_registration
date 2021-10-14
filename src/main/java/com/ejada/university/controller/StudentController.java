package com.ejada.university.controller;

import com.ejada.university.entity.Student;
import com.ejada.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        Student student = studentService.findById(studentId);

        if(student == null){
            throw new RuntimeException("Student id not found: "+ studentId);
        }

        return student;
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){

        // I set id by zero in case that id is passed to force saving
        student.setStudentId(0);
        studentService.save(student);

        return student;
    }

    @PutMapping("/employees")
    public Student updateStudent(@RequestBody Student student){

        studentService.save(student);
        return student;
    }

    @DeleteMapping("/students/{studentId}")
    public String deleteStudent(@PathVariable int studentId){
        Student student = studentService.findById(studentId);

        if(student == null){
            throw new RuntimeException("Student id not found: "+ studentId);
        }

        studentService.deleteById(studentId);

        return "Deleted student with id : "+studentId;
    }



}
