package com.money_account_service.mappers;

import com.money_account_service.dtos.response.TopUpResponseDto;
import com.money_account_service.entities.TransferEntity;

public class TopUpMapper {

    public static TopUpResponseDto map(TransferEntity transferEntity) {
        return TopUpResponseDto.builder()
                .balance(0L)
                .build();
    }
}
