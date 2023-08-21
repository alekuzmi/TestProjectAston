package com.aston.demo.service;


import com.aston.demo.entity.BankAccount;
import com.aston.demo.entity.Client;
import com.aston.demo.entity.Transaction;
import com.aston.demo.model.*;
import com.aston.demo.repository.BankAccountRepository;
import com.aston.demo.repository.ClientRepository;
import com.aston.demo.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceImpl {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public ResponseCreate create(Request request) {
        Client client = clientRepository.findByFirstNameAndLastName(request.getFirstName(), request.getLastName());

        if (client == null) {
            client = new Client(request.getPin(),
                    request.getFirstName(), request.getLastName(), request.getFatherName());
            clientRepository.save(client);
        }
        if (!request.getPin().equals(client.getPinHash())) {
            return null;
        }
        System.out.println(client.getId());
        BankAccount bankAccount = new BankAccount(client.getId(),  0);
        bankAccountRepository.save(bankAccount);
        return new ResponseCreate(client.getFirstName(), client.getLastName(), client.getFatherName(), bankAccount.getId(), bankAccount.getBalance());
    }


    @Transactional
    public ResponseInfo info(Request request) {
        Client client = clientRepository.findByFirstNameAndLastName(request.getFirstName(), request.getLastName());
        System.out.println(client.getId());
        BankAccount[] bankAccounts = bankAccountRepository.findIdAndBalanceByClientId(client.getId());
        System.out.println(bankAccounts.length);
        if (!request.getPin().equals(client.getPinHash())) {

        }
        ResponseInfo responseInfo = new ResponseInfo(client.getFirstName(), client.getLastName(), client.getFatherName(), bankAccounts);
        return responseInfo;
    }

    @Transactional
    public ResponseTransaction deposit(RequestTransaction requestTransaction) {
        Client client = clientRepository.findByFirstNameAndLastName(requestTransaction.getFirstName(), requestTransaction.getLastName());
        if (!requestTransaction.getPin().equals(client.getPinHash())) {

        }
        BankAccount bankAccount = bankAccountRepository.findById(requestTransaction.getAccountNumberTo()).get();
        bankAccount.setBalance(bankAccount.getBalance() + requestTransaction.getCount());
        bankAccountRepository.save(bankAccount);
        transactionRepository.save(new Transaction(null, requestTransaction.getAccountNumberTo(),
                requestTransaction.getCount(),  LocalDateTime.now()));
        return new ResponseTransaction(requestTransaction.getFirstName(), requestTransaction.getLastName(), requestTransaction.getFatherName(),
                null, requestTransaction.getAccountNumberTo(), requestTransaction.getCount());
    }


    @Transactional
    public ResponseTransaction withdraw(RequestTransaction requestTransaction) {
        Client client = clientRepository.findByFirstNameAndLastName(requestTransaction.getFirstName(), requestTransaction.getLastName());
        if (!requestTransaction.getPin().equals(client.getPinHash())) {

        }
        BankAccount bankAccount = bankAccountRepository.findById(requestTransaction.getAccountNumberFrom()).get();
        if (requestTransaction.getCount() > bankAccount.getBalance()){
            return null;
        }
        bankAccount.setBalance(bankAccount.getBalance() - requestTransaction.getCount());
        bankAccountRepository.save(bankAccount);
        transactionRepository.save(new Transaction(requestTransaction.getAccountNumberFrom(), null,
                requestTransaction.getCount(),  LocalDateTime.now()));
        return new ResponseTransaction(requestTransaction.getFirstName(), requestTransaction.getLastName(), requestTransaction.getFatherName(),
                requestTransaction.getAccountNumberFrom(), null, requestTransaction.getCount());
    }

    @Transactional
    public ResponseTransaction transfer(RequestTransaction requestTransaction) {
        Client client = clientRepository.findByFirstNameAndLastName(requestTransaction.getFirstName(), requestTransaction.getLastName());
        if (!requestTransaction.getPin().equals(client.getPinHash())) {

        }
        BankAccount bankAccountFrom = bankAccountRepository.findById(requestTransaction.getAccountNumberFrom()).get();
        BankAccount bankAccountTo = bankAccountRepository.findById(requestTransaction.getAccountNumberTo()).get();
        if (requestTransaction.getCount() > bankAccountFrom.getBalance()){
            return null;
        }
        bankAccountFrom.setBalance(bankAccountFrom.getBalance() - requestTransaction.getCount());
        bankAccountTo.setBalance(bankAccountTo.getBalance() + requestTransaction.getCount());
        bankAccountRepository.save(bankAccountFrom);
        bankAccountRepository.save(bankAccountTo);
        transactionRepository.save(new Transaction(requestTransaction.getAccountNumberFrom(), requestTransaction.getAccountNumberTo(),
                requestTransaction.getCount(),  LocalDateTime.now()));
        return new ResponseTransaction(requestTransaction.getFirstName(), requestTransaction.getLastName(), requestTransaction.getFatherName(),
                requestTransaction.getAccountNumberFrom(), requestTransaction.getAccountNumberTo(), requestTransaction.getCount());
    }
}

