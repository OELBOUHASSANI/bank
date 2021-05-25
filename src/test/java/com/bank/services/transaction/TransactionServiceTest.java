package com.bank.services.transaction;

import com.bank.dto.TransactionDto;
import com.bank.entities.Transaction;
import com.bank.enumerated.TransactionTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @InjectMocks
    TransactionServiceImp transactionService;

    @Test
    public void should_create_transaction() {
        // GIVEN
        TransactionDto transactionDto = TransactionDto.builder()
                .accountId(1L)
                .amount(BigDecimal.valueOf(10))
                .transactionType(TransactionTypeEnum.DEPOSIT)
                .build();
        // WHEN
        Transaction transaction = this.transactionService.create(transactionDto);

        // THEN
        assertThat(transaction.getAmount()).isEqualTo(BigDecimal.valueOf(10));
        assertThat(transaction.getTransactionType()).isEqualTo(TransactionTypeEnum.DEPOSIT);


    }
}
