package com.aston.demo.controller;


import com.aston.demo.model.*;
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
    public ResponseEntity<ResponseInfo> balance(@RequestBody Request request) {
        ResponseInfo responseInfo = clientService.info(request);
        return ResponseEntity.ok(responseInfo);
    }

    @RequestMapping("/history")
    public String history(@RequestBody Request request) {
        return "history";
    }

    @RequestMapping("/deposit")
    public ResponseEntity<ResponseTransaction> deposit(@RequestBody RequestTransaction requestTransaction) {
        ResponseTransaction responseTransaction = clientService.deposit(requestTransaction);
        return ResponseEntity.ok(responseTransaction);
    }

    @RequestMapping("/withdraw")
    public ResponseEntity<ResponseTransaction> withdraw(@RequestBody RequestTransaction requestTransaction) {
        ResponseTransaction responseTransaction = clientService.withdraw(requestTransaction);
        return ResponseEntity.ok(responseTransaction);
    }


    @RequestMapping("/transfer")
    public ResponseEntity<ResponseTransaction> transfer(@RequestBody RequestTransaction requestTransaction) {
        ResponseTransaction responseTransaction = clientService.transfer(requestTransaction);
        return ResponseEntity.ok(responseTransaction);
    }

}

