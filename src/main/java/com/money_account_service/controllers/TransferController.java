package com.money_account_service.controllers;

import com.money_account_service.dtos.request.TransferRequestDto;
import com.money_account_service.services.AccountService;
import com.money_account_service.services.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class TransferController {

//    private final TransferService transferService;
//
//    @PostMapping("/transfer")
//    public void transfer(@RequestBody TransferRequestDto transferRequestDto) {
//        transferService.transfer(transferRequestDto);
//    }
//    @GetMapping("/transfers")
//    public void getTransferHistory() {
//
//    }
//
//    @GetMapping("/transfers/{id}")
//    public void getTransfer(@PathVariable Long id) {
////        transferService.getTransferDetails(id);
//    }
}
