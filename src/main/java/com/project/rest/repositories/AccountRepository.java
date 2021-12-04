package com.project.rest.repositories;

import com.project.rest.entities.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query("select p from Account p where p.username like %?1%")
    List<Account> findAccountByUsername(String nome);
}
