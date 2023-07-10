package com.money_account_service.dtos.request;

public record TransferRequestDto(String receiverName, String accountNumber, String title, Long balance,
                                 String idempotencyKey) {
}
