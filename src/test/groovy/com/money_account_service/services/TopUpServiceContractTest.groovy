package com.money_account_service.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.money_account_service.dtos.request.CreateAccountRequestDto
import com.money_account_service.dtos.request.TopUpRequestDto
import com.money_account_service.dtos.response.AuthorizeResponseDto
import com.money_account_service.dtos.response.CreateAccountResponseDto
import com.money_account_service.dtos.response.TopUpResponseDto
import com.money_account_service.entities.AccountEntity
import com.money_account_service.entities.TransferEntity
import com.money_account_service.repositories.AccountRepository
import com.money_account_service.repositories.TransferRepository
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

import java.time.Clock

class TopUpServiceContractTest extends BaseWeb {

    @Autowired
    private TransferRepository transferRepository
    private TopUpService topUpService

    def setup() {
        topUpService = new TopUpService(transferRepository)
    }

    def "Should top up account"() {
        given: "TopUpRequestDto is provided"
        TopUpRequestDto topUpRequestDto = new TopUpRequestDto(0L, "PLN", "123")

        and: "TopUpResponseDto is provided"
        TopUpResponseDto topUpResponseDto = new TopUpResponseDto(0L)

        and: "Current timestamp is provided"
        def ts = Clock.systemUTC().instant()

        and: "TransferEntity is provided"
        TransferEntity transferEntity = TransferEntity.builder()
                .title("TOP_UP")
                .transferDate(ts)
                .idempotencyKey("1234")
                .type("TOP_UP")
                .createdAt(null)
                .updatedAt(null)
                .build()

        and: "AuthorizeResponseDto is provided"
        AuthorizeResponseDto authorizeResponseDto = AuthorizeResponseDto.builder()
                .email("mail@mail.com")
                .name("kris")
                .userSub("123")
                .build()

        and: "TransferEntity is added to database"
        transferRepository.save(transferEntity)
        userServiceClient.authorize(_) >> authorizeResponseDto

        expect: "API call response is asserted"
        mockMvc.perform(MockMvcRequestBuilders.post("/topUp")
                .contentType("application/json")
                .header((HttpHeaders.AUTHORIZATION), "Bearer 123")
                .content(objectMapper.writeValueAsString(topUpRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .response
                .contentAsString == objectMapper.writeValueAsString(topUpResponseDto)
    }
}
