package com.aston.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Transfer {

    private String firstName;
    private String lastName;
    private String fatherName;
    private String pin;
    private UUID accountNumberFrom;
    private UUID accountNumberTo;
    private Integer count;

}
