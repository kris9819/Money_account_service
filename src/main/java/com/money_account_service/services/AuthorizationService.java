package com.money_account_service.services;

import com.money_account_service.dtos.response.AuthorizeResponseDto;
import com.money_account_service.entities.AccountEntity;
import com.money_account_service.mappers.UserMapper;
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

    public Long authorize(String token) {

        if (token.startsWith("Bearer")) {
            return getAccountIdByUserSub(userServiceClient.authorize(token.substring(7)));
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong token provided");
    }

    public UserModel authorizeWithUserModel(String token) {

        if (token.startsWith("Bearer")) {
            return UserMapper.authorizeRequestDtoToUserModel(userServiceClient.authorize(token.substring(7)));
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong token provided");
    }

    private Long getAccountIdByUserSub(AuthorizeResponseDto authorizeResponseDto) {
        Optional<AccountEntity> accountEntityByUserSub = Optional.ofNullable(accountRepository.findByUserSub(authorizeResponseDto.userSub()));

        if (accountEntityByUserSub.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account doesn't exist");
        }

        return accountEntityByUserSub.get().getAccountId();
    }
}
