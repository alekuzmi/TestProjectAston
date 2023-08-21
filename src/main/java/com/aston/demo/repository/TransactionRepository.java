package com.aston.demo.repository;

import com.aston.demo.entity.Client;
import com.aston.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
