package com.money_account_service.dtos.response;

import lombok.Builder;

import java.util.List;

@Builder
public record TransfersResponseDto(List<TransferResponseDto> transfers) {
}
