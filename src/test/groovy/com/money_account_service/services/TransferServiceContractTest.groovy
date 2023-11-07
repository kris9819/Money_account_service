package com.money_account_service.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.money_account_service.dtos.request.TopUpRequestDto
import com.money_account_service.dtos.request.TransferRequestDto
import com.money_account_service.dtos.response.AuthorizeResponseDto
import com.money_account_service.dtos.response.TopUpResponseDto
import com.money_account_service.dtos.response.TransferResponseDto
import com.money_account_service.dtos.response.TransfersResponseDto
import com.money_account_service.entities.TransferEntity
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

class TransferServiceContractTest extends BaseWeb {

    @Autowired
    private TransferRepository transferRepository
    private TransferService transferService

    def setup() {
        transferService = new TransferService(transferRepository)
    }

    def "Should do transfer to given account"() {
        given: "TransferRequestDto is provided"
        TransferRequestDto transferRequestDto = new TransferRequestDto("test", "123", "transfer", 0L, "123")

        and: "TransferResponseDto is provided"
        TransferResponseDto transferResponseDto = new TransferResponseDto("???", "???", "transfer", Clock.systemUTC().instant(),0L)

        and: "AuthorizeResponseDto is provided"
        AuthorizeResponseDto authorizeResponseDto = AuthorizeResponseDto.builder()
                .email("mail@mail.com")
                .name("kris")
                .userSub("123")
                .build()

        userServiceClient.authorize(_) >> authorizeResponseDto

        expect: "API call response is asserted"
        mockMvc.perform(MockMvcRequestBuilders.post("/transfer")
                .contentType("application/json")
                .header((HttpHeaders.AUTHORIZATION), "Bearer 123")
                .content(objectMapper.writeValueAsString(transferRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .response
                .contentAsString == objectMapper.writeValueAsString(transferResponseDto)
    }


    def "Should return list of transactions for given account"() {
        given: "TransferEntities is provided"

        TransferEntity transferEntity1 = TransferEntity.builder()
                .transferId(1)
                .title("transfer1")
                .transferDate(null)
                .idempotencyKey("123")
                .type("TRANSFER")
                .createdAt(null)
                .updatedAt(null)
                .build()

        TransferEntity transferEntity2 = TransferEntity.builder()
                .transferId(2)
                .title("transfer2")
                .transferDate(null)
                .idempotencyKey("1234")
                .type("TRANSFER")
                .createdAt(null)
                .updatedAt(null)
                .build()

        and: "TransferResponseDto is provided"
        TransfersResponseDto transfersResponseDto = new TransfersResponseDto(List.of(transferEntity1, transferEntity2))

        and: "AuthorizeResponseDto is provided"
        AuthorizeResponseDto authorizeResponseDto = AuthorizeResponseDto.builder()
                .email("mail@mail.com")
                .name("kris")
                .userSub("123")
                .build()

        and: "TransferEntities are added to database"
        transferRepository.saveAll(List.of(transferEntity1, transferEntity2))
        userServiceClient.authorize(_) >> authorizeResponseDto

        expect: "API call response is asserted"
        mockMvc.perform(MockMvcRequestBuilders.get("/transfers")
                .header((HttpHeaders.AUTHORIZATION), "Bearer 123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .response
                .contentAsString == objectMapper.writeValueAsString(transfersResponseDto)
    }


    def "Should return transaction with given id"() {
        given: "TransferEntities is provided"

        TransferEntity transferEntity = TransferEntity.builder()
                .transferId(1)
                .title("transfer1")
                .transferDate(null)
                .idempotencyKey("123")
                .type("TRANSFER")
                .createdAt(null)
                .updatedAt(null)
                .build()


        and: "TransferResponseDto is provided"
        TransferResponseDto transferResponseDto = new TransferResponseDto("???", "???", transferEntity.title, transferEntity.transferDate, 0L)

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
        mockMvc.perform(MockMvcRequestBuilders.get("/transfers/{id}", 1L)
                .header((HttpHeaders.AUTHORIZATION), "Bearer 123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .response
                .contentAsString == objectMapper.writeValueAsString(transferResponseDto)
    }

}
