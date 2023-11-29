package com.money_account_service.controllers;

import com.money_account_service.models.UserModel;
import com.money_account_service.services.AuthorizationService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AuthorizationController {

    private final AuthorizationService authorizationService;

    public Long authorize(String token) {
        return authorizationService.authorize(token);
    }

    public UserModel authorizeWithUserModel(String token) {
        return authorizationService.authorizeWithUserModel(token);
    }
}
