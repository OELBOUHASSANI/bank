package com.bank.services.transaction;


import com.bank.dao.Database;
import com.bank.dto.TransactionDto;
import com.bank.entities.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionServiceImp implements TransactionService {

   @Override
   public Transaction create(TransactionDto transactionDto)  {
      return Transaction.builder()
              .transactionId(++Database.transactionSequence)
              .transactionType(transactionDto.getTransactionType())
              .transactionDate(LocalDateTime.now())
              .amount(transactionDto.getAmount())
              .build();
   }
}
