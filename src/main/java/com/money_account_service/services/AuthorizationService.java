package com.money_account_service.services;

import com.money_account_service.mappers.UserMapper;
import com.money_account_service.models.UserModel;
import com.money_account_service.utility.UserServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
public class AuthorizationService {

    UserServiceClient userServiceClient;

    public UserModel authorize(String token) {

        if (token.startsWith("Bearer")) {
            return UserMapper.authorizeRequestDtoToUserModel(userServiceClient.authorize(token.substring(7)));
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong token provided");
    }
}
