package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    
    AccountRepository accountRepository;


    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public ResponseEntity<Account> createAccount(Account account){
        if (accountRepository.findByUsername(account.getUsername()).isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        if(account.getUsername().length() == 0 || account.getUsername().length() < 4)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        
        return ResponseEntity.status(HttpStatus.OK).body(accountRepository.save(account));
        
    }

    @Transactional
    public ResponseEntity<Account> login(Account account){
        Optional<Account> foundAccount = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (foundAccount.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        
        return ResponseEntity.status(HttpStatus.OK).body(foundAccount.get());
        
    }
}
