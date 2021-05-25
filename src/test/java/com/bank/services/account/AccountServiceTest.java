package com.bank.services.account;

import com.bank.dao.imp.AccountDaoImp;
import com.bank.entities.Account;
import com.bank.exceptions.BankAccountNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks
    AccountServiceImp account;

    @Mock
    AccountDaoImp accountDao;

    @Test
    public void getAccount_should_throw_account_not_found_exception() {
        // GIVEN
        when(accountDao.getAccount(12L)).thenReturn(null);
        try {
            // WHEN
            this.account.getAccount(12L);
            fail();
        } catch (BankAccountNotFoundException exception) {
            // THEN
            assertThat(exception.getMessage()).isEqualTo("Bank account not found, accountId: 12");
        }
    }
    @Test
    public void getAccount_should_return_the_account_from_database() {
        // GIVEN
        Account account = Account.builder().accountId(12L).build();
        when(accountDao.getAccount(12L)).thenReturn(account);
        // WHEN
        Account result = this.account.getAccount(12L);
        // THEN
        assertThat(account).isEqualTo(result);
    }
}
