package com.aston.demo.model.Response;

import com.aston.demo.entity.BankAccount;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatementBalance {

    private String firstName;
    private String lastName;
    private String fatherName;
    private BankAccount[] bankAccounts;

    public class Filter {
        public Filter() {
        }

        @Override
        public boolean equals(Object value) {
            if(value == null)
                return true;
            return "****MASKED_VALUE****".equals(value);
        }
    }
}
