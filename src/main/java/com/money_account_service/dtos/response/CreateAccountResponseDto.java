package com.money_account_service.dtos.response;

import java.math.BigDecimal;

public record CreateAccountResponseDto(String accountNumber, BigDecimal balance, String currency) {
}
