package com.money_account_service.utility;

import java.time.Instant;
import java.time.LocalDateTime;

public final class DateTimeProvider {

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

    public Instant getInstant() {
        return Instant.now();
    }
}
