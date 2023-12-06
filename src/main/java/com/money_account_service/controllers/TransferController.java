package com.money_account_service.controllers;

import com.money_account_service.dtos.request.TransferRequestDto;
import com.money_account_service.dtos.response.TransferResponseDto;
import com.money_account_service.dtos.response.TransfersResponseDto;
import com.money_account_service.entities.AccountEntity;
import com.money_account_service.entities.TransferEntity;
import com.money_account_service.mappers.TransferMapper;
import com.money_account_service.services.AuthorizationService;
import com.money_account_service.services.TransferService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransferController extends AuthorizationController{

    private final TransferService transferService;

    public TransferController(AuthorizationService authorizationService, TransferService transferService) {
        super(authorizationService);
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public TransferResponseDto transfer(@RequestBody TransferRequestDto transferRequestDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
        AccountEntity account = authorize(accessToken);
        TransferEntity transfer = transferService.transfer(transferRequestDto, account.getAccountId());
        return TransferMapper.map(transfer);
    }
    @GetMapping("/transfers")
    public TransfersResponseDto getTransferHistory(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
        AccountEntity account = authorize(accessToken);
        List<TransferEntity> transferList = transferService.getTransferHistory(account.getAccountId());
        return TransferMapper.mapTransfers(transferList);
    }

    @GetMapping("/transfers/{id}")
    public TransferResponseDto getTransfer(@PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
        AccountEntity account = authorize(accessToken);
        TransferEntity transfer = transferService.getTransferDetails(id, account.getAccountId());
        return TransferMapper.map(transfer);
    }
}
