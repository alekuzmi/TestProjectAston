package com.aston.demo.service;


import com.aston.demo.entity.BankAccount;
import com.aston.demo.entity.Client;
import com.aston.demo.exception.BusinessException;
import com.aston.demo.model.Response.Info;
import com.aston.demo.model.Response.ResponseCreate;
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
    public ResponseCreate create(String firstName, String lastName, String fatherName, String pin) throws BusinessException {
        Client client = clientRepository.findByFirstNameAndLastName(firstName, lastName);

        if (client == null) {
            client = new Client(md5sum(pin), firstName, lastName, fatherName);
            clientRepository.save(client);
        }
        if (!md5sum(pin).equals(client.getPinHash())) {
            throw new BusinessException("PIN не подходит");
        }
        BankAccount bankAccount = new BankAccount(client.getId(), 0);
        bankAccountRepository.save(bankAccount);
        return new ResponseCreate(client.getFirstName(), client.getLastName(), client.getFatherName(),
                bankAccount.getId(), bankAccount.getBalance());
    }

    @Transactional
    public Info info(String firstName, String lastName, String fatherName, String pin) throws BusinessException {
        Client client = clientRepository.findByFirstNameAndLastName(firstName, lastName);
        System.out.println(client.getId());
        BankAccount[] bankAccounts = bankAccountRepository.findIdAndBalanceByClientId(client.getId());
        System.out.println(bankAccounts.length);
        if (!md5sum(pin).equals(client.getPinHash())) {
            throw new BusinessException("PIN не подходит");
        }
        Info responseInfo = new Info(client.getFirstName(), client.getLastName(), client.getFatherName(), bankAccounts);
        return responseInfo;
    }

    @Transactional
    public ResponseTransaction deposit(String firstName, String lastName, String fatherName, String pin,
                                       UUID accountNumberFrom, UUID accountNumberTo, Integer count) throws BusinessException {
        Client client = clientRepository.findByFirstNameAndLastName(firstName, lastName);
        if (!md5sum(pin).equals(client.getPinHash())) {
            throw new BusinessException("PIN не подходит");
        }

        BankAccount bankAccount = bankAccountRepository.findById(accountNumberTo).get();
        synchronized (bankAccount.getId().toString().intern()) {
            bankAccount.setBalance(bankAccount.getBalance() + count);
            bankAccountRepository.save(bankAccount);
            transactionRepository.save(new com.aston.demo.entity.Transaction(null, accountNumberTo,
                    count, LocalDateTime.now()));
            return new ResponseTransaction(firstName, lastName, fatherName,
                    null, accountNumberTo, count);
        }
    }


    @Transactional
    public ResponseTransaction withdraw(String firstName, String lastName, String fatherName, String pin,
                                        UUID accountNumberFrom, UUID accountNumberTo, Integer count) throws BusinessException {
        Client client = clientRepository.findByFirstNameAndLastName(firstName, lastName);
        if (!md5sum(pin).equals(client.getPinHash())) {
            throw new BusinessException("PIN не подходит.");
        }
        BankAccount bankAccount = bankAccountRepository.findById(accountNumberFrom).get();
        synchronized (bankAccount.getId().toString().intern()) {
            if (count > bankAccount.getBalance()) {
                throw new BusinessException("Недостаточно средств.");
            }
            bankAccount.setBalance(bankAccount.getBalance() - count);
            bankAccountRepository.save(bankAccount);
            transactionRepository.save(new com.aston.demo.entity.Transaction(accountNumberFrom, null,
                    count, LocalDateTime.now()));
            return new ResponseTransaction(firstName, lastName, fatherName,
                    accountNumberFrom, null, count);
        }
    }

    @Transactional
    public ResponseTransaction transfer(String firstName, String lastName, String fatherName, String pin,
                                        UUID accountNumberFrom, UUID accountNumberTo, Integer count) throws BusinessException {
        Client client = clientRepository.findByFirstNameAndLastName(firstName, lastName);
        if (!md5sum(pin).equals(client.getPinHash())) {
            throw new BusinessException("PIN не подходит");
        }
        BankAccount bankAccountFrom = bankAccountRepository.findById(accountNumberFrom).get();
        BankAccount bankAccountTo = bankAccountRepository.findById(accountNumberTo).get();

        UUID firstAccount = accountNumberFrom.compareTo(accountNumberTo) > 0 ? accountNumberFrom : accountNumberTo;
        UUID secondAccount = accountNumberFrom.compareTo(accountNumberTo) > 0 ? accountNumberTo : accountNumberFrom;

        synchronized (firstAccount.toString().intern()) {
            synchronized (secondAccount.toString().intern()) {

                if (count > bankAccountFrom.getBalance()) {
                    throw new BusinessException("Недостаточно средств.");
                }
                bankAccountFrom.setBalance(bankAccountFrom.getBalance() - count);
                bankAccountTo.setBalance(bankAccountTo.getBalance() + count);
                bankAccountRepository.save(bankAccountFrom);
                bankAccountRepository.save(bankAccountTo);
                transactionRepository.save(new com.aston.demo.entity.Transaction(accountNumberFrom, accountNumberTo,
                        count, LocalDateTime.now()));
                return new ResponseTransaction(firstName, lastName, fatherName,
                        accountNumberFrom, accountNumberTo, count);

            }
        }
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

