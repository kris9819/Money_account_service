package com.money_account_service.dtos.response;

import lombok.Builder;

@Builder
public record AccountDetailsResponseDto (Long accountId, String accountNumber, Long balance, String currency) {

}
