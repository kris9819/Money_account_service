package com.money_account_service.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.money_account_service.repositories.AccountRepository;
import com.money_account_service.repositories.TransferRepository;
import com.money_account_service.services.AccountService;
import com.money_account_service.services.AuthorizationService;
import com.money_account_service.services.TopUpService;
import com.money_account_service.services.TransferService;
import com.money_account_service.utility.UserServiceClient;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class AppConfig {

    @Bean
    public AccountService accountService(AccountRepository accountRepository) {
        return new AccountService(accountRepository);
    }

    @Bean
    public TransferService transferService(TransferRepository transferRepository, UserServiceClient userServiceClient) {
        return new TransferService(transferRepository, userServiceClient);
    }

    @Bean
    public TopUpService topUpService(UserServiceClient userServiceClient) {
        return new TopUpService(userServiceClient);
    }

    @Bean
    public AuthorizationService authorizationService(UserServiceClient userServiceClient) {
        return new AuthorizationService(userServiceClient);
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
        return new ObjectMapper();
    }

    private SSLContext getSSLContext() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, getTrustManagers(), new java.security.SecureRandom());

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
