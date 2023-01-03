package com.example.apitransfer240931.controller;

import com.example.apitransfer240931.exception.InvalidCurrencyException;
import com.example.apitransfer240931.model.Account;
import com.example.apitransfer240931.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AccountController {
    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        validateAccount(account);
        return new ResponseEntity<Account>(accountRepository.save(account), HttpStatus.CREATED);
    }

    private void validateAccount(Account account){
        //Las únicas divisas permitidas son EUR, USD y GBP
        if(!account.getCurrency().equals("EUR") && !account.getCurrency().equals("USD") && !account.getCurrency().equals("GBP")){
            throw new InvalidCurrencyException("La divisa especificada es inválida, cuando se ingresa un valor diferente a\n" +
                    "EUR, USD y GBP");
        }


    }


}
