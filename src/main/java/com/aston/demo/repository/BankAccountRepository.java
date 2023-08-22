package com.aston.demo.repository;


import com.aston.demo.entity.BankAccount;
import com.aston.demo.entity.Client;
import jakarta.persistence.Column;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {

    BankAccount[] findByClientId(Long ClientId);

}