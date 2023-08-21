package com.aston.demo.model.Response;

import java.util.UUID;

public class ResponseTransaction {

    private String firstName;
    private String lastName;
    private String fatherName;
    private UUID accountNumberFrom;
    private UUID accountNumberTo;
    private Integer count;

    public ResponseTransaction(String firstName, String lastName, String fatherName, UUID accountNumberFrom, UUID accountNumberTo, Integer count) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.accountNumberFrom = accountNumberFrom;
        this.accountNumberTo = accountNumberTo;
        this.count = count;
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

    public UUID getAccountNumberFrom() {
        return accountNumberFrom;
    }

    public void setAccountNumberFrom(UUID accountNumberFrom) {
        this.accountNumberFrom = accountNumberFrom;
    }

    public UUID getAccountNumberTo() {
        return accountNumberTo;
    }

    public void setAccountNumberTo(UUID accountNumberTo) {
        this.accountNumberTo = accountNumberTo;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
