package com.aston.demo.controller;


import com.aston.demo.exception.BusinessException;
import com.aston.demo.model.Request.AccountData;
import com.aston.demo.model.Request.CustomerData;
import com.aston.demo.model.Deposit;
import com.aston.demo.model.Response.BankingHistory;
import com.aston.demo.model.Transfer;
import com.aston.demo.model.Withdraw;
import com.aston.demo.model.Response.BankAccountInfo;
import com.aston.demo.model.Response.StatementBalance;
import com.aston.demo.model.Response.ResponseTransaction;
import com.aston.demo.service.ServiceImpl;
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
    public ResponseEntity handleException(BusinessException e) {
        Exception exception = new Exception(e.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<BankAccountInfo> create(@RequestBody CustomerData customerData) throws Exception {
        BankAccountInfo bankAccountInfo = clientService.create(customerData.getFirstName(), customerData.getLastName(),
                customerData.getFatherName(), customerData.getPin());
        return ResponseEntity.ok(bankAccountInfo);
    }


    @PostMapping("/balance")
    public ResponseEntity<StatementBalance> statementBalance(@RequestBody CustomerData customerData) throws BusinessException {
        StatementBalance statementBalance = clientService.info(customerData.getFirstName(), customerData.getLastName(),
                customerData.getFatherName(), customerData.getPin());
        return ResponseEntity.ok(statementBalance);
    }

    @RequestMapping("/history")
    public ResponseEntity<BankingHistory>  history(@RequestBody AccountData accountData) throws BusinessException {
        BankingHistory bankingHistory = clientService.history(accountData.getFirstName(), accountData.getLastName(),
                accountData.getFatherName(), accountData.getPin(), accountData.getAccountNumber());
        return ResponseEntity.ok(bankingHistory);
    }

    @PostMapping("/deposit")
    public ResponseEntity<ResponseTransaction> deposit(@RequestBody Deposit deposit) throws BusinessException {
        ResponseTransaction responseTransaction = clientService.deposit(deposit.getFirstName(), deposit.getLastName(),
                deposit.getFatherName(), deposit.getPin(), deposit.getAccountNumberTo(),
                deposit.getCount());
        return ResponseEntity.ok(responseTransaction);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ResponseTransaction> withdraw(@RequestBody Withdraw withdraw) throws BusinessException {
        ResponseTransaction responseTransaction = clientService.withdraw(withdraw.getFirstName(), withdraw.getLastName(),
                withdraw.getFatherName(), withdraw.getPin(), withdraw.getAccountNumberFrom(), withdraw.getCount());
        return ResponseEntity.ok(responseTransaction);
    }


    @PostMapping("/transfer")
    public ResponseEntity<ResponseTransaction> transfer(@RequestBody Transfer transfer) throws BusinessException {
        ResponseTransaction responseTransaction = clientService.transfer(transfer.getFirstName(), transfer.getLastName(),
                transfer.getFatherName(), transfer.getPin(), transfer.getAccountNumberFrom(), transfer.getAccountNumberTo(),
                transfer.getCount());
        return ResponseEntity.ok(responseTransaction);
    }

}

