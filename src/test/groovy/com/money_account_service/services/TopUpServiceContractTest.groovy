package com.money_account_service.services


import com.money_account_service.dtos.request.TopUpRequestDto
import com.money_account_service.dtos.response.AuthorizeResponseDto
import com.money_account_service.dtos.response.TopUpResponseDto
import com.money_account_service.entities.AccountEntity
import com.money_account_service.entities.TransferEntity
import com.money_account_service.repositories.TransferRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

import java.time.Clock

class TopUpServiceContractTest extends BaseWeb {

    @Autowired
    private TransferRepository transferRepository
    @SpringBean
    private Clock clock = Mock(Clock)
    private TopUpService topUpService

    def setup() {
        topUpService = new TopUpService(transferRepository, clock)
    }

    def "Should top up account"() {
        given: "TopUpRequestDto is provided"
        TopUpRequestDto topUpRequestDto = new TopUpRequestDto(0L, "PLN", "123")

        and: "TopUpResponseDto is provided"
        TopUpResponseDto topUpResponseDto = new TopUpResponseDto(0L)

        and: "Current timestamp is provided"
        def ts = clock.instant()


        and: "TransferEntity is provided"
        TransferEntity transferEntity = TransferEntity.builder()
                .title("TOP_UP")
                .transferDate(ts)
                .idempotencyKey("1234")
                .type("TOP_UP")
                .createdAt(null)
                .updatedAt(null)
                .build()

        and: "AccountEntity is provided"
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

        and: "TransferEntity and AccountEntity is added to database"
        transferRepository.save(transferEntity)
        accountRepository.save(accountEntity)
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
