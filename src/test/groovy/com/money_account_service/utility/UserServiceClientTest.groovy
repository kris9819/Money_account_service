package com.money_account_service.utility

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.WireMockServer
import com.money_account_service.dtos.response.AuthorizeResponseDto
import okhttp3.OkHttpClient
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.client.WireMock.*

class UserServiceClientTest extends Specification {

    WireMockServer wireMock = new WireMockServer(8081)

    private OkHttpClient okHttpClient
    private UserServiceClient userServiceClient
    private ObjectMapper objectMapper

    def setup() {
        objectMapper = new ObjectMapper()
        okHttpClient = getOkHttpClient()
        userServiceClient = new UserServiceClient(okHttpClient, objectMapper)

        wireMock.start()
    }

    def cleanup() {
        wireMock.resetAll()
        wireMock.stop()
    }

    def "Should authorize request and return user details"() {
        given: "AuthorizeResponseDto is provided"
        AuthorizeResponseDto authorizeResponseDto1 = AuthorizeResponseDto.builder()
                .email("mail@mail.com")
                .userSub("123")
                .name("kris")
                .build()

        wireMock.stubFor(
                post(urlEqualTo("/authorize"))
                        .willReturn(okJson(objectMapper.writeValueAsString(authorizeResponseDto1)))
        )

        when: "authorize method is called"
            def authorizeResponse = userServiceClient.authorize("Bearer 123")

        then: "expected values are returned"
            authorizeResponse.userSub() == "123"
            authorizeResponse.name() == "kris"
            authorizeResponse.email() == "mail@mail.com"
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder().build()
    }
}
