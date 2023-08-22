package com.aston.demo.repository;

import com.aston.demo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByFirstNameAndLastName(String firstName, String lastName);

}
