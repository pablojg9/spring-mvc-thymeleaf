package com.project.rest.service;

import com.project.rest.model.Account;
import com.project.rest.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long idAccount){
        Optional<Account> account = accountRepository.findById(idAccount);
        return account;
    }

    public void save(Account account){
        accountRepository.save(account);
    }

    public void deleteById(Long idAccount) {
        accountRepository.deleteById(idAccount);
    }
}
