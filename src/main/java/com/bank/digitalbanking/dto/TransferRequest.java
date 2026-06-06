package com.bank.digitalbanking.dto;

import java.math.BigDecimal;

public record TransferRequest(String fromUser, String toUser, BigDecimal amount) { }
