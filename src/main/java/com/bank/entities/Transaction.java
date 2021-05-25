package com.bank.entities;


import com.bank.enumerated.TransactionTypeEnum;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private int transactionId;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private TransactionTypeEnum transactionType;
}
