package com.charter.rewards.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class TransactionRequest {
    private long customerId;
    private int transactionAmount;
    private LocalDate transactionDate;
}
