package com.money_account_service.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.money_account_service.repositories.TransferRepository
import com.money_account_service.utility.UserServiceClient
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@Testcontainers
class BaseWeb extends Specification {

    @Autowired
    MockMvc mockMvc
    @Autowired
    ObjectMapper objectMapper
    @SpringBean
    UserServiceClient userServiceClient = Mock(UserServiceClient)
    AuthorizationService authorizationService

    static PostgreSQLContainer postgreSQL = new PostgreSQLContainer("postgres:latest")

    @Shared
    PostgreSQLContainer postgreSQLContainer = postgreSQL;

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        "spring.test.database.replace=none"
        registry.add("spring.datasource.url", postgreSQL::getJdbcUrl)
        registry.add("spring.datasource.username", postgreSQL::getUsername)
        registry.add("spring.datasource.password", postgreSQL::getPassword)
    }

    def setup() {
        authorizationService = new AuthorizationService(userServiceClient)
    }
}
