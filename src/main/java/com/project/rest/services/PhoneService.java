package com.project.rest.services;

import com.project.rest.entities.Phone;
import com.project.rest.repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    public Iterable<Phone> findAll() {
        return phoneRepository.findAll();
    }

    public Optional<Phone> findById(Long idPhone) {
        return phoneRepository.findById(idPhone);
    }

    public void save(Phone phone) {
        phoneRepository.save(phone);
    }
}
