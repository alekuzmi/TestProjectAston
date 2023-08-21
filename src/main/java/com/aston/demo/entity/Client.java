package com.aston.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pin_hash")
    private String pinHash;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "father_name")
    private String fatherName;

    public Client() {
    }

    public Client(String pinHash, String firstName, String lastName, String fatherName) {
        this.pinHash = pinHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
    }
}
