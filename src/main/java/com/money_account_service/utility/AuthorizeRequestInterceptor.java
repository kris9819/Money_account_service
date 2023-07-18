package com.money_account_service.utility;

import com.money_account_service.dtos.response.AuthorizeResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@AllArgsConstructor
public class AuthorizeRequestInterceptor {

    private UserServiceClient userServiceClient;

    public Optional<AuthorizeResponseDto> authorizeRequest(HttpServletRequest requestServlet)
    {
        String authHeader = requestServlet.getHeader("Authorization");

        if (authHeader.startsWith("Bearer")) {
            return Optional.ofNullable(userServiceClient.authorize(authHeader.substring(7)));
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong token provided");
    }
}
