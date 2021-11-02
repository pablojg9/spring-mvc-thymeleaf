package com.project.rest.repository;

import com.project.rest.model.Phone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PhoneRepository extends CrudRepository<Phone, Long> {
}
