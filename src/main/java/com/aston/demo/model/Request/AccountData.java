package com.aston.demo.model.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class AccountData {

    private String firstName;
    private String lastName;
    private String fatherName;
    private String pin;
    private UUID accountNumber;

}
