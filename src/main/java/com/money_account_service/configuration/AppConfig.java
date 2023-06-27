package com.money_account_service.configuration;

import com.money_account_service.repositories.AccountRepository;
import com.money_account_service.repositories.TopUpRepository;
import com.money_account_service.repositories.TransferRepository;
import com.money_account_service.repositories.UserRepository;
import com.money_account_service.services.AccountManagementService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public AccountManagementService accountManagementService(TopUpRepository topUpRepository, TransferRepository transferRepository,
                                                             AccountRepository accountRepository, UserRepository userRepository) {
        return new AccountManagementService(topUpRepository, transferRepository, accountRepository);
    }
}
