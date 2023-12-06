package com.money_account_service.controllers;

import com.money_account_service.dtos.request.CreateAccountRequestDto;
import com.money_account_service.dtos.response.AccountResponseDto;
import com.money_account_service.entities.AccountEntity;
import com.money_account_service.mappers.AccountMapper;
import com.money_account_service.models.UserModel;
import com.money_account_service.services.AccountService;
import com.money_account_service.services.AuthorizationService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController extends AuthorizationController {

    private final AccountService accountService;

    private final AuthorizationService authorizationService;

    public AccountController(AuthorizationService authorizationService, AccountService accountService) {
        super(authorizationService);
        this.accountService = accountService;
        this.authorizationService = authorizationService;
    }

    @PostMapping("/account")
    public AccountResponseDto createAccount(@RequestBody CreateAccountRequestDto createAccountRequestDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
        UserModel user = authorizationService.authorizeUser(accessToken);
        AccountEntity account = accountService.createAccount(createAccountRequestDto, user);
        return AccountMapper.map(account);
    }

    @GetMapping("/account/details")
    public AccountResponseDto getAccountDetails(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
        AccountEntity account = authorize(accessToken);
        return AccountMapper.map(account);
    }

}
