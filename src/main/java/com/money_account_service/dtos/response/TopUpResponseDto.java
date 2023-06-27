package com.money_account_service.dtos.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TopUpResponseDto(BigDecimal balance) {
}
