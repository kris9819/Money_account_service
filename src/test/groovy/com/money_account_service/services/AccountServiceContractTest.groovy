package com.money_account_service.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.money_account_service.dtos.request.CreateAccountRequestDto
import com.money_account_service.dtos.response.AccountDetailsResponseDto
import com.money_account_service.dtos.response.AuthorizeResponseDto
import com.money_account_service.dtos.response.CreateAccountResponseDto
import com.money_account_service.entities.AccountEntity
import com.money_account_service.repositories.AccountRepository
import com.money_account_service.utility.UserServiceClient
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class AccountServiceContractTest extends Specification {

    @Autowired
    private MockMvc mockMvc
    @Autowired
    private ObjectMapper objectMapper
    @SpringBean
    private final AccountRepository accountRepository = Mock(AccountRepository)
    @SpringBean
    private final UserServiceClient userServiceClient = Mock(UserServiceClient)
    private AuthorizationService authorizationService
    private AccountService accountService

    def setup() {
        authorizationService = new AuthorizationService(userServiceClient)
        accountService = new AccountService(accountRepository)
    }

    def "Should add new account"() {
        given: "CreateAccountRequestDto is provided"
            CreateAccountRequestDto createAccountRequestDto1 = new CreateAccountRequestDto("PLN")

        and: "CreateAccountResponseDto is provided"

            CreateAccountResponseDto createAccountResponseDto = new CreateAccountResponseDto("123", 0L, "PLN")

        and: "AccountEntity is provided"
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

        accountRepository.save(_ as AccountEntity) >> accountEntity
        accountRepository.findByUserSub(_) >> Optional.ofNullable(null)
        userServiceClient.authorize(_) >> authorizeResponseDto

        expect: "API call response is asserted"
            mockMvc.perform(MockMvcRequestBuilders.post("/account")
                    .contentType("application/json")
                    .header((HttpHeaders.AUTHORIZATION), "Bearer 123")
                    .content(objectMapper.writeValueAsString(createAccountRequestDto1)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn()
                    .response
                    .contentAsString == objectMapper.writeValueAsString(createAccountResponseDto)
    }

    def "Should get account info"() {
        given:
        AccountDetailsResponseDto accountDetailsResponseDto = new AccountDetailsResponseDto("123", 0L, "PLN")

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

        accountRepository.findById(_) >> Optional.ofNullable(accountEntity)
        userServiceClient.authorize(_) >> authorizeResponseDto

        expect: "API call response is asserted"
            mockMvc.perform(MockMvcRequestBuilders.get("/account/{id}/details", 1L)
                    .header((HttpHeaders.AUTHORIZATION), "Bearer 123"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn()
                    .response
                    .contentAsString == objectMapper.writeValueAsString(accountDetailsResponseDto)
    }
}
