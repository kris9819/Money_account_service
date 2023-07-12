package com.money_account_service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Table(name = "ledger")
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LedgerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ledger_record_id")
    private Long ledgerRecordId;

    @Column(name = "debit_account_id")
    private String debitAccountId;

    @Column(name = "credit_account_id")
    private String creditAccountId;

    @Column(name = "debit_amount")
    private Long debitAmount;

    @Column(name = "debit_currency")
    private String debitCurrency;

    @Column(name = "credit_amount")
    private Long creditAmount;

    @Column(name = "credit_currency")
    private String creditCurrency;

    @Column(name = "exchange_rate")
    private Long exchangeRate;

    @Column(name = "transfer_id")
    private Long transferId;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
