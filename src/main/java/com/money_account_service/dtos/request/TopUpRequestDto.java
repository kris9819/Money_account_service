package com.money_account_service.dtos.request;

public record TopUpRequestDto(Long balance, String currency, String idempotencyKey) {
}
