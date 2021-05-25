package com.bank.services.operationmanagement;


import com.bank.dto.AccountHistoryDto;
import com.bank.dto.TransactionDto;
import com.bank.entities.Account;
import com.bank.entities.Transaction;
import com.bank.exceptions.BalanceNotSufficientException;
import com.bank.exceptions.BankAccountNotFoundException;
import com.bank.exceptions.TransactionTypeNotSupportedException;
import com.bank.services.account.AccountService;
import com.bank.services.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.bank.enumerated.TransactionTypeEnum.*;

@Service
public class OperationManagementServiceImp implements OperationManagementService {

    private final AccountService accountService;
    private final TransactionService transactionService;

    @Autowired
    public OperationManagementServiceImp(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    public Transaction executeOperation(TransactionDto transactionDto) {
        switch (transactionDto.getTransactionType()) {
            case DEPOSIT:
                return this.deposit(transactionDto);
            case WITHDRAW:
                return this.withdraw(transactionDto);
            default:
                throw new TransactionTypeNotSupportedException(transactionDto.getTransactionType() + " transaction type  not supported");
        }

    }

    public Transaction deposit(TransactionDto transactionDto) throws BankAccountNotFoundException {

        Account account = this.accountService.getAccount(transactionDto.getAccountId());
        Transaction transaction = this.transactionService.create(transactionDto);
        account.getTransactions().add(transaction);
        account.setTotalBalance(account.getTotalBalance().add(transactionDto.getAmount()));
        return transaction;
    }


    public Transaction withdraw(TransactionDto transactionDto) throws BankAccountNotFoundException, BalanceNotSufficientException {

        Account account = this.accountService.getAccount(transactionDto.getAccountId());
        if (transactionDto.getAmount().compareTo(account.getTotalBalance()) > 0) {
            throw new BalanceNotSufficientException("Balance is not sufficient, accountId: "+ account.getAccountId());
        }
        Transaction transaction = this.transactionService.create(transactionDto);
        account.getTransactions().add(transaction);
        account.setTotalBalance(account.getTotalBalance().subtract(transactionDto.getAmount()));
        return transaction;


    }

    @Override
    public AccountHistoryDto getTransactionHistory(Long accountId) throws BankAccountNotFoundException {
        Account account = this.accountService.getAccount(accountId);

        return AccountHistoryDto.builder()
                .accountId(account.getAccountId())
                .balance(account.getTotalBalance())
                .transactions(account.getTransactions())
                .build();
    }


}
