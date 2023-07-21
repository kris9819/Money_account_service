package com.money_account_service.controllers;

import com.money_account_service.dtos.request.CreateAccountRequestDto;
import com.money_account_service.mappers.UserMapper;
import com.money_account_service.services.AccountService;
import com.money_account_service.utility.UserServiceClient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final UserServiceClient userServiceClient;

    @PostMapping("/account")
    public void createAccount(@RequestBody CreateAccountRequestDto createAccountRequestDto, HttpServletRequest request) {
        accountService.createAccount(createAccountRequestDto,
                UserMapper.authorizeRequestDtoToUserModel(userServiceClient.authorize(request.getHeader("Authorization"))));
    }

    @GetMapping("/account/{id}/details")
    public void getAccountDetails(@PathVariable Long id, HttpServletRequest request) {
    }

}
