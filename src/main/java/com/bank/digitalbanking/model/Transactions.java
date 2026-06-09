package com.bank.digitalbanking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    private String username;
    private String transactionType;
    private BigDecimal amount;
    private String description;
    private LocalDateTime timestamp;

    @PrePersist
    protected void onCreate(){
        this.timestamp=LocalDateTime.now();
    }


}
