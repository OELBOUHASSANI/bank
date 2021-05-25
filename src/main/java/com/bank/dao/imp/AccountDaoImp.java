package com.bank.dao.imp;



import com.bank.dao.AccountDao;
import com.bank.entities.Account;
import com.bank.dao.Database;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImp implements AccountDao {

    public Account getAccount(Long accountId){
       return Database.accounts.get(accountId);
    }

}

