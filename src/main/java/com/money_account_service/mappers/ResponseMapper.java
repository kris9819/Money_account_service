package com.money_account_service.mappers;

import com.money_account_service.dtos.response.AccountDetailsResponseDto;
import com.money_account_service.dtos.response.CreateAccountResponseDto;
import com.money_account_service.dtos.response.TransferResponseDto;
import com.money_account_service.entities.AccountEntity;
import com.money_account_service.entities.TransferEntity;

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
//                .balance(accountEntity.getBalance())
                .currency(accountEntity.getCurrency())
                .build();
    }

//    public static TopUpResponseDto topUpEntityToTopUpResponse(TopUpEntity topUpEntity) {
//        return TopUpResponseDto.builder()
//                .balance(topUpEntity.getBalance())
//                .build();
//    }

    public static TransferResponseDto transferEntityToTransferResponse(TransferEntity transferEntity) {
        return TransferResponseDto.builder()
                .title(transferEntity.getTitle())
//                .amount()
                .receiverName("???")
                .accountNumber("???")
//                .TransferDate(LocalDate.ofInstant(transferEntity.getTransferDate(), ZoneOffset.UTC))
                .build();
    }
}
