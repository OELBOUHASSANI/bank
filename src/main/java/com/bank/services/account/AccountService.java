package com.bank.services.account;

import com.bank.entities.Account;
import com.bank.exceptions.BankAccountNotFoundException;


public interface AccountService {

   Account getAccount(Long accountId) throws BankAccountNotFoundException;
}
