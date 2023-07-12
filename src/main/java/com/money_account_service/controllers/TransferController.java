package com.money_account_service.controllers;

import com.money_account_service.dtos.request.TransferRequestDto;
import com.money_account_service.dtos.response.AuthorizeResponseDto;
import com.money_account_service.services.TransferService;
import com.money_account_service.utility.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@AllArgsConstructor
@RestController
public class TransferController {

    private final TransferService transferService;

    private final RequestInterceptor requestInterceptor;

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferRequestDto transferRequestDto, HttpServletRequest request) {
        Optional<AuthorizeResponseDto> authorizeResponseDto = requestInterceptor.authorizeRequest(request);
        if (authorizeResponseDto.isPresent()) {

        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Failed to authorize user");
        }
    }
    @GetMapping("/transfers")
    public void getTransferHistory(HttpServletRequest request) {
        Optional<AuthorizeResponseDto> authorizeResponseDto = requestInterceptor.authorizeRequest(request);
        if (authorizeResponseDto.isPresent()) {

        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Failed to authorize user");
        }
    }

    @GetMapping("/transfers/{id}")
    public void getTransfer(@PathVariable Long id, HttpServletRequest request) {
        Optional<AuthorizeResponseDto> authorizeResponseDto = requestInterceptor.authorizeRequest(request);
        if (authorizeResponseDto.isPresent()) {

        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Failed to authorize user");
        }
    }
}
