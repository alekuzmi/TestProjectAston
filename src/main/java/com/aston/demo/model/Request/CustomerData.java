package com.aston.demo.model.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerData {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String fatherName;

    @NotBlank
    private String pin;

}
