package com.aston.demo.model.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class AccountData {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String fatherName;

    @NotBlank
    private String pin;

    @NotNull
    private UUID accountNumber;

}
