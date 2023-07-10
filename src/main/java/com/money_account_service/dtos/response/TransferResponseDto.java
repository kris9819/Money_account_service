package com.money_account_service.dtos.response;

import lombok.Builder;

import java.time.Instant;

@Builder
public record TransferResponseDto(String receiverName, String accountNumber, String title, Instant transferDate,
                                  Long amount) {
}
