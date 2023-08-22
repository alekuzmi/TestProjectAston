package com.aston.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "bank_accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "balance")
    private Integer balance;

    public BankAccount() {
    }

    public BankAccount(Long clientId, Integer balance) {
        this.clientId = clientId;
        this.balance = balance;
    }
}
