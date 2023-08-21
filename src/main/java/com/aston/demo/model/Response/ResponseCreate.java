package com.aston.demo.model.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ResponseCreate {

    private String firstName;
    private String lastName;
    private String fatherName;
    private UUID accountNumber;
    private Integer balance;



}
