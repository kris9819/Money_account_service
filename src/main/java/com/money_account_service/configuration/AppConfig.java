package com.money_account_service.configuration;

import com.money_account_service.repositories.AccountRepository;
import com.money_account_service.repositories.TopUpRepository;
import com.money_account_service.repositories.TransferRepository;
import com.money_account_service.services.AccountManagementService;
import com.money_account_service.utility.RestTemplateWrapper;
import com.money_account_service.utility.SSLCertificationHandling;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public AccountManagementService accountManagementService(TopUpRepository topUpRepository, TransferRepository transferRepository,
                                                             AccountRepository accountRepository, RestTemplateWrapper restTemplateWrapper) {
        return new AccountManagementService(topUpRepository, transferRepository, accountRepository, restTemplateWrapper);
    }

    @Bean
    public RestTemplateWrapper restTemplateWrapper(RestTemplate restTemplate) {
        return new RestTemplateWrapper(restTemplate);
    }

    @Bean RestTemplate restTemplate() {
        SSLCertificationHandling.ignoreCertificates();
        return new RestTemplate();
    }
}
