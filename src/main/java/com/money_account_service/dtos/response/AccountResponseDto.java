package com.money_account_service.dtos.response;

import lombok.Builder;

@Builder
public record AccountResponseDto(String accountNumber, Long balance, String currency) {
}
