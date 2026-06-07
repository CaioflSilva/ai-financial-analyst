package com.aifinancialanalyst.domain.exception;

public class TransactionNotFoundException extends BusinessException {

    public TransactionNotFoundException(String message) {
        super(message);
    }
}