package com.money_account_service.services;

import com.money_account_service.dtos.response.AuthorizeResponseDto;
import com.money_account_service.entities.AccountEntity;
import com.money_account_service.models.UserModel;
import com.money_account_service.repositories.AccountRepository;
import com.money_account_service.utility.UserServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@AllArgsConstructor
public class AuthorizationService {

    UserServiceClient userServiceClient;

    AccountRepository accountRepository;

    public AccountEntity authorizeAccount(String token) {

        if (token.startsWith("Bearer")) {
            return getAccountIdByUserSub(userServiceClient.authorize(token.substring(7)));
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong token provided");
    }

    public UserModel authorizeUser(String token) {

        if (token.startsWith("Bearer")) {
            AuthorizeResponseDto authorizeResponseDto = userServiceClient.authorize(token.substring(7));
            return UserModel.builder()
                    .userSub(authorizeResponseDto.userSub())
                    .email(authorizeResponseDto.email())
                    .name(authorizeResponseDto.name())
                    .build();
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong token provided");
    }

    private AccountEntity getAccountIdByUserSub(AuthorizeResponseDto authorizeResponseDto) {
        Optional<AccountEntity> accountEntityByUserSub = accountRepository.findByUserSub(authorizeResponseDto.userSub());

        if (accountEntityByUserSub.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account doesn't exist");
        }

        return accountEntityByUserSub.get();
    }
}
