package com.project.rest.service;

import com.project.rest.model.Account;
import com.project.rest.model.Phone;
import com.project.rest.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    public Optional<Phone> findById(Long idPhone) {
        return phoneRepository.findById(idPhone);
    }

    public void save(Phone phone) {
        phoneRepository.save(phone);
    }

}
