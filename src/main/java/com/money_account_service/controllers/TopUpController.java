package com.money_account_service.controllers;

import com.money_account_service.dtos.request.TopUpRequestDto;
import com.money_account_service.dtos.response.TopUpResponseDto;
import com.money_account_service.entities.AccountEntity;
import com.money_account_service.entities.TransferEntity;
import com.money_account_service.mappers.TopUpMapper;
import com.money_account_service.services.AuthorizationService;
import com.money_account_service.services.TopUpService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopUpController extends AuthorizationController {

    private final TopUpService topUpService;

    public TopUpController(AuthorizationService authorizationService, TopUpService topUpService) {
        super(authorizationService);
        this.topUpService = topUpService;
    }


    @PostMapping("/topUp")
    public TopUpResponseDto topUp(@RequestBody TopUpRequestDto topUpRequestDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
        AccountEntity account = authorize(accessToken);
        TransferEntity transfer = topUpService.topUp(topUpRequestDto, account.getAccountId());
        return TopUpMapper.map(transfer);
    }
}
