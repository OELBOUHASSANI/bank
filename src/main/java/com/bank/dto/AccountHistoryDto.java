package com.bank.dto;

import com.bank.entities.Transaction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Builder
public class AccountHistoryDto {
    List<Transaction> transactions;
    Long accountId;
    BigDecimal balance;
}
