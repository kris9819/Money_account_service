package com.money_account_service.dtos.response;

import lombok.Builder;

@Builder
public record CreateAccountResponseDto(String accountNumber, Long balance, String currency) {
}
