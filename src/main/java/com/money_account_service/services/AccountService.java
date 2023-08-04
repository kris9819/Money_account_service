package com.money_account_service.services;


import com.money_account_service.dtos.request.CreateAccountRequestDto;
import com.money_account_service.dtos.response.AccountDetailsResponseDto;
import com.money_account_service.dtos.response.CreateAccountResponseDto;
import com.money_account_service.entities.AccountEntity;
import com.money_account_service.mappers.ResponseMapper;
import com.money_account_service.models.UserModel;
import com.money_account_service.repositories.AccountRepository;
import com.money_account_service.utility.DateTimeProvider;
import lombok.AllArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;

    private DateTimeProvider dateTimeProvider;

    public CreateAccountResponseDto createAccount(CreateAccountRequestDto createAccountRequestDto, UserModel userModel) {
        AccountEntity accountEntity = AccountEntity.builder()
                .accountNumber(generateAccountNumberForUser())
                .currency(createAccountRequestDto.currency())
                .userSub(userModel.userSub())
                .createdAt(dateTimeProvider.getInstant())
                .updatedAt(dateTimeProvider.getInstant())
                .build();

        if (accountRepository.findByUserSub(accountEntity.getUserSub()).isPresent())
        {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User account already exist");
        }
        return ResponseMapper.accountEntityToCreateAccountResponse(accountRepository.save(accountEntity));
    }


    public AccountDetailsResponseDto getAccountDetails(Long id, UserModel userModel) {

        Optional<AccountEntity> accountEntityOptional = accountRepository.findById(id);
        if (accountEntityOptional.isPresent()) {
            return ResponseMapper.accountEntityToAccountDetailsResponse(accountEntityOptional.get());
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User account already exist");
    }

    private String generateAccountNumberForUser() {
        Iban iban = Iban.random(CountryCode.PL);
        return iban.getAccountNumber();
    }
}
