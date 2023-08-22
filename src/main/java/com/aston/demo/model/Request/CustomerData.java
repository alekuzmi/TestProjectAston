package com.aston.demo.model.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerData {

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

}
