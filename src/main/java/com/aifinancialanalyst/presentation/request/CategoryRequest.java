package com.aifinancialanalyst.presentation.request;

import com.aifinancialanalyst.domain.model.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(

        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Type is required")
        CategoryType type
) {}