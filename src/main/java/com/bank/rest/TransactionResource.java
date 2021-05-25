package com.bank.rest;

import com.bank.dto.AccountHistoryDto;
import com.bank.dto.TransactionDto;
import com.bank.entities.Transaction;
import com.bank.exceptions.BankAccountNotFoundException;
import com.bank.services.operationmanagement.OperationManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/rest")
@Validated
public class TransactionResource {

    private final OperationManagementService operationManagementService;

    @Autowired
    public TransactionResource(OperationManagementService operationManagementService) {
        this.operationManagementService = operationManagementService;
    }

    @PostMapping(value = "/operation")
    public Transaction executeOperation(@Valid @RequestBody() TransactionDto transactionDto) throws  BankAccountNotFoundException {
            return this.operationManagementService.executeOperation(transactionDto);
    }

    @GetMapping(value = "/history/{accountId}")
    public AccountHistoryDto history(@PathVariable(value = "accountId") @NotNull Long accountId) throws  BankAccountNotFoundException {
        return this.operationManagementService.getTransactionHistory(accountId);
    }

}
