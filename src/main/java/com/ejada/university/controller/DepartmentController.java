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
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private InstructorService instructorService;

    @GetMapping("/departments")
    public List<Department> findAll() {
        return departmentService.findAll();
    }

    @GetMapping("/departments/{departmentId}")
    public Department getDepartment(@PathVariable int departmentId) {
        Department department = departmentService.findById(departmentId);

        if (department == null) {
            throw new EntityNotFoundException("Department id not found: " + departmentId);
        }

        return department;
    }

    @PostMapping("/departments")
    public Department addDepartment(@RequestBody Department department) {

        // I set id by zero in case that id is passed to force saving
        department.setDepartmentId(0);
        departmentService.save(department);

        return department;
    }

    @PutMapping("/departments")
    public Department updateDepartment(@RequestBody Department department) {

        departmentService.save(department);
        return department;
    }

    @PutMapping("/departments/instructors/{instructorId}")
    public Department addManager(@PathVariable int instructorId, @RequestBody Department department) {

        Instructor manager = instructorService.findById(instructorId);
        if (manager == null) {
            throw new EntityNotFoundException("Instructor id not found: " + instructorId);
        }
        department.setManager(manager);
        departmentService.addManager(department);
        return department;
    }

    @DeleteMapping("/departments/{departmentId}")
    public String deleteDepartment(@PathVariable int departmentId) {
        Department department = departmentService.findById(departmentId);

        if (department == null) {
            throw new EntityNotFoundException("Department id not found: " + departmentId);
        }
        departmentService.deleteById(departmentId);

        return "Deleted department with id : " + departmentId;
    }


}
