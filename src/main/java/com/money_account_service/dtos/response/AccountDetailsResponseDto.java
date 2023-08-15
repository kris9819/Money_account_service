package com.money_account_service.dtos.response;

import lombok.Builder;

@Builder
public record AccountDetailsResponseDto (String accountNumber, Long balance, String currency) {

}
