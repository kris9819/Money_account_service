package com.money_account_service.mappers;

import com.money_account_service.dtos.response.TransferResponseDto;
import com.money_account_service.dtos.response.TransfersResponseDto;
import com.money_account_service.entities.TransferEntity;

import java.util.List;

public class TransferMapper {

    public static TransferResponseDto map(TransferEntity transferEntity) {
        return TransferResponseDto.builder()
                .title(transferEntity.getTitle())
                .amount(0L)
                .receiverName("???")
                .accountNumber("???")
                .transferDate(transferEntity.getTransferDate())
                .build();
    }

    public static TransfersResponseDto map(List<TransferEntity> transferEntityList) {
        return TransfersResponseDto.builder()
                .transfers(transferEntityList)
                .build();
    }
}
