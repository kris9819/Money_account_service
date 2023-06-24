package com.money_account_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountManagementController {

    @PostMapping("/topUp")
    public void topUp() {

    }

    @PostMapping("/transfer")
    public void transfer() {

    }

    @PostMapping("/createAccount")
    public void createAccount() {

    }

    @GetMapping("/transactions")
    public void getTransactionHistory() {

    }

    @GetMapping("/transactions/{id}")
    public void getTransaction(@PathVariable Long id) {

    }

    @GetMapping("/account/{id}/details")
    public void getAccountDetails(@PathVariable Long id) {

    }

}
