package com.bank.digitalbanking.repo;

import com.bank.digitalbanking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByUsernameAndMpin(String name,String mpin);
}
