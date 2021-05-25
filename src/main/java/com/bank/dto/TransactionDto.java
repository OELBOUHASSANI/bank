package com.bank.dto;

import com.bank.enumerated.TransactionTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class TransactionDto {

    @Positive(message = "amount should be positive")
    BigDecimal amount;

    @Positive(message = "accountId should not be Positive")
    Long accountId;

    @NotNull(message = "transactionType should not be null")
    TransactionTypeEnum transactionType;

    
}
