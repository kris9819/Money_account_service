package com.money_account_service.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.money_account_service.dtos.response.AccountDetailsResponseDto
import com.money_account_service.dtos.response.AuthorizeResponseDto
import com.money_account_service.dtos.response.CreateAccountResponseDto
import com.money_account_service.entities.AccountEntity
import com.money_account_service.models.UserModel
import org.spockframework.spring.SpringBean
import com.money_account_service.repositories.AccountRepository
import com.money_account_service.utility.UserServiceClient
import okhttp3.Call
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class AccountServiceContractTest extends Specification {

    @Autowired
    private ObjectMapper objectMapper
    @Autowired
    private OkHttpClient okHttpClient
    @SpringBean
    private final AccountRepository accountRepository = Mock(AccountRepository)
    @SpringBean
    private final UserServiceClient userServiceClient = Mock(UserServiceClient)
    private AuthorizationService authorizationService
    private AccountService accountService

    def setup() {
        authorizationService = new AuthorizationService(userServiceClient)
        accountService = new AccountService(accountRepository, null)
    }

    def "Should add new account"() {
        given: "CreateAccountRequestDto is provided"
            JSONObject createAccountRequestDto = new JSONObject()
            def createAccountUrl = "https://localhost:8080/account"
            final MediaType JSON = MediaType.get("application/json; charset=utf-8");

            createAccountRequestDto.put("currency", "PLN")

            RequestBody requestBody = RequestBody.create(createAccountRequestDto.toString(), JSON)

        and: "expected AccountEntity is provided"
            AccountEntity accountEntity = AccountEntity.builder()
                .accountNumber("123")
                .currency("PLN")
                .userSub("123")
                .createdAt(null)
                .updatedAt(null)
                .build()

        and: "AuthorizeResponseDto is provided"
        AuthorizeResponseDto authorizeResponseDto = AuthorizeResponseDto.builder()
                .email("mail@mail.com")
                .name("kris")
                .userSub("123")
                .build()

        when: "account method is called"
            Request request = new Request.Builder()
                .url(createAccountUrl)
                .addHeader("Authorization", "Bearer 123")
                .post(requestBody)
                .build()

            Call call = okHttpClient.newCall(request)
            Response response = call.execute()


        then: "database connection is mocked"
            accountRepository.save(_ as AccountEntity) >> accountEntity
            accountRepository.findByUserSub(_) >> Optional.ofNullable(null)
            userServiceClient.authorize(_) >> authorizeResponseDto

        and: "expected values are returned"
            CreateAccountResponseDto createAccountResponseDto1 = objectMapper.readValue(response.body().string(), CreateAccountResponseDto.class)
            createAccountResponseDto1.currency() == "PLN"
            createAccountResponseDto1.balance() == 0L
            createAccountResponseDto1.accountNumber() == "123"
    }

    def "Should get account info"() {
        given: "CreateAccountRequestDto is provided"
        def getAccountInfoUrl = "https://localhost:8080/account/1/details"

        and: "expected AccountEntity is provided"
        AccountEntity accountEntity = AccountEntity.builder()
                .accountNumber("123")
                .currency("PLN")
                .userSub("123")
                .build()

        and: "AuthorizeResponseDto is provided"
        AuthorizeResponseDto authorizeResponseDto = AuthorizeResponseDto.builder()
                .email("mail@mail.com")
                .name("kris")
                .userSub("123")
                .build()

        when: "account method is called"
        Request request = new Request.Builder()
                .url(getAccountInfoUrl)
                .addHeader("Authorization", "Bearer 123")
                .build()

        Call call = okHttpClient.newCall(request)
        Response response = call.execute()


        then: "database connection is mocked"
        accountRepository.findById(_) >> Optional.ofNullable(accountEntity)
        userServiceClient.authorize(_) >> authorizeResponseDto

        and: "expected values are returned"
        AccountDetailsResponseDto accountDetailsResponseDto = objectMapper.readValue(response.body().string(), AccountDetailsResponseDto.class)
        accountDetailsResponseDto.currency() == "PLN"
        accountDetailsResponseDto.balance() == 0L
        accountDetailsResponseDto.accountNumber() == "123"
    }
}
