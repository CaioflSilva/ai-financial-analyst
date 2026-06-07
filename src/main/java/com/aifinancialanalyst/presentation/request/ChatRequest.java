package com.aifinancialanalyst.presentation.request;

import jakarta.validation.constraints.NotBlank;

public record ChatRequest(

        @NotBlank(message = "Message is required")
        String message
) {}