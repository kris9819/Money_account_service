package com.money_account_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class MoneyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyServiceApplication.class, args);
    }
}
