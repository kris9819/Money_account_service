package com.money_account_service.controllers;

import com.money_account_service.dtos.request.TopUpRequestDto;
import com.money_account_service.dtos.response.AuthorizeResponseDto;
import com.money_account_service.services.TopUpService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@AllArgsConstructor
@RestController
public class TopUpController {

    private final TopUpService topUpService;


    @PostMapping("/topUp")
    public void topUp(@RequestBody TopUpRequestDto topUpRequestDto, HttpServletRequest request) {
    }
}
