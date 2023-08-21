package com.aston.demo.controller;


import com.aston.demo.model.Request.Request;
import com.aston.demo.model.Request.Transaction;
import com.aston.demo.model.Response.ResponseCreate;
import com.aston.demo.model.Response.Info;
import com.aston.demo.model.Response.ResponseTransaction;
import com.aston.demo.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final ServiceImpl clientService;

    @Autowired
    public Controller(ServiceImpl clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseCreate> create(@RequestBody Request request) throws Exception {
        ResponseCreate responseCreate = clientService.create(request);
        return ResponseEntity.ok(responseCreate);
    }


    @RequestMapping("/info")
    public ResponseEntity<Info> balance(@RequestBody Request request) {
        Info responseInfo = clientService.info(request);
        return ResponseEntity.ok(responseInfo);
    }

    @RequestMapping("/history")
    public String history(@RequestBody Request request) {
        return "history";
    }

    @RequestMapping("/deposit")
    public ResponseEntity<ResponseTransaction> deposit(@RequestBody Transaction transaction) {
        ResponseTransaction responseTransaction = clientService.deposit(transaction);
        return ResponseEntity.ok(responseTransaction);
    }

    @RequestMapping("/withdraw")
    public ResponseEntity<ResponseTransaction> withdraw(@RequestBody Transaction transaction) {
        ResponseTransaction responseTransaction = clientService.withdraw(transaction);
        return ResponseEntity.ok(responseTransaction);
    }


    @RequestMapping("/transfer")
    public ResponseEntity<ResponseTransaction> transfer(@RequestBody Transaction transaction) {
        ResponseTransaction responseTransaction = clientService.transfer(transaction);
        return ResponseEntity.ok(responseTransaction);
    }

}

