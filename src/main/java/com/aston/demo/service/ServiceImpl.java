package com.aston.demo.service;


import com.aston.demo.entity.BankAccount;
import com.aston.demo.entity.Client;
import com.aston.demo.model.Request.Request;
import com.aston.demo.model.Request.Transaction;
import com.aston.demo.model.Response.ResponseCreate;
import com.aston.demo.model.Response.Info;
import com.aston.demo.model.Response.ResponseTransaction;
import com.aston.demo.repository.BankAccountRepository;
import com.aston.demo.repository.ClientRepository;
import com.aston.demo.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

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
            client = new Client(md5sum(request.getPin()),
                    request.getFirstName(), request.getLastName(), request.getFatherName());
            clientRepository.save(client);
        }
        if (!md5sum(request.getPin()).equals(client.getPinHash())) {
            return null;
        }
        BankAccount bankAccount = new BankAccount(client.getId(),  0);
        bankAccountRepository.save(bankAccount);
        return new ResponseCreate(client.getFirstName(), client.getLastName(), client.getFatherName(), bankAccount.getId(), bankAccount.getBalance());
    }

    @Transactional
    public Info info(Request request) {
        Client client = clientRepository.findByFirstNameAndLastName(request.getFirstName(), request.getLastName());
        System.out.println(client.getId());
        BankAccount[] bankAccounts = bankAccountRepository.findIdAndBalanceByClientId(client.getId());
        System.out.println(bankAccounts.length);
        if (!md5sum(request.getPin()).equals(client.getPinHash())) {

        }
        Info responseInfo = new Info(client.getFirstName(), client.getLastName(), client.getFatherName(), bankAccounts);
        return responseInfo;
    }

    @Transactional
    public ResponseTransaction deposit(Transaction transaction) {
        Client client = clientRepository.findByFirstNameAndLastName(transaction.getFirstName(), transaction.getLastName());
        if (!md5sum(transaction.getPin()).equals(client.getPinHash())) {

        }
        BankAccount bankAccount = bankAccountRepository.findById(transaction.getAccountNumberTo()).get();
        bankAccount.setBalance(bankAccount.getBalance() + transaction.getCount());
        bankAccountRepository.save(bankAccount);
        transactionRepository.save(new com.aston.demo.entity.Transaction(null, transaction.getAccountNumberTo(),
                transaction.getCount(),  LocalDateTime.now()));
        return new ResponseTransaction(transaction.getFirstName(), transaction.getLastName(), transaction.getFatherName(),
                null, transaction.getAccountNumberTo(), transaction.getCount());
    }


    @Transactional
    public ResponseTransaction withdraw(Transaction transaction) {
        Client client = clientRepository.findByFirstNameAndLastName(transaction.getFirstName(), transaction.getLastName());
        if (!md5sum(transaction.getPin()).equals(client.getPinHash())) {

        }
        BankAccount bankAccount = bankAccountRepository.findById(transaction.getAccountNumberFrom()).get();
        if (transaction.getCount() > bankAccount.getBalance()){
            return null;
        }
        bankAccount.setBalance(bankAccount.getBalance() - transaction.getCount());
        bankAccountRepository.save(bankAccount);
        transactionRepository.save(new com.aston.demo.entity.Transaction(transaction.getAccountNumberFrom(), null,
                transaction.getCount(),  LocalDateTime.now()));
        return new ResponseTransaction(transaction.getFirstName(), transaction.getLastName(), transaction.getFatherName(),
                transaction.getAccountNumberFrom(), null, transaction.getCount());
    }

    @Transactional
    public ResponseTransaction transfer(Transaction transaction) {
        Client client = clientRepository.findByFirstNameAndLastName(transaction.getFirstName(), transaction.getLastName());
        if (!md5sum(transaction.getPin()).equals(client.getPinHash())) {

        }
        BankAccount bankAccountFrom = bankAccountRepository.findById(transaction.getAccountNumberFrom()).get();
        BankAccount bankAccountTo = bankAccountRepository.findById(transaction.getAccountNumberTo()).get();
        if (transaction.getCount() > bankAccountFrom.getBalance()){
            return null;
        }
        bankAccountFrom.setBalance(bankAccountFrom.getBalance() - transaction.getCount());
        bankAccountTo.setBalance(bankAccountTo.getBalance() + transaction.getCount());
        bankAccountRepository.save(bankAccountFrom);
        bankAccountRepository.save(bankAccountTo);
        transactionRepository.save(new com.aston.demo.entity.Transaction(transaction.getAccountNumberFrom(), transaction.getAccountNumberTo(),
                transaction.getCount(),  LocalDateTime.now()));
        return new ResponseTransaction(transaction.getFirstName(), transaction.getLastName(), transaction.getFatherName(),
                transaction.getAccountNumberFrom(), transaction.getAccountNumberTo(), transaction.getCount());
    }

    public static String md5sum(String input) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("Unable to compute md5sum for string", ex);
        }
        assert (digest != null);
        digest.update(input.getBytes());
        BigInteger hash = new BigInteger(1, digest.digest());
        return (hash.toString(16));
    }
}

