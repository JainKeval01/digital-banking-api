package com.bank.digitalbanking.controller;


import com.bank.digitalbanking.model.User;
import com.bank.digitalbanking.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping("/login")
    public String showUser(@RequestParam String name,@RequestParam String mpin){
        if(service.isUserRegistered(name,mpin)){
            return "Login Succesfull";
        }else {
            return "Login falied";
        }
    }
}
