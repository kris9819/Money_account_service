package com.money_account_service.controllers;

import com.money_account_service.dtos.request.TransferRequestDto;
import com.money_account_service.dtos.response.AuthorizeResponseDto;
import com.money_account_service.services.TransferService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@AllArgsConstructor
@RestController
public class TransferController {

    private final TransferService transferService;

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferRequestDto transferRequestDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
    }
    @GetMapping("/transfers")
    public void getTransferHistory(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
    }

    @GetMapping("/transfers/{id}")
    public void getTransfer(@PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
    }
}
