package com.money_account_service.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.money_account_service.repositories.AccountRepository;
import com.money_account_service.repositories.TransferRepository;
import com.money_account_service.services.AccountService;
import com.money_account_service.services.AuthorizationService;
import com.money_account_service.services.TopUpService;
import com.money_account_service.services.TransferService;
import com.money_account_service.services.kafka.LedgerMessagePublisher;
import com.money_account_service.utility.UserServiceClient;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Clock;

@Configuration
public class AppConfig {

    @Bean
    public AccountService accountService(AccountRepository accountRepository) {
        return new AccountService(accountRepository);
    }

    @Bean
    public TransferService transferService(TransferRepository transferRepository, LedgerMessagePublisher ledgerMessagePublisher, Clock clock) {
        return new TransferService(transferRepository, ledgerMessagePublisher, clock);
    }

    @Bean
    public TopUpService topUpService(TransferRepository transferRepository, Clock clock) {
        return new TopUpService(transferRepository, clock);
    }

    @Bean
    public LedgerMessagePublisher ledgerMessagePublisher(KafkaTemplate kafkaTemplate) {
        return new LedgerMessagePublisher(kafkaTemplate);
    }

    @Bean
    public AuthorizationService authorizationService(UserServiceClient userServiceClient, AccountRepository accountRepository) {
        return new AuthorizationService(userServiceClient, accountRepository);
    }

    @Bean
    public UserServiceClient userServiceClient(OkHttpClient okHttpClient, ObjectMapper objectMapper) {
        return new UserServiceClient(okHttpClient, objectMapper);
    }

    @Bean
    public OkHttpClient okHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.hostnameVerifier((hostname, session) -> true);

        builder.sslSocketFactory(getSSLContext().getSocketFactory(), (X509TrustManager) getTrustManagers()[0]);

        return builder.build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    private SSLContext getSSLContext() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, getTrustManagers(), new SecureRandom());

        return sslContext;
    }

    private static TrustManager[] getTrustManagers() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };
    }
}
