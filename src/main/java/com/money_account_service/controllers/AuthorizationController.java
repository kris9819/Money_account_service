package com.money_account_service.controllers;

import com.money_account_service.entities.AccountEntity;
import com.money_account_service.services.AuthorizationService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AuthorizationController {

    private final AuthorizationService authorizationService;

    public AccountEntity authorize(String token) {
        return authorizationService.authorizeAccount(token);
    }
}
