package com.ejada.university.controller;

import com.ejada.university.entity.Department;
import com.ejada.university.entity.Instructor;
import com.ejada.university.exception.EntityNotFoundException;
import com.ejada.university.service.DepartmentService;
import com.ejada.university.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/instructors")
    public List<Instructor> findAll() {
        return instructorService.findAll();
    }

    @GetMapping("/instructors/{instructorId}")
    public Instructor getInstructor(@PathVariable int instructorId) {
        Instructor instructor = instructorService.findById(instructorId);

        if (instructor == null) {
            throw new EntityNotFoundException("Instructor id not found: " + instructorId);
        }

        return instructor;
    }

    @GetMapping("/instructors/departments/{departmentId}")
    public List<Instructor> getAllInstructorsByDepartmentId(@PathVariable int departmentId) {
        return instructorService.findByDepartmentId(departmentId);
    }


    @PostMapping("/instructors/departments/{departmentId}")
    public Instructor addInstructor(@PathVariable int departmentId, @RequestBody Instructor instructor) {

        // I set id by zero in case that id is passed to force saving
        instructor.setPersonId(0);
        Department department = departmentService.findById(departmentId);
        if (department == null) {
            throw new EntityNotFoundException("Department id not found: " + departmentId);
        }
        instructor.setDepartment(department);
        instructorService.save(instructor);
        return instructor;
    }

    @PutMapping("/instructors/departments/{departmentId}")
    public Instructor updateInstructor(@PathVariable int departmentId, @RequestBody Instructor instructor) {

        Department department = departmentService.findById(departmentId);
        if (department == null) {
            throw new EntityNotFoundException("Department id not found: " + departmentId);
        }
        instructor.setDepartment(department);
        instructorService.save(instructor);
        return instructor;
    }

    @DeleteMapping("/instructors/{instructorId}")
    public String deleteInstructor(@PathVariable int instructorId) {
        Instructor instructor = instructorService.findById(instructorId);

        if (instructor == null) {
            throw new EntityNotFoundException("Instructor id not found: " + instructorId);
        }

        instructorService.deleteById(instructorId);

        return "Deleted instructor with id : " + instructorId;
    }


}
