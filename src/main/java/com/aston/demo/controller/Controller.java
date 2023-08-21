package com.aston.demo.controller;


import com.aston.demo.exception.BusinessException;
import com.aston.demo.model.Request.AccountData;
import com.aston.demo.model.Request.CustomerData;
import com.aston.demo.model.Deposit;
import com.aston.demo.model.Response.*;
import com.aston.demo.model.Transfer;
import com.aston.demo.model.Withdraw;
import com.aston.demo.service.ServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    private final ServiceImpl clientService;

    @Autowired
    public Controller(ServiceImpl clientService) {
        this.clientService = clientService;
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorMessage> handleException(BusinessException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<BankAccountInfo> create(@Valid @RequestBody CustomerData customerData) throws Exception {
        BankAccountInfo bankAccountInfo = clientService.create(customerData.getFirstName(), customerData.getLastName(),
                customerData.getFatherName(), customerData.getPin());
        return ResponseEntity.ok(bankAccountInfo);
    }


    @PostMapping("/balance")
    public ResponseEntity<StatementBalance> statementBalance(@Valid @RequestBody CustomerData customerData) throws BusinessException {
        StatementBalance statementBalance = clientService.info(customerData.getFirstName(), customerData.getLastName(),
                customerData.getFatherName(), customerData.getPin());
        return ResponseEntity.ok(statementBalance);
    }

    @RequestMapping("/history")
    public ResponseEntity<BankingHistory>  history(@Valid @RequestBody AccountData accountData) throws BusinessException {
        BankingHistory bankingHistory = clientService.history(accountData.getFirstName(), accountData.getLastName(),
                accountData.getFatherName(), accountData.getPin(), accountData.getAccountNumber());
        return ResponseEntity.ok(bankingHistory);
    }

    @PostMapping("/deposit")
    public ResponseEntity<ResponseTransaction> deposit(@Valid @RequestBody Deposit deposit) throws BusinessException {
        ResponseTransaction responseTransaction = clientService.deposit(deposit.getFirstName(), deposit.getLastName(),
                deposit.getFatherName(), deposit.getPin(), deposit.getAccountNumberTo(),
                deposit.getCount());
        return ResponseEntity.ok(responseTransaction);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ResponseTransaction> withdraw(@Valid @RequestBody Withdraw withdraw) throws BusinessException {
        ResponseTransaction responseTransaction = clientService.withdraw(withdraw.getFirstName(), withdraw.getLastName(),
                withdraw.getFatherName(), withdraw.getPin(), withdraw.getAccountNumberFrom(), withdraw.getCount());
        return ResponseEntity.ok(responseTransaction);
    }


    @PostMapping("/transfer")
    public ResponseEntity<ResponseTransaction> transfer(@Valid @RequestBody Transfer transfer) throws BusinessException {
        ResponseTransaction responseTransaction = clientService.transfer(transfer.getFirstName(), transfer.getLastName(),
                transfer.getFatherName(), transfer.getPin(), transfer.getAccountNumberFrom(), transfer.getAccountNumberTo(),
                transfer.getCount());
        return ResponseEntity.ok(responseTransaction);
    }

}

