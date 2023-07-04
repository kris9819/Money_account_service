package com.money_account_service.controllers;

import com.money_account_service.dtos.request.CreateAccountRequestDto;
import com.money_account_service.dtos.request.TopUpRequestDto;
import com.money_account_service.dtos.request.TransferRequestDto;
import com.money_account_service.services.AccountManagementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AccountManagementController {

    private final AccountManagementService accountManagementService;

    @PostMapping("/topUp")
    public void topUp(@RequestBody TopUpRequestDto topUpRequestDto) {
        accountManagementService.topUp(topUpRequestDto);
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferRequestDto transferRequestDto) {
        accountManagementService.transfer(transferRequestDto);
    }

    @PostMapping("/createAccount")
    public void createAccount(@RequestBody CreateAccountRequestDto createAccountRequestDto) {
        accountManagementService.createAccount(createAccountRequestDto);
    }

    @GetMapping("/transactions")
    public void getTransactionHistory() {

    }

    @GetMapping("/transactions/{id}")
    public void getTransaction(@PathVariable Long id) {
//        accountManagementService.getTransactionDetails(id);
    }

    @GetMapping("/account/{id}/details")
    public void getAccountDetails(@PathVariable Long id) {
//        accountManagementService.getAccountDetails(id);
    }

}
