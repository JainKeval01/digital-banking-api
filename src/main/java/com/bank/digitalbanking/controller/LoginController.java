package com.bank.digitalbanking.controller;


import com.bank.digitalbanking.dto.LoginRequest;
import com.bank.digitalbanking.dto.SignupRequest;
import com.bank.digitalbanking.dto.TransactionRequest;
import com.bank.digitalbanking.dto.TransferRequest;
import com.bank.digitalbanking.model.Transactions;
import com.bank.digitalbanking.model.User;
import com.bank.digitalbanking.service.LoginService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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
        String msg=service.withDraw(request.username(),request.amount());
        return ResponseEntity.ok( msg);
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> tranfer(@RequestBody TransferRequest request){
        String msg=service.transfer(request.fromUser(),request.toUser(),request.amount());
        return ResponseEntity.ok(msg);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignupRequest request){
        String msg=service.signUp(request.name(),request.mpin());

        if(msg.equals("ok")) {
            return ResponseEntity.ok("Registration Successful");
        }else{
            return ResponseEntity.ok("User Exists");
        }
    }

    @GetMapping("/transactions/{username}")
    public List<Transactions> transactionsList(@PathVariable String username) {
        return service.getTransactionsList(username);
    }
}
