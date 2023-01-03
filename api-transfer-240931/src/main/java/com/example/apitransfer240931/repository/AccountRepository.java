package com.example.apitransfer240931.repository;

import com.example.apitransfer240931.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsById(Long id);
}

