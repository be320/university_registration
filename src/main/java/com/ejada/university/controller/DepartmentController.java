package com.ejada.university.controller;

import com.ejada.university.entity.Department;
import com.ejada.university.entity.Instructor;
import com.ejada.university.exception.NotFoundException;
import com.ejada.university.exception.ConflictException;
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

    /**
     * get all the departments in the university
     * @return departments
     */
    @GetMapping("/departments")
    public List<Department> findAll() {
        return departmentService.findAll();
    }

    /**
     * get a department by passing department id
     * @param departmentId
     * @return department
     */
    @GetMapping("/departments/{departmentId}")
    public Department getDepartment(@PathVariable int departmentId) {
        Department department = departmentService.findById(departmentId);

        if (department == null) {
            throw new NotFoundException("Department id not found: " + departmentId);
        }

        return department;
    }

    /**
     * add new department
     * @param department
     * @return department
     */
    @PostMapping("/departments")
    public Department addDepartment(@RequestBody Department department) {

        // I set id by zero in case that id is passed to force saving
        department.setDepartmentId(0);
        departmentService.save(department);

        return department;
    }

    /**
     * update existing department data
     * @param department
     * @return department
     */
    @PutMapping("/departments")
    public Department updateDepartment(@RequestBody Department department) {

        departmentService.save(department);
        return department;
    }

    /**
     * assign manager to existing department
     * @param instructorId
     * @param department
     * @return department
     */
    @PutMapping("/departments/instructors/{instructorId}")
    public Department addManager(@PathVariable int instructorId, @RequestBody Department department) {

        Instructor manager = instructorService.findById(instructorId);
        if (manager == null) {
            throw new NotFoundException("Instructor id not found: " + instructorId);
        }

        boolean managerIsUnique = departmentService.checkIfManagerIsUnique(manager.getPersonId());
        if(!managerIsUnique){
            throw new ConflictException("this instructor with id "+  + instructorId +" is a manager for other department.");
        }

        department.setManager(manager);
        departmentService.addManager(department);
        return department;
    }

    /**
     * delete department by passing its id
     * @param departmentId
     * @return
     */
    @DeleteMapping("/departments/{departmentId}")
    public String deleteDepartment(@PathVariable int departmentId) {
        Department department = departmentService.findById(departmentId);

        if (department == null) {
            throw new NotFoundException("Department id not found: " + departmentId);
        }
        departmentService.deleteById(departmentId);

        return "Deleted department with id : " + departmentId;
    }


}
