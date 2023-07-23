package com.money_account_service.controllers;

import com.money_account_service.models.UserModel;
import com.money_account_service.services.AuthorizationService;

public abstract class AuthorizationController {

    AuthorizationService authorizationService;

    public UserModel authorize(String token) {
        return authorizationService.authorize(token);
    }
}
