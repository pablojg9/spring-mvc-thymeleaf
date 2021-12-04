package com.project.rest.repositories;

import com.project.rest.entities.Phone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PhoneRepository extends CrudRepository<Phone, Long> {

    @Query("SELECT p from Phone p where p.account.id = ?1")
    List<Phone> getPhones(Long idAccount);
}
