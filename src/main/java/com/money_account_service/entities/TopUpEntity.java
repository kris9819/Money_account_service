package com.money_account_service.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Table(name = "top_ups")
@Entity
@Builder
@Data
public class TopUpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "top_up_id")
    private Long topUpId;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "currency")
    private String currency;

    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;

    @Column(name = "action_date")
    private Instant actionDate;

    @Column(name = "idempotency_key")
    private String idempotencyKey;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
