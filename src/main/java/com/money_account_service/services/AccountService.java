package com.money_account_service.services;


import com.money_account_service.dtos.request.CreateAccountRequestDto;
import com.money_account_service.dtos.response.CreateAccountResponseDto;
import com.money_account_service.models.UserModel;
import com.money_account_service.repositories.AccountRepository;
import com.money_account_service.utility.UserServiceClient;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;
    private UserServiceClient userServiceClient;

    public CreateAccountResponseDto createAccount(CreateAccountRequestDto createAccountRequestDto, UserModel userModel) {
//        if (authorizeRequest(createAccountRequestDto.accessToken()).authorized()) {
////            AccountEntity accountEntity = accountRepository.save(RequestMapper.accountRequestToAccountEntity(createAccountRequestDto));
////            return ResponseMapper.accountEntityToCreateAccountResponse(a)
//            return null;
//        }
        return null;
    }

//
//    public AccountDetailsResponseDto getAccountDetails(Long id, UserModel userModel) {
//        Optional<AccountEntity> accountEntityOptional = accountRepository.findById(id);
//        return accountEntityOptional.map(ResponseMapper::accountEntityToAccountDetailsResponse).orElse(null);
//    }
}
