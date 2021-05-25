package com.bank.dao;
import com.bank.entities.Account;

public interface AccountDao {
    Account getAccount(Long accountId);
}
