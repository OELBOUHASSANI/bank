package com.bank.services.operationmanagement;

import com.bank.dto.AccountHistoryDto;
import com.bank.dto.TransactionDto;
import com.bank.entities.Transaction;
import com.bank.exceptions.BalanceNotSufficientException;
import com.bank.exceptions.BankAccountNotFoundException;

public interface OperationManagementService {

    Transaction executeOperation(TransactionDto transactionDto) throws BankAccountNotFoundException, BalanceNotSufficientException;

    AccountHistoryDto getTransactionHistory(Long accountId) throws BankAccountNotFoundException;
}
