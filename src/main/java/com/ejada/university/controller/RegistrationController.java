package com.ejada.university.controller;

import com.ejada.university.entity.Registration;
import com.ejada.university.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

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

    @PostMapping("/registrations")
    public Registration addRegistration(@RequestBody Registration registration){

        // I set id by zero in case that id is passed to force saving
        registration.setRegistrationId(0);
        registrationService.save(registration);

        return registration;
    }

    @PutMapping("/registrations")
    public Registration updateRegistration(@RequestBody Registration registration){

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
