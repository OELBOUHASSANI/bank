package com.bank.services.transaction;

import com.bank.dto.TransactionDto;
import com.bank.entities.Transaction;


public interface TransactionService {

   Transaction create(TransactionDto transactionDto);
}
