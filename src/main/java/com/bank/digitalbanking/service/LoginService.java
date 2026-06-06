package com.bank.digitalbanking.service;

import com.bank.digitalbanking.model.User;
import com.bank.digitalbanking.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LoginService {

    @Autowired
    private UserRepo repo;

    public boolean isUserRegistered(String name,String mpin){
        return repo.findByUsernameAndMpin(name,mpin).isPresent();
    }

    public void depositAmount(String name,BigDecimal amount) {
        User user=repo.findByUsername(name).orElse(new User());
        user.setBalance(user.getBalance().add(amount));
        repo.save(user);
    }

    public void withDraw(String name,BigDecimal amount){
      User user=repo.findByUsername(name).orElse(new User());
      user.setBalance(user.getBalance().subtract(amount));
      repo.save(user);
    }

    public BigDecimal getBalance(String username) {
        User user=repo.findByUsername(username).orElse(new User());
        return user.getBalance();

    }
}
