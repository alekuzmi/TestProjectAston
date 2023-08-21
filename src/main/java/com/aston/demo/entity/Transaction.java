package com.aston.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number_from")
    private UUID accountNumberFrom;

    @Column(name = "account_number_to")
    private UUID accountNumberTo;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "created_at")
    private LocalDateTime time;

    public Transaction() {
    }

    public Transaction(UUID accountNumberFrom, UUID accountNumberTo, Integer amount, LocalDateTime time) {
        this.accountNumberFrom = accountNumberFrom;
        this.accountNumberTo = accountNumberTo;
        this.amount = amount;
        this.time = time;
    }
}
