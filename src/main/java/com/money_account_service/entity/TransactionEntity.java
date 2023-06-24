package com.money_account_service.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Table(name = "transactions")
@Entity
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "title")
    private String title;

    @Column(name = "transaction_date")
    private Instant transactionDate;

    @Column(name = "idempotency_key")
    private String idempotencyKey;

    @Column(name = "ledger_entry_id")
    private Long ledgerEntryId;

}
// jak to przechowywać, czy chcemy mieć 2 foreign key do kont użytkowników czy trzymamy np. sama nazwe
// jak powiązać trasakcje przez tabele accounts czy ledger