package com.money_account_service.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.money_account_service.dtos.request.AuthorizeRequestDto;
import com.money_account_service.dtos.response.AuthorizeResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@AllArgsConstructor
public class RestTemplateWrapper {

    private RestTemplate restTemplate;

    public static final String AUTHORIZE_URL = "https://localhost:8081/authorize";

    public Optional<AuthorizeResponseDto> authorizeRequest(AuthorizeRequestDto authorizeRequestDto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        ObjectMapper objectMapper = new ObjectMapper();
        HttpEntity<String> entity;

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        try {
            String authorizeRequestString = objectMapper.writeValueAsString(authorizeRequestDto);
            entity = new HttpEntity<>(authorizeRequestString, httpHeaders);

            ResponseEntity<AuthorizeResponseDto> authorizeResponse = restTemplate
                    .exchange(AUTHORIZE_URL, HttpMethod.POST, entity, AuthorizeResponseDto.class);
            return Optional.ofNullable(authorizeResponse.getBody());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
