package com.bank.services.operatiomanagement;

import com.bank.dto.AccountHistoryDto;
import com.bank.dto.TransactionDto;
import com.bank.entities.Account;
import com.bank.entities.Transaction;
import com.bank.enumerated.TransactionTypeEnum;
import com.bank.exceptions.BalanceNotSufficientException;
import com.bank.exceptions.BankAccountNotFoundException;
import com.bank.services.account.AccountServiceImp;
import com.bank.services.operationmanagement.OperationManagementServiceImp;
import com.bank.services.transaction.TransactionServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OperationManagementServiceTest {

    @InjectMocks
    OperationManagementServiceImp operationManagementService;

    @Mock
    AccountServiceImp accountService;

    @Mock
    TransactionServiceImp transactionService;

    // deposit
    @Test
    public void should_deposit_the_amount_of_money_in_the_account() throws BankAccountNotFoundException {
        // GIVEN
        TransactionDto transactionDto = buildTransactionDto(BigDecimal.valueOf(100), TransactionTypeEnum.DEPOSIT);
        Account account = buildAccount();
        when(accountService.getAccount(transactionDto.getAccountId())).thenReturn(account);
        when(transactionService.create(transactionDto)).thenCallRealMethod();
        // WHEN
        Transaction transaction = this.operationManagementService.deposit(transactionDto);
        // THEN
        assertThat(transaction.getTransactionType()).isEqualTo(TransactionTypeEnum.DEPOSIT);
        assertThat(transaction.getAmount()).isEqualTo(BigDecimal.valueOf(100));
        assertThat(account.getTotalBalance()).isEqualTo(BigDecimal.valueOf(300));
        assertThat(account.getTransactions().size()).isEqualTo(1);
        assertThat(account.getTransactions().get(0)).isEqualTo(transaction);

    }

    // withdraw
    @Test
    public void should_withdraw_amount_from_the_account_balance() throws BankAccountNotFoundException, BalanceNotSufficientException {
        // GIVEN
        TransactionDto transactionDto = buildTransactionDto(BigDecimal.valueOf(50),  TransactionTypeEnum.WITHDRAW);
        Account account = buildAccount();
        when(accountService.getAccount(transactionDto.getAccountId())).thenReturn(account);
        when(transactionService.create(transactionDto)).thenCallRealMethod();

        // WHEN
        Transaction transaction =  this.operationManagementService.withdraw(transactionDto);
        // THEN
        assertThat(transaction.getTransactionType()).isEqualTo(TransactionTypeEnum.WITHDRAW);
        assertThat(transaction.getAmount()).isEqualTo(BigDecimal.valueOf(50));
        assertThat(account.getTotalBalance()).isEqualTo(BigDecimal.valueOf(150));
        assertThat(account.getTransactions().size()).isEqualTo(1);
        assertThat(account.getTransactions().get(0)).isEqualTo(transaction);
    }

    @Test
    public void withdraw_should_throw_balance_not_sufficient_exception() throws BankAccountNotFoundException {
        // GIVEN
        TransactionDto transactionDto = buildTransactionDto(BigDecimal.valueOf(300),  TransactionTypeEnum.WITHDRAW);
        Account account = buildAccount();
        when(accountService.getAccount(transactionDto.getAccountId())).thenReturn(account);

        try {
            this.operationManagementService.withdraw(transactionDto);
            fail();

        } catch (BalanceNotSufficientException exception) {
            assertThat(exception.getMessage()).isEqualTo("Balance is not sufficient, accountId: 123");
        }
    }

    // getTransactionHistory
    @Test
    public void should_return_the_transaction_history_for_an_account() throws BankAccountNotFoundException {
        // GIVEN
        Account account = buildAccount();
        Transaction transaction1 = buildTransaction(BigDecimal.valueOf(10), TransactionTypeEnum.DEPOSIT);
        Transaction transaction2 = buildTransaction(BigDecimal.valueOf(99), TransactionTypeEnum.WITHDRAW);
        account.setTransactions(Arrays.asList(transaction1, transaction2));
        when(accountService.getAccount(account.getAccountId())).thenReturn(account);
        // WHEN
        AccountHistoryDto accountHistoryDto =  this.operationManagementService.getTransactionHistory(123L);
        // THEN
        assertThat(accountHistoryDto.getAccountId()).isEqualTo(123);
        assertThat(accountHistoryDto.getBalance()).isEqualTo(BigDecimal.valueOf(200));
        assertThat(accountHistoryDto.getTransactions().size()).isEqualTo(2);
        assertThat(accountHistoryDto.getTransactions().get(0).getTransactionType()).isEqualTo(TransactionTypeEnum.DEPOSIT);
        assertThat(accountHistoryDto.getTransactions().get(0).getAmount()).isEqualTo(BigDecimal.valueOf(10));
        assertThat(accountHistoryDto.getTransactions().get(1).getTransactionType()).isEqualTo(TransactionTypeEnum.WITHDRAW);
        assertThat(accountHistoryDto.getTransactions().get(1).getAmount()).isEqualTo(BigDecimal.valueOf(99));


    }

    private Transaction buildTransaction(BigDecimal amount, TransactionTypeEnum type) {
        return Transaction.builder()
                .transactionId(1)
                .amount(amount)
                .transactionType(type).build();
    }

    private TransactionDto buildTransactionDto(BigDecimal amount,  TransactionTypeEnum type) {
        return TransactionDto.builder()
                .accountId(123L)
                .amount(amount)
                .transactionType(type)
                .build();
    }

    private Account buildAccount() {
        return Account.builder()
                .accountId(123L)
                .transactions(new ArrayList<>())
                .totalBalance(BigDecimal.valueOf(200))
                .build();
    }
}
