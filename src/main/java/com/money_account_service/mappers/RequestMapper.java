package com.money_account_service.mappers;

import com.money_account_service.dtos.request.CreateAccountRequestDto;
import com.money_account_service.dtos.request.TopUpRequestDto;
import com.money_account_service.dtos.request.TransferRequestDto;
import com.money_account_service.entities.AccountEntity;
import com.money_account_service.entities.TopUpEntity;
import com.money_account_service.entities.TransferEntity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class RequestMapper {

    public static AccountEntity accountRequestToAccountEntity(CreateAccountRequestDto createAccountRequestDto) {
        return AccountEntity.builder()
                .accountNumber(UUID.randomUUID().toString())
                .balance(new BigDecimal(0.0))
                .currency(createAccountRequestDto.currency())
                .userId(createAccountRequestDto.accessToken())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    public static TopUpEntity topUpRequestToTopUpEntity(TopUpRequestDto topUpRequestDto) {
        return TopUpEntity.builder()
                .balance(topUpRequestDto.balance())
                .currency(topUpRequestDto.currency())
                .exchangeRate(new BigDecimal(1.0))
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
                .transactionDate(Instant.now())
                .idempotencyKey(transferRequestDto.idempotencyKey())
                .ledgerEntryId(123L)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }
}
//TODO: Czy powinienem robić kilka mapperów, i czy takie mapowanie jest git
// Czy accountId powinno być dostarczane z frontu?
// Jak z exchangeRate, jak to dostarczać?
// Czy nie lepszy będzie model entity-model-dto