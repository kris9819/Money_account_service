package com.money_account_service.controllers;

import com.money_account_service.dtos.request.CreateAccountRequestDto;
import com.money_account_service.dtos.response.AccountDetailsResponseDto;
import com.money_account_service.dtos.response.CreateAccountResponseDto;
import com.money_account_service.services.AccountService;
import com.money_account_service.services.AuthorizationService;
import com.money_account_service.utility.UserServiceClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController extends AuthorizationController {

    private final AccountService accountService;

    private final UserServiceClient userServiceClient;

    public AccountController(AuthorizationService authorizationService, AccountService accountService, UserServiceClient userServiceClient) {
        super(authorizationService);
        this.accountService = accountService;
        this.userServiceClient = userServiceClient;
    }

    @PostMapping("/account")
    public CreateAccountResponseDto createAccount(@RequestBody CreateAccountRequestDto createAccountRequestDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
        return accountService.createAccount(createAccountRequestDto, authorize(accessToken));
    }

    @GetMapping("/account/{id}/details")
    public AccountDetailsResponseDto getAccountDetails(@PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
        return accountService.getAccountDetails(id, authorize(accessToken));
    }

}
