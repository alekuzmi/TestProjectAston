package com.aston.demo.model;

import com.aston.demo.entity.BankAccount;

public class ResponseInfo {

    private String firstName;
    private String lastName;
    private String fatherName;
    private BankAccount[] bankAccounts;

    public ResponseInfo(String firstName, String lastName, String fatherName, BankAccount[] bankAccounts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.bankAccounts = bankAccounts;
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

    public BankAccount[] getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(BankAccount[] bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
