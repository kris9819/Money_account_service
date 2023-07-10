package com.money_account_service.utility;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

@AllArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {

    private UserServiceClient userServiceClient;

    @Override
    public boolean preHandle(HttpServletRequest requestServlet, HttpServletResponse responseServlet, Object handler)
    {
        String authHeader = requestServlet.getHeader("Authorization");

        if (authHeader.startsWith("Bearer")) {
            return userServiceClient.authorize(authHeader.substring(7));
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong token provided");
    }
}
