package com.bank.digitalbanking.controller;


import com.bank.digitalbanking.dto.LoginRequest;
import com.bank.digitalbanking.dto.TransactionRequest;
import com.bank.digitalbanking.model.User;
import com.bank.digitalbanking.service.LoginService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/bank")
public class LoginController {

    @Autowired
    private LoginService service;

    @GetMapping("/balance/{username}")
    public ResponseEntity<String> showBalance(@PathVariable String username){
        try{
            BigDecimal balance=service.getBalance(username);
            return ResponseEntity.ok(balance.toString());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }
    @PostMapping("/login")
    public String showUser(@RequestBody LoginRequest request){
        if(service.isUserRegistered(request.username(), request.mpin())){
            return "Login successful";
        }else {
            return "Login failed";
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> depositAmount(@RequestBody TransactionRequest request){
        service.depositAmount(request.username(),request.amount());
        return ResponseEntity.ok( "Deposited..");
    }
    @PostMapping("/withdraw")
    public ResponseEntity<String> withDraw(@RequestBody TransactionRequest request){
        service.withDraw(request.username(),request.amount());
        return ResponseEntity.ok( "Withdrawn..");
    }
}
