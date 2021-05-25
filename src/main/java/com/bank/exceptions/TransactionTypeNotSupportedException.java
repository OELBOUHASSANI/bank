package com.bank.exceptions;

public class TransactionTypeNotSupportedException extends RuntimeException {
    public TransactionTypeNotSupportedException(String message) {
        super(message);
    }


}
