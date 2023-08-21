package com.aston.demo.model.Response;

import com.aston.demo.entity.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Info {

    private String firstName;
    private String lastName;
    private String fatherName;
    private BankAccount[] bankAccounts;

}
