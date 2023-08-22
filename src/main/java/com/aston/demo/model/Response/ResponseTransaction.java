package com.aston.demo.model.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseTransaction {

    private String firstName;
    private String lastName;
    private String fatherName;
    private UUID accountNumberFrom;
    private UUID accountNumberTo;
    private Integer count;

}
