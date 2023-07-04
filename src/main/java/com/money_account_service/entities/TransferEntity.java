package com.money_account_service.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Table(name = "transfers")
@Entity
@Builder
@Data
public class TransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    private Long transferId;

    @Column(name = "title")
    private String title;

    @Column(name = "transaction_date")
    private Instant transactionDate;

    @Column(name = "idempotency_key")
    private String idempotencyKey;

    @Column(name = "ledger_entry_id")
    private Long ledgerEntryId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}