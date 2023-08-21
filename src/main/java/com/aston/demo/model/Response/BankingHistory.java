package com.aston.demo.model.Response;

import com.aston.demo.entity.BankAccount;
import com.aston.demo.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class BankingHistory {

    private String firstName;
    private String lastName;
    private String fatherName;
    private UUID accountNumber;
    private Transaction[] transactions;
}
