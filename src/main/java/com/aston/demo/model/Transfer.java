package com.aston.demo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Transfer {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String fatherName;

    @NotBlank
    private String pin;

    @NotBlank
    private UUID accountNumberFrom;

    @NotNull
    private UUID accountNumberTo;

    @NotNull
    private Integer count;

}
