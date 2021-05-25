package com.bank.services.account;

import com.bank.dao.AccountDao;
import com.bank.entities.Account;
import com.bank.exceptions.BankAccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImp implements  AccountService{

    private final AccountDao accountDao;

    @Autowired
    public AccountServiceImp(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account getAccount(Long accountId) throws BankAccountNotFoundException {
        Account account = accountDao.getAccount(accountId);
        if (account == null) {
            throw new BankAccountNotFoundException("Bank account not found, accountId: " + accountId);
        }
        return account;
    }

}
