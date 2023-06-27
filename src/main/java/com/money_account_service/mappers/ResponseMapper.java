package com.money_account_service.mappers;

import com.money_account_service.dtos.response.AccountDetailsResponseDto;
import com.money_account_service.dtos.response.CreateAccountResponseDto;
import com.money_account_service.dtos.response.TopUpResponseDto;
import com.money_account_service.dtos.response.TransferResponseDto;
import com.money_account_service.entities.AccountEntity;
import com.money_account_service.entities.TopUpEntity;
import com.money_account_service.entities.TransferEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class ResponseMapper {

    public static AccountDetailsResponseDto accountEntityToAccountDetailsResponse(AccountEntity accountEntity) {
        return AccountDetailsResponseDto.builder()
                .accountNumber(accountEntity.getAccountNumber())
                .currency(accountEntity.getCurrency())
                .accountNumber(accountEntity.getAccountNumber())
                .build();
    }

    public static CreateAccountResponseDto accountEntityToCreateAccountResponse(AccountEntity accountEntity) {
        return CreateAccountResponseDto.builder()
                .accountNumber(accountEntity.getAccountNumber())
                .balance(accountEntity.getBalance())
                .currency(accountEntity.getCurrency())
                .build();
    }

    public static TopUpResponseDto topUpEntityToTopUpResponse(TopUpEntity topUpEntity) {
        return TopUpResponseDto.builder()
                .balance(topUpEntity.getBalance())
                .build();
    }

    public static TransferResponseDto transferEntityToTransferResponse(TransferEntity transferEntity) {
        return TransferResponseDto.builder()
                .title(transferEntity.getTitle())
                .amount(new BigDecimal(0.0))
                .receiverName("???")
                .accountNumber("???")
                .transactionDate(LocalDate.ofInstant(transferEntity.getTransactionDate(), ZoneOffset.UTC))
                .build();
    }
}

//TODO: jak ogarnąć kwote transakcji?
