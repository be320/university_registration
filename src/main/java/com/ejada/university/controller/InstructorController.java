package com.ejada.university.controller;

import com.ejada.university.entity.Department;
import com.ejada.university.entity.Instructor;
import com.ejada.university.exception.NotFoundException;
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

    /**
     * get all instructors
     * @return instructors
     */
    @GetMapping("/instructors")
    public List<Instructor> findAll() {
        return instructorService.findAll();
    }

    /**
     * get instructor by passing its id
     * @param instructorId
     * @return instructor
     */
    @GetMapping("/instructors/{instructorId}")
    public Instructor getInstructor(@PathVariable int instructorId) {
        Instructor instructor = instructorService.findById(instructorId);

        if (instructor == null) {
            throw new NotFoundException("Instructor id not found: " + instructorId);
        }

        return instructor;
    }


    /**
     * get all the instructors teaching in a department by passing department id
     * @param departmentId
     * @return instructors
     */
    @GetMapping("/instructors/departments/{departmentId}")
    public List<Instructor> getAllInstructorsByDepartmentId(@PathVariable int departmentId) {
        return instructorService.findByDepartmentId(departmentId);
    }


    /**
     * add new instructor and assign him to a department
     * @param departmentId
     * @param instructor
     * @return instructor
     */
    @PostMapping("/instructors/departments/{departmentId}")
    public Instructor addInstructor(@PathVariable int departmentId, @RequestBody Instructor instructor) {

        // I set id by zero in case that id is passed to force saving
        instructor.setPersonId(0);
        Department department = departmentService.findById(departmentId);
        if (department == null) {
            throw new NotFoundException("Department id not found: " + departmentId);
        }
        instructor.setDepartment(department);
        instructorService.save(instructor);
        return instructor;
    }

    /**
     * update existing instructor data
     * @param departmentId
     * @param instructor
     * @return instructor
     */
    @PutMapping("/instructors/departments/{departmentId}")
    public Instructor updateInstructor(@PathVariable int departmentId, @RequestBody Instructor instructor) {

        Department department = departmentService.findById(departmentId);
        if (department == null) {
            throw new NotFoundException("Department id not found: " + departmentId);
        }
        instructor.setDepartment(department);
        instructorService.save(instructor);
        return instructor;
    }

    /**
     * delete instructor by passing its id
     * @param instructorId
     * @return deleting message
     */
    @DeleteMapping("/instructors/{instructorId}")
    public String deleteInstructor(@PathVariable int instructorId) {
        Instructor instructor = instructorService.findById(instructorId);

        if (instructor == null) {
            throw new NotFoundException("Instructor id not found: " + instructorId);
        }

        instructorService.deleteById(instructorId);

        return "Deleted instructor with id : " + instructorId;
    }


}
