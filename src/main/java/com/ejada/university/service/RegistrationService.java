package com.ejada.university.service;

import com.ejada.university.dao.RegistrationDAO;
import com.ejada.university.entity.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationDAO registrationDAO;

    @Transactional
    public List<Registration> findAll(){
        return registrationDAO.findAll();
    }

    @Transactional
    public Registration findById(int id){
        return registrationDAO.findById(id);
    }

    @Transactional
    public void save(Registration registration){
        registrationDAO.save(registration);
    }

    @Transactional
    public void deleteById(int id){
        registrationDAO.deleteById(id);
    }
}
