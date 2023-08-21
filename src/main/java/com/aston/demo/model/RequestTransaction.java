package com.aston.demo.model;

import java.util.UUID;

public class RequestTransaction {

    private String firstName;
    private String lastName;
    private String fatherName;
    private String pin;
    private UUID accountNumberFrom;
    private UUID accountNumberTo;
    private Integer count;

    public RequestTransaction(String firstName, String lastName, String fatherName, String pin,
                              UUID accountNumberFrom, UUID accountNumberTo, Integer count) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.pin = pin;
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

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
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
