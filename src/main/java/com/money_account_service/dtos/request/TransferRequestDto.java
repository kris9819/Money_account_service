package com.money_account_service.dtos.request;

import java.math.BigDecimal;

public record TransferRequestDto(String receiverName, String accountNumber, String title, BigDecimal balance,
                                 String idempotencyKey, String accessToken) {
}
