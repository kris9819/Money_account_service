package com.money_account_service.controllers;

import com.money_account_service.dtos.request.TopUpRequestDto;
import com.money_account_service.services.AccountService;
import com.money_account_service.services.TopUpService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class TopUpController {

//    private final TopUpService topUpService;
//
//    @PostMapping("/topUp")
//    public void topUp(@RequestBody TopUpRequestDto topUpRequestDto) {
//        topUpService.topUp(topUpRequestDto);
//    }
}
