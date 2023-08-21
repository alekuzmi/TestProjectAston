package com.aston.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

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

    public Transaction(UUID accountNumberFrom, UUID accountNumberTo, Integer count, LocalDateTime time) {
        this.accountNumberFrom = accountNumberFrom;
        this.accountNumberTo = accountNumberTo;
        this.amount = count;
        this.time = time;
    }

    public UUID getAccountNumberFrom() {
        return accountNumberFrom;
    }

    public void setAccountNumberFrom(UUID accountNumberFrom) {
        this.accountNumberFrom = accountNumberFrom;
    }

    public UUID getAccountNumberTo() {
        return accountNumberTo;
    }

    public void setAccountNumberTo(UUID accountNumberTo) {
        this.accountNumberTo = accountNumberTo;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
