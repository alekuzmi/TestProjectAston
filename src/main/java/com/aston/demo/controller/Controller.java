package com.aston.demo.controller;


import com.aston.demo.exception.BusinessException;
import com.aston.demo.model.Request.Request;
import com.aston.demo.model.Request.Transaction;
import com.aston.demo.model.Response.ResponseCreate;
import com.aston.demo.model.Response.Info;
import com.aston.demo.model.Response.ResponseTransaction;
import com.aston.demo.service.ServiceImpl;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class Controller {
    private final ServiceImpl clientService;

    @Autowired
    public Controller(ServiceImpl clientService) {
        this.clientService = clientService;
    }

//    @ExceptionHandler(BusinessException.class)
//    public Response handleException(BusinessException e) {
//        return new Response(e.getMessage());
//    }

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseCreate> create(@RequestBody Request request) throws Exception {
        ResponseCreate responseCreate = clientService.create(request.getFirstName(), request.getLastName(),
                request.getFatherName(), request.getPin());
        return ResponseEntity.ok(responseCreate);
    }


    @RequestMapping("/info")
    public ResponseEntity<Info> balance(@RequestBody Request request) throws BusinessException {
        Info responseInfo = clientService.info(request.getFirstName(), request.getLastName(),
                request.getFatherName(), request.getPin());
        return ResponseEntity.ok(responseInfo);
    }

    @RequestMapping("/history")
    public String history(@RequestBody Request request) {
        return "history";
    }

    @RequestMapping("/deposit")
    public ResponseEntity<ResponseTransaction> deposit(@RequestBody Transaction transaction) throws BusinessException {
        ResponseTransaction responseTransaction = clientService.deposit(transaction.getFirstName(), transaction.getLastName(),
                transaction.getFatherName(), transaction.getPin(), transaction.getAccountNumberFrom(), transaction.getAccountNumberTo(),
                transaction.getCount());
        return ResponseEntity.ok(responseTransaction);
    }

    @RequestMapping("/withdraw")
    public ResponseEntity<ResponseTransaction> withdraw(@RequestBody Transaction transaction) throws BusinessException {
        ResponseTransaction responseTransaction = clientService.withdraw(transaction.getFirstName(), transaction.getLastName(),
                transaction.getFatherName(), transaction.getPin(), transaction.getAccountNumberFrom(), transaction.getAccountNumberTo(),
                transaction.getCount());
        return ResponseEntity.ok(responseTransaction);
    }


    @RequestMapping("/transfer")
    public ResponseEntity<ResponseTransaction> transfer(@RequestBody Transaction transaction) throws BusinessException {
        ResponseTransaction responseTransaction = clientService.transfer(transaction.getFirstName(), transaction.getLastName(),
                transaction.getFatherName(), transaction.getPin(), transaction.getAccountNumberFrom(), transaction.getAccountNumberTo(),
                transaction.getCount());
        return ResponseEntity.ok(responseTransaction);
    }

}

