package com.aston.demo.service;


import com.aston.demo.entity.BankAccount;
import com.aston.demo.entity.Client;
import com.aston.demo.entity.Transaction;
import com.aston.demo.exception.BusinessException;
import com.aston.demo.model.Response.BankingHistory;
import com.aston.demo.model.Response.StatementBalance;
import com.aston.demo.model.Response.BankAccountInfo;
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

    private static UUID ATM_NUMBER = new UUID(0, 0);

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public BankAccountInfo create(String firstName, String lastName, String fatherName, String pin) throws BusinessException {
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
        return new BankAccountInfo(client.getFirstName(), client.getLastName(), client.getFatherName(),
                bankAccount.getId(), bankAccount.getBalance());
    }

    @Transactional
    public StatementBalance info(String firstName, String lastName, String fatherName, String pin) throws BusinessException {
        Client client = clientRepository.findByFirstNameAndLastName(firstName, lastName);
        BankAccount[] bankAccounts = bankAccountRepository.findByClientId(client.getId());
        if (!md5sum(pin).equals(client.getPinHash())) {
            throw new BusinessException("PIN не подходит");
        }
        StatementBalance responseStatementBalance = new StatementBalance(client.getFirstName(), client.getLastName(), client.getFatherName(), bankAccounts);
        return responseStatementBalance;
    }

    @Transactional
    public BankingHistory history(String firstName, String lastName, String fatherName, String pin, UUID accountNumber) throws BusinessException {
        Client client = clientRepository.findByFirstNameAndLastName(firstName, lastName);
        System.out.println(ATM_NUMBER);
        if (!md5sum(pin).equals(client.getPinHash())) {
            throw new BusinessException("PIN не подходит");
        }

        Transaction[] transactions = transactionRepository.findByAccountNumberFromOrAccountNumberTo(accountNumber, accountNumber);
        return new BankingHistory(firstName, lastName, fatherName, accountNumber, transactions);
    }


    @Transactional
    public ResponseTransaction deposit(String firstName, String lastName, String fatherName, String pin,
                                       UUID accountNumberTo, Integer count) throws BusinessException {
        Client client = clientRepository.findByFirstNameAndLastName(firstName, lastName);
        if (!md5sum(pin).equals(client.getPinHash())) {
            throw new BusinessException("PIN не подходит");
        }

        BankAccount bankAccount = bankAccountRepository.findById(accountNumberTo).get();
        synchronized (bankAccount.getId().toString().intern()) {
            bankAccount.setBalance(bankAccount.getBalance() + count);
            bankAccountRepository.save(bankAccount);
            transactionRepository.save(new Transaction(ATM_NUMBER, accountNumberTo,
                    count, LocalDateTime.now()));
            return new ResponseTransaction(firstName, lastName, fatherName,
                    null, accountNumberTo, count);
        }
    }


    @Transactional
    public ResponseTransaction withdraw(String firstName, String lastName, String fatherName, String pin,
                                        UUID accountNumberFrom, Integer count) throws BusinessException {
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
            transactionRepository.save(new Transaction(accountNumberFrom, ATM_NUMBER,
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
                transactionRepository.save(new Transaction(accountNumberFrom, accountNumberTo,
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

