package com.money_account_service.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.money_account_service.dtos.response.AuthorizeResponseDto;
import lombok.RequiredArgsConstructor;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RequiredArgsConstructor
public class UserServiceClient {

    private final OkHttpClient okHttpClient;

    private final ObjectMapper objectMapper;
    @Value("${authorize.url}")
    private String AUTHORIZE_URL;

    public AuthorizeResponseDto authorize(String accessToken) {
        try {
            RequestBody requestBody = RequestBody.create(null, new byte[0]);

            Request request = new Request.Builder()
                    .url(AUTHORIZE_URL)
                    .addHeader("Authorization", accessToken)
                    .post(requestBody)
                    .build();

            Call call = okHttpClient.newCall(request);
            Response response = call.execute();

            if (response.isSuccessful() && response.body() != null) {
                return objectMapper.readValue(response.body().string(), AuthorizeResponseDto.class);
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to authorize user");
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Failed to authorize user");
    }
}
