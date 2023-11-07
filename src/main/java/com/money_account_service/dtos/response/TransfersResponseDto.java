package com.money_account_service.dtos.response;

import com.money_account_service.entities.TransferEntity;
import lombok.Builder;

import java.util.List;

@Builder
public record TransfersResponseDto(List<TransferEntity> transfers) {
}
