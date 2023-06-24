package com.money_account_service.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponseDto(String receiverName, String accountNumber, String title, LocalDate transactionDate,
                                     BigDecimal amount, BigDecimal balance) {
}
