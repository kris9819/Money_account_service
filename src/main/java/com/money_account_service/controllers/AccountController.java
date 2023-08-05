package com.money_account_service.controllers;

import com.money_account_service.dtos.request.CreateAccountRequestDto;
import com.money_account_service.mappers.UserMapper;
import com.money_account_service.services.AccountService;
import com.money_account_service.utility.UserServiceClient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AccountController extends AuthorizationController {

    private final AccountService accountService;

    private final UserServiceClient userServiceClient;

    @PostMapping("/account")
    public void createAccount(@RequestBody CreateAccountRequestDto createAccountRequestDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
        accountService.createAccount(createAccountRequestDto, authorize(accessToken));
    }

    @GetMapping("/account/{id}/details")
    public void getAccountDetails(@PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
    }

}
