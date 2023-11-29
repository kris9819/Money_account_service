package com.money_account_service.services;

import com.money_account_service.dtos.request.CreateAccountRequestDto;
import com.money_account_service.entities.AccountEntity;
import com.money_account_service.models.UserModel;
import com.money_account_service.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;

    @Transactional
    public AccountEntity createAccount(CreateAccountRequestDto createAccountRequestDto, UserModel userModel) {
        AccountEntity accountEntity = new AccountEntity(generateAccountNumberForUser(),
                createAccountRequestDto.currency(), userModel.userSub());

        Optional<AccountEntity> accountEntityByUserSub = Optional.ofNullable(accountRepository.findByUserSub(accountEntity.getUserSub()));

        if (accountEntityByUserSub.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User account already exist");
        }

        return accountRepository.save(accountRepository.save(accountEntity));
    }

    @Transactional
    public AccountEntity getAccountDetails(Long id) {
        Optional<AccountEntity> accountEntityOptional = accountRepository.findById(id);

        return accountEntityOptional.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.CONFLICT, "User account already exist"));
    }

    private String generateAccountNumberForUser() {
        Iban iban = Iban.random(CountryCode.PL);
        return iban.getAccountNumber();
    }
}
