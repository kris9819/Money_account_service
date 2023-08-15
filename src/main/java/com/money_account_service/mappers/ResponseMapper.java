package com.money_account_service.mappers;

import com.money_account_service.dtos.response.AccountDetailsResponseDto;
import com.money_account_service.dtos.response.CreateAccountResponseDto;
import com.money_account_service.dtos.response.TopUpResponseDto;
import com.money_account_service.dtos.response.TransferResponseDto;
import com.money_account_service.entities.AccountEntity;
import com.money_account_service.entities.TransferEntity;

//TODO: Add balance calculation and account numbers when ledger is ready
public class ResponseMapper {

    public static AccountDetailsResponseDto accountEntityToAccountDetailsResponse(AccountEntity accountEntity) {
        return AccountDetailsResponseDto.builder()
                .accountNumber(accountEntity.getAccountNumber())
                .currency(accountEntity.getCurrency())
                .balance(0L)
                .build();
    }

    public static CreateAccountResponseDto accountEntityToCreateAccountResponse(AccountEntity accountEntity) {
        return CreateAccountResponseDto.builder()
                .accountNumber(accountEntity.getAccountNumber())
                .balance(0L)
                .currency(accountEntity.getCurrency())
                .build();
    }

    public static TopUpResponseDto transferEntityToTopUpResponse(TransferEntity transferEntity) {
        return TopUpResponseDto.builder()
                .balance(0L)
                .build();
    }

    public static TransferResponseDto transferEntityToTransferResponse(TransferEntity transferEntity) {
        return TransferResponseDto.builder()
                .title(transferEntity.getTitle())
                .amount(0L)
                .receiverName("???")
                .accountNumber("???")
                .transferDate(transferEntity.getTransferDate())
                .build();
    }
}
