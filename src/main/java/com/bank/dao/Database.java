
package com.bank.dao;

import com.bank.entities.Account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Database {

    public static final Map<Long, Account> accounts = new HashMap();
    public static int transactionSequence = 0;

    static {
        accounts.put(1L, Account.builder()
                .accountId(1L)
                .totalBalance(BigDecimal.valueOf(0))
                .transactions(new ArrayList<>())
                .build());
        accounts.put(2L, Account.builder()
                .accountId(2L)
                .totalBalance(BigDecimal.valueOf(300))
                .transactions(new ArrayList<>())
                .build());

    }


}