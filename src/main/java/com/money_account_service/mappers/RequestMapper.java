package com.money_account_service.mappers;

import com.money_account_service.dtos.request.CreateAccountRequestDto;
import com.money_account_service.dtos.request.TopUpRequestDto;
import com.money_account_service.dtos.request.TransferRequestDto;
import com.money_account_service.entities.AccountEntity;
import com.money_account_service.entities.TopUpEntity;
import com.money_account_service.entities.TransferEntity;

import java.time.Instant;
import java.util.UUID;

public class RequestMapper {

    public static AccountEntity accountRequestToAccountEntity(CreateAccountRequestDto createAccountRequestDto) {
        return AccountEntity.builder()
                .accountNumber(UUID.randomUUID().toString())
                .currency(createAccountRequestDto.currency())
//                .userSub(createAccountRequestDto.accessToken())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    public static TopUpEntity topUpRequestToTopUpEntity(TopUpRequestDto topUpRequestDto) {
        return TopUpEntity.builder()
                .balance(topUpRequestDto.balance())
                .currency(topUpRequestDto.currency())
//                .exchangeRate(new Long(1.0))
                .actionDate(Instant.now())
                .idempotencyKey(topUpRequestDto.idempotencyKey())
                .accountId("???")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    public static TransferEntity transferRequestToTransferEntity(TransferRequestDto transferRequestDto) {
        return TransferEntity.builder()
                .title(transferRequestDto.title())
                .transferDate(Instant.now())
                .idempotencyKey(transferRequestDto.idempotencyKey())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }
}