package com.money_account_service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Table(name = "ledger")
@Entity
@Builder
@Data
public class LedgerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ledger_id")
    private Long ledgerId;

    @Column(name = "debit_account_id")
    private String debitAccountId;

    @Column(name = "credit_account_id")
    private String creditAccountId;

    @Column(name = "debit_amount")
    private BigDecimal debitAmount;

    @Column(name = "debit_currency")
    private String debitCurrency;

    @Column(name = "credit_amount")
    private BigDecimal creditAmount;

    @Column(name = "credit_currency")
    private String creditCurrency;

    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;

    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "top_up_id")
    private Long topUpId;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
