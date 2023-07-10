package com.money_account_service.services;


import com.money_account_service.dtos.request.CreateAccountRequestDto;
import com.money_account_service.dtos.response.CreateAccountResponseDto;
import com.money_account_service.repositories.AccountRepository;
import com.money_account_service.utility.UserServiceClient;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;
    private UserServiceClient userServiceClient;

    public CreateAccountResponseDto createAccount(CreateAccountRequestDto createAccountRequestDto) {
//        if (authorizeRequest(createAccountRequestDto.accessToken()).authorized()) {
////            AccountEntity accountEntity = accountRepository.save(RequestMapper.accountRequestToAccountEntity(createAccountRequestDto));
////            return ResponseMapper.accountEntityToCreateAccountResponse(a)
//            return null;
//        }
        return null;
    }

//
//    public AccountDetailsResponseDto getAccountDetails(Long id) {
//        Optional<AccountEntity> accountEntityOptional = accountRepository.findById(id);
//        return accountEntityOptional.map(ResponseMapper::accountEntityToAccountDetailsResponse).orElse(null);
//    }

//    private AuthorizeResponseDto authorizeRequest(String accessToken) {
//        Optional<AuthorizeResponseDto> authorizeResponseDtoOptional = restTemplateWrapper.authorizeRequest(
//                new AuthorizeRequestDto(accessToken));
//
//        if (authorizeResponseDtoOptional.isPresent()) {
//            return authorizeResponseDtoOptional.get();
//        } else {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Failed to authorize request");
//        }
//    }
}
