package com.aston.demo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Withdraw {

    @NotBlank
    @Pattern(regexp = "^[а-яА-Я]+$")
    private String firstName;

    @NotBlank
    private String lastName;


    private String fatherName;

    @NotBlank
    private String pin;

    @NotNull
    private UUID accountNumberFrom;

    @NotNull
    private Integer count;

}
