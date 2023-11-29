package com.money_account_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.Instant;

@Table(name = "transfers")
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferEntity {

    public TransferEntity(String title, String idempotencyKey, Long accountId, String type) {
        this.title = title;
        this.transferDate = Clock.systemUTC().instant();
        this.idempotencyKey = idempotencyKey;
        this.accountId = accountId;
        this.type = type;
        this.createdAt = Clock.systemUTC().instant();
        this.updatedAt = Clock.systemUTC().instant();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    private Long transferId;

    @Column(name = "title")
    private String title;

    @Column(name = "transfer_date")
    private Instant transferDate;

    @Column(name = "idempotency_key")
    private String idempotencyKey;

    @Column(name = "type")
    private String type;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
