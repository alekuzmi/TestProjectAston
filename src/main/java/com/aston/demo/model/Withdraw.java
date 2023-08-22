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
    @Pattern(regexp = "^[а-яА-Я]+$")
    private String lastName;

    @Pattern(regexp = "^[а-яА-Я]+$")
    private String fatherName;

    @NotBlank
    @Pattern(regexp = "^[0-9]+$")
    private String pin;

    @NotNull
    private UUID accountNumberFrom;

    @NotNull
    private Integer count;

}
