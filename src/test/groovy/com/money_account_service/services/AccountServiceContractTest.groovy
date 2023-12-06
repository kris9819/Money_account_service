package com.money_account_service.services

import com.money_account_service.dtos.request.CreateAccountRequestDto
import com.money_account_service.dtos.response.AccountDetailsResponseDto
import com.money_account_service.dtos.response.AuthorizeResponseDto
import com.money_account_service.entities.AccountEntity
import com.money_account_service.repositories.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class AccountServiceContractTest extends BaseWeb {

    @Autowired
    private AccountRepository accountRepository
    private AccountService accountService

    def setup() {
        accountService = new AccountService(accountRepository)
    }

    def "Should add new account"() {
        given: "CreateAccountRequestDto is provided"
            CreateAccountRequestDto createAccountRequestDto1 = new CreateAccountRequestDto("PLN")

        and: "AuthorizeResponseDto is provided"
        AuthorizeResponseDto authorizeResponseDto = AuthorizeResponseDto.builder()
                .email("mail@mail.com")
                .name("kris")
                .userSub("123")
                .build()

        userServiceClient.authorize(_) >> authorizeResponseDto

        expect: "API call response is asserted"
            mockMvc.perform(MockMvcRequestBuilders.post("/account")
                    .contentType("application/json")
                    .header((HttpHeaders.AUTHORIZATION), "Bearer 123")
                    .content(objectMapper.writeValueAsString(createAccountRequestDto1)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath('$.accountNumber').isNotEmpty())
                    .andExpect(MockMvcResultMatchers.jsonPath('$.balance').isNumber())
                    .andExpect(MockMvcResultMatchers.jsonPath('$.currency').value('PLN'))
    }

    def "Should get account info"() {
        given:
        AccountDetailsResponseDto accountDetailsResponseDto = new AccountDetailsResponseDto("123", 0L, "PLN")

        and: "expected AccountEntity is provided"
        AccountEntity accountEntity = AccountEntity.builder()
                .accountNumber("123")
                .currency("PLN")
                .userSub("1234")
                .build()

        and: "AuthorizeResponseDto is provided"
        AuthorizeResponseDto authorizeResponseDto = AuthorizeResponseDto.builder()
                .email("mail@mail.com")
                .name("kris")
                .userSub("1234")
                .build()

        and: "AccountEntity is added to database"
        accountRepository.save(accountEntity)
        userServiceClient.authorize(_) >> authorizeResponseDto

        expect: "API call response is asserted"
            mockMvc.perform(MockMvcRequestBuilders.get("/account/details", 1L)
                    .header((HttpHeaders.AUTHORIZATION), "Bearer 123"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn()
                    .response
                    .contentAsString == objectMapper.writeValueAsString(accountDetailsResponseDto)
    }
}
