package com.money_account_service.utility

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomjankes.wiremock.WireMockGroovy
import com.money_account_service.dtos.response.AuthorizeResponseDto
import okhttp3.OkHttpClient
import spock.lang.Specification

import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import java.security.SecureRandom
import java.security.cert.X509Certificate

class UserServiceClientTest extends Specification {

    WireMockGroovy wireMock = new WireMockGroovy(8081)

    private OkHttpClient okHttpClient
    private UserServiceClient userServiceClient
    private ObjectMapper objectMapper

    def setup() {
        objectMapper = new ObjectMapper()
        okHttpClient = getOkHttpClient()
        userServiceClient = new UserServiceClient(okHttpClient, objectMapper)
    }

    def "Should authorize request and return user details"() {
        given: "AuthorizeResponseDto is provided"
        AuthorizeResponseDto authorizeResponseDto1 = AuthorizeResponseDto.builder()
                .email("mail@mail.com")
                .userSub("123")
                .name("kris")
                .build()

        wireMock.stub {
            request {
                method "GET"
                url "/authorize"
            }
            response {
                status 200
                body objectMapper.writeValueAsString(authorizeResponseDto1)
                headers { "Content-Type" "application/json" }
            }
        }


        when: "authorize method is called"
            def authorizeResponse = userServiceClient.authorize("Bearer 123")

        then: "expected values are returned"
            authorizeResponse.userSub() == "123"
            authorizeResponse.name() == "kris"
            authorizeResponse.email() == "mail@mail.com"
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.hostnameVerifier((hostname, session) -> true);

        SSLContext sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, getTrustManagers(), new SecureRandom());

        builder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) getTrustManagers()[0]);

        return builder.build();
    }


    private static TrustManager[] getTrustManagers() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
    }
}
