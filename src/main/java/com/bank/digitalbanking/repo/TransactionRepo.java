package com.bank.digitalbanking.repo;

import com.bank.digitalbanking.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transactions,Integer> {
    List<Transactions> findByUsernameOrderByTimestampDesc(String username);
}
