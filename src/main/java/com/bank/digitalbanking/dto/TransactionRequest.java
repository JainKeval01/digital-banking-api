package com.bank.digitalbanking.dto;

import java.math.BigDecimal;

public record TransactionRequest(String username, BigDecimal amount) {}
