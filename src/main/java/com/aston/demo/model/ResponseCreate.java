package com.aston.demo.model;

import java.util.UUID;

public class ResponseCreate {

    private String firstName;
    private String lastName;
    private String fatherName;
    private UUID accountNumber;
    private Integer balance;

    public ResponseCreate(String firstName, String lastName, String fatherName, UUID accountNumber, Integer balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public UUID getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(UUID accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
