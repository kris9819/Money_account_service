package com.money_account_service.controllers;

import com.money_account_service.dtos.request.CreateAccountRequestDto;
import com.money_account_service.services.AccountService;
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

    @PostMapping("/account")
    public void createAccount(@RequestBody CreateAccountRequestDto createAccountRequestDto) {
        accountService.createAccount(createAccountRequestDto);
    }

    @GetMapping("/account/{id}/details")
    public void getAccountDetails(@PathVariable Long id) {
//        accountManagementService.getAccountDetails(id);
    }

}
