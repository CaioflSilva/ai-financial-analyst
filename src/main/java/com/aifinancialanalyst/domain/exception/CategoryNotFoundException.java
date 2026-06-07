package com.aifinancialanalyst.domain.exception;

public class CategoryNotFoundException extends BusinessException {

    public CategoryNotFoundException(String message) {
        super(message);
    }
}