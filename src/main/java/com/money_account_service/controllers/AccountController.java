package com.money_account_service.controllers;

import com.money_account_service.dtos.request.CreateAccountRequestDto;
import com.money_account_service.dtos.response.AuthorizeResponseDto;
import com.money_account_service.mappers.UserMapper;
import com.money_account_service.models.UserModel;
import com.money_account_service.services.AccountService;
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

@RestController
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final RequestInterceptor requestInterceptor;

    @PostMapping("/account")
    public void createAccount(@RequestBody CreateAccountRequestDto createAccountRequestDto, HttpServletRequest request) {
        Optional<AuthorizeResponseDto> authorizeResponseDto = requestInterceptor.authorizeRequest(request);
        if (authorizeResponseDto.isPresent()) {
            UserModel userModel = UserMapper.authorizeRequestDtoToUserModel(authorizeResponseDto.get());
            accountService.createAccount(createAccountRequestDto, userModel);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Failed to authorize user");
        }
    }

    @GetMapping("/account/{id}/details")
    public void getAccountDetails(@PathVariable Long id, HttpServletRequest request) {
        Optional<AuthorizeResponseDto> authorizeResponseDto = requestInterceptor.authorizeRequest(request);
        if (authorizeResponseDto.isPresent()) {

        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Failed to authorize user");
        }
    }

}
