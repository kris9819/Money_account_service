package com.money_account_service.dtos.response;

import java.util.List;

public record TransactionsResponseDto(List<TransactionResponseDto> transactions) {
}
