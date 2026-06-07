package com.aifinancialanalyst.presentation.response;

import com.aifinancialanalyst.domain.model.Category;
import com.aifinancialanalyst.domain.model.CategoryType;

import java.util.UUID;

public record CategoryResponse(
        UUID id,
        String name,
        CategoryType type
) {
    public static CategoryResponse from(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getType()
        );
    }
}