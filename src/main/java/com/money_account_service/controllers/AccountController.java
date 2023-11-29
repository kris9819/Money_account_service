package com.money_account_service.controllers;

import com.money_account_service.dtos.request.CreateAccountRequestDto;
import com.money_account_service.dtos.response.AccountDetailsResponseDto;
import com.money_account_service.dtos.response.CreateAccountResponseDto;
import com.money_account_service.mappers.ResponseMapper;
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

    public AccountController(AuthorizationService authorizationService, AccountService accountService) {
        super(authorizationService);
        this.accountService = accountService;
    }

    @PostMapping("/account")
    public CreateAccountResponseDto createAccount(@RequestBody CreateAccountRequestDto createAccountRequestDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
        return ResponseMapper.accountEntityToCreateAccountResponse(accountService.createAccount(createAccountRequestDto, authorizeWithUserModel(accessToken)));
    }

    @GetMapping("/account/details")
    public AccountDetailsResponseDto getAccountDetails(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
        return ResponseMapper.accountEntityToAccountDetailsResponse(accountService.getAccountDetails(authorize(accessToken)));
    }

}
