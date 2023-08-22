package com.aston.demo.repository;

import com.aston.demo.entity.BankAccount;
import com.aston.demo.entity.Client;
import com.aston.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Transaction[] findByAccountNumberFromOrAccountNumberTo(UUID ClientIdFrom, UUID ClientIdTo);
}
