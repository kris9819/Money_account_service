package com.money_account_service.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.money_account_service.dtos.response.AuthorizeResponseDto;
import lombok.AllArgsConstructor;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@AllArgsConstructor
public class UserServiceClient {

    private OkHttpClient okHttpClient;
    public static final String AUTHORIZE_URL = "https://localhost:8081/authorize";

    public AuthorizeResponseDto authorize(String accessToken) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
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
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Failed to authorize user");
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Failed to authorize user");
    }
}
