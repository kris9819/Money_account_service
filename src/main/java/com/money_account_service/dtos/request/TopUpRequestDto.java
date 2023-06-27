package com.money_account_service.dtos.request;

import java.math.BigDecimal;

public record TopUpRequestDto(BigDecimal balance, String currency, String idempotencyKey, String accessToken) {
}

//TODO metoda authorize() zwracałaby nazwe użytkownika, wtedy nie trzeba jej przekazywać w request. Czy lepiej przekazywac
// to w request body