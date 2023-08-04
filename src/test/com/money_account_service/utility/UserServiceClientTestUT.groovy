package com.money_account_service.utility

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.*
import org.json.JSONObject
import org.spockframework.spring.SpringBean
import org.springframework.test.util.ReflectionTestUtils
import spock.lang.Specification

class UserServiceClientTestUT extends Specification {


    @SpringBean
    private OkHttpClient okHttpClient = Mock(OkHttpClient)
    private UserServiceClient userServiceClient
    private ObjectMapper objectMapper
    private Call call = Mock(Call)

    def setup() {
        objectMapper = new ObjectMapper()
        userServiceClient = new UserServiceClient(okHttpClient, objectMapper)
    }

    def "Should authorize request and return user details"() {
        given: "AuthorizeResponseDto is provided"
            JSONObject authorizeResponseDto = new JSONObject()
            final MediaType JSON = MediaType.get("application/json; charset=utf-8");

            authorizeResponseDto.put("userSub", "123")
            authorizeResponseDto.put("email", "mail@mail.com")
            authorizeResponseDto.put("name", "kris")

            ResponseBody responseBody = ResponseBody.create(authorizeResponseDto.toString(), JSON)

        ReflectionTestUtils.setField(userServiceClient, "AUTHORIZE_URL", "https://localhost:8081/authorize");

        and: "mock response is provided"
            final Response response = new Response.Builder()
                    .request(new Request.Builder().url("http://url.com").build())
                    .protocol(Protocol.HTTP_2)
                    .code(200).message("").body(responseBody)
                    .build()

        when: "authorize method is called"
            def authorizeResponse = userServiceClient.authorize("Bearer 123")

        then: "httpclient is mocked"
            call.execute() >> response
            okHttpClient.newCall(_) >> call

        and: "expected values are returned"
            authorizeResponse.userSub() == "123"
            authorizeResponse.name() == "kris"
            authorizeResponse.email() == "mail@mail.com"


    }
}
