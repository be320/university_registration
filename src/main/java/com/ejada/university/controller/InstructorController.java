package com.ejada.university.controller;

import com.ejada.university.entity.Instructor;
import com.ejada.university.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @GetMapping("/instructors")
    public List<Instructor> findAll(){
        return instructorService.findAll();
    }

    @GetMapping("/instructors/{instructorId}")
    public Instructor getInstructor(@PathVariable int instructorId){
        Instructor instructor = instructorService.findById(instructorId);

        if(instructor == null){
            throw new RuntimeException("Instructor id not found: "+ instructorId);
        }

        return instructor;
    }

    @PostMapping("/instructors")
    public Instructor addInstructor(@RequestBody Instructor instructor){

        // I set id by zero in case that id is passed to force saving
        instructor.setPersonId(0);
        instructorService.save(instructor);

        return instructor;
    }

    @PutMapping("/instructors")
    public Instructor updateInstructor(@RequestBody Instructor instructor){

        instructorService.save(instructor);
        return instructor;
    }

    @DeleteMapping("/instructors/{instructorId}")
    public String deleteInstructor(@PathVariable int instructorId){
        Instructor instructor = instructorService.findById(instructorId);

        if(instructor == null){
            throw new RuntimeException("Instructor id not found: "+ instructorId);
        }

        instructorService.deleteById(instructorId);

        return "Deleted instructor with id : "+instructorId;
    }



}
