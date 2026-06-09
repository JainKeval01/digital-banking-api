package com.bank.digitalbanking.service;

import com.bank.digitalbanking.model.Transactions;
import com.bank.digitalbanking.model.User;
import com.bank.digitalbanking.repo.TransactionRepo;
import com.bank.digitalbanking.repo.UserRepo;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    private UserRepo repo;
    @Autowired
    private TransactionRepo transactionRepo;

    public boolean isUserRegistered(String name,String mpin){
        return repo.findByUsernameAndMpin(name,mpin).isPresent();
    }

    public void saveTransactions(String username,String transactionType,BigDecimal amount,String description){
        Transactions tran=new Transactions();
        tran.setAmount(amount);
        tran.setDescription(description);
        tran.setUsername(username);
        tran.setTransactionType(transactionType);
        transactionRepo.save(tran);
    }

    public void depositAmount(String name,BigDecimal amount) {
        User user=repo.findByUsername(name).get();
        user.setBalance(user.getBalance().add(amount));
        saveTransactions(name,"Deposit",amount,"Deposited "+amount);
        repo.save(user);
    }

    public String withDraw(String name,BigDecimal amount){
      User user=repo.findByUsername(name).get();
      if(user.getBalance().compareTo(amount)>=0) {
          user.setBalance(user.getBalance().subtract(amount));
          repo.save(user);
          saveTransactions(name,"Withdrawn",amount,"Withdrawn "+amount);
          return amount+"Withdrawn";
      }
      return "low balance";
    }

    public BigDecimal getBalance(String username) {
        User user=repo.findByUsername(username).orElse(new User());
        return user.getBalance();

    }

    public String transfer(String sender, String receiver, BigDecimal amount) {
        User fromUser=repo.findByUsername(sender).get();
        if(repo.findByUsername(receiver).isPresent()) {
            User toUser = repo.findByUsername(receiver).get();
            if (fromUser.getBalance().compareTo(amount)>=0) {
                toUser.setBalance(toUser.getBalance().add(amount));
                fromUser.setBalance(fromUser.getBalance().subtract(amount));
                repo.save(toUser);
                repo.save(fromUser);
                saveTransactions(sender,"Transfer",amount,"Transfered "+amount+" to "+ receiver);
                return amount + "transferred to " + receiver;
            } else {
                return "Low balance";
            }
        }else{
            return "No such User";
        }
    }

    public String signUp(String username,String mpin) {
        if (repo.findByUsername(username).isEmpty()) {
            User user = new User();
            user.setMpin(mpin);
            user.setUsername(username);
            user.setBalance(BigDecimal.ZERO);
            repo.save(user);
            return "ok";
        }else {
            return "not ok";
        }
    }


    public List<Transactions> getTransactionsList(String username) {
        return transactionRepo.findByUsernameOrderByTimestampDesc(username);
    }
}
