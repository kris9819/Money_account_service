package com.money_account_service.services;


import com.money_account_service.dtos.request.AuthorizeRequestDto;
import com.money_account_service.dtos.request.CreateAccountRequestDto;
import com.money_account_service.dtos.request.TopUpRequestDto;
import com.money_account_service.dtos.request.TransferRequestDto;
import com.money_account_service.dtos.response.AuthorizeResponseDto;
import com.money_account_service.dtos.response.CreateAccountResponseDto;
import com.money_account_service.dtos.response.TopUpResponseDto;
import com.money_account_service.dtos.response.TransferResponseDto;
import com.money_account_service.entities.AccountEntity;
import com.money_account_service.mappers.RequestMapper;
import com.money_account_service.mappers.ResponseMapper;
import com.money_account_service.repositories.AccountRepository;
import com.money_account_service.repositories.TopUpRepository;
import com.money_account_service.repositories.TransferRepository;
import com.money_account_service.utility.RestTemplateWrapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@AllArgsConstructor
public class AccountManagementService {

    private TopUpRepository topUpRepository;
    private TransferRepository transferRepository;
    private AccountRepository accountRepository;
    private RestTemplateWrapper restTemplateWrapper;

    public TopUpResponseDto topUp(TopUpRequestDto topUpRequestDto) {
        if (authorizeRequest(topUpRequestDto.accessToken()).authorized()) {
//            TopUpEntity topUpEntity = topUpRepository.save(RequestMapper.topUpRequestToTopUpEntity(topUpRequestDto));
//            return ResponseMapper.topUpEntityToTopUpResponse(topUpEntity);
            return null;
        }
        return null;
    }

    public TransferResponseDto transfer(TransferRequestDto transferRequestDto) {
        if (authorizeRequest(transferRequestDto.accessToken()).authorized()) {
//            TransferEntity transferEntity = transferRepository.save(RequestMapper.transferRequestToTransferEntity(transferRequestDto));
//            return ResponseMapper.transferEntityToTransferResponse(transferEntity);
            return null;
        }
        return null;
    }

    public CreateAccountResponseDto createAccount(CreateAccountRequestDto createAccountRequestDto) {
        if (authorizeRequest(createAccountRequestDto.accessToken()).authorized()) {
//            AccountEntity accountEntity = accountRepository.save(RequestMapper.accountRequestToAccountEntity(createAccountRequestDto));
//            return ResponseMapper.accountEntityToCreateAccountResponse(a)
            return null;
        }
        return null;
    }

//    public TransferResponseDto getTransactionDetails(Long id) {
//        Optional<TransferEntity> transferEntityOptional = transferRepository.findById(id);
//        return transferEntityOptional.map(ResponseMapper::transferEntityToTransferResponse).orElse(null);
//    }
//
//    public AccountDetailsResponseDto getAccountDetails(Long id) {
//        Optional<AccountEntity> accountEntityOptional = accountRepository.findById(id);
//        return accountEntityOptional.map(ResponseMapper::accountEntityToAccountDetailsResponse).orElse(null);
//    }

    private AuthorizeResponseDto authorizeRequest(String accessToken) {
        Optional<AuthorizeResponseDto> authorizeResponseDtoOptional = restTemplateWrapper.authorizeRequest(
                new AuthorizeRequestDto(accessToken));

        if (authorizeResponseDtoOptional.isPresent()) {
            return authorizeResponseDtoOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to authorize request");
        }
    }
}
