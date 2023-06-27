package com.money_account_service.dtos.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record TransferResponseDto(String receiverName, String accountNumber, String title, LocalDate transactionDate,
                                  BigDecimal amount) {
}
