package com.aifinancialanalyst.presentation.response;

import java.math.BigDecimal;

public record DashboardResponse(
        BigDecimal totalIncome,
        BigDecimal totalExpense,
        BigDecimal balance,
        long transactionCount
) {}