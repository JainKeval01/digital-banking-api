package com.bank.digitalbanking.service;

import com.bank.digitalbanking.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepo repo;

    public boolean isUserRegistered(String name,String mpin){
        return repo.findByUsernameAndMpin(name,mpin).isPresent();
    }
}
