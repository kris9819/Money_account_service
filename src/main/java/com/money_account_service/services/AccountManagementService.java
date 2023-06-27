package com.money_account_service.services;


import com.money_account_service.dtos.request.CreateAccountRequestDto;
import com.money_account_service.dtos.request.TopUpRequestDto;
import com.money_account_service.dtos.request.TransferRequestDto;
import com.money_account_service.dtos.response.AccountDetailsResponseDto;
import com.money_account_service.dtos.response.CreateAccountResponseDto;
import com.money_account_service.dtos.response.TopUpResponseDto;
import com.money_account_service.dtos.response.TransferResponseDto;
import com.money_account_service.entities.AccountEntity;
import com.money_account_service.entities.TopUpEntity;
import com.money_account_service.entities.TransferEntity;
import com.money_account_service.mappers.RequestMapper;
import com.money_account_service.mappers.ResponseMapper;
import com.money_account_service.repositories.AccountRepository;
import com.money_account_service.repositories.TopUpRepository;
import com.money_account_service.repositories.TransferRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class AccountManagementService {

    private final TopUpRepository topUpRepository;
    private final TransferRepository transferRepository;
    private final AccountRepository accountRepository;
//    private final UserRepository userRepository;

    public TopUpResponseDto topUp(TopUpRequestDto topUpRequestDto) {
        //TODO: dodanie tego do ledgera?
        TopUpEntity topUpEntity = topUpRepository.save(RequestMapper.topUpRequestToTopUpEntity(topUpRequestDto));
        return ResponseMapper.topUpEntityToTopUpResponse(topUpEntity);
    }

    public TransferResponseDto transfer(TransferRequestDto transferRequestDto) {
        //TODO: dodanie tego do ledgera?
        TransferEntity transferEntity = transferRepository.save(RequestMapper.transferRequestToTransferEntity(transferRequestDto));
        return ResponseMapper.transferEntityToTransferResponse(transferEntity);
    }

    public CreateAccountResponseDto createAccount(CreateAccountRequestDto createAccountRequestDto) {
        AccountEntity accountEntity = accountRepository.save(RequestMapper.accountRequestToAccountEntity(createAccountRequestDto));
        return ResponseMapper.accountEntityToCreateAccountResponse(accountEntity);
    }

    public TransferResponseDto getTransactionDetails(Long id) {
        Optional<TransferEntity> transferEntityOptional = transferRepository.findById(id);
        return transferEntityOptional.map(ResponseMapper::transferEntityToTransferResponse).orElse(null);
    }

    public AccountDetailsResponseDto getAccountDetails(Long id) {
        Optional<AccountEntity> accountEntityOptional = accountRepository.findById(id);
        return accountEntityOptional.map(ResponseMapper::accountEntityToAccountDetailsResponse).orElse(null);
    }
}
