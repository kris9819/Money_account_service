package com.money_account_service.mappers;

import com.money_account_service.dtos.response.AccountResponseDto;
import com.money_account_service.entities.AccountEntity;

public class AccountMapper {

    public static AccountResponseDto map(AccountEntity accountEntity) {
        return AccountResponseDto.builder()
                .accountNumber(accountEntity.getAccountNumber())
                .currency(accountEntity.getCurrency())
                .balance(0L)
                .build();
    }
}
