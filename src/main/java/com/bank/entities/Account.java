package com.bank.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Builder
public class Account {

    private Long accountId;
    private BigDecimal totalBalance;
    private List<Transaction> transactions;
}