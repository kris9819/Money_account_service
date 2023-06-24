package com.money_account_service.dtos.response;

import java.math.BigDecimal;

public record AccountDetailsResponseDto (String accountNumber, BigDecimal balance, String currency) {

}
