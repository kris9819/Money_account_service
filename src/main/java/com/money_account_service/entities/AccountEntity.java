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

import java.time.Clock;
import java.time.Instant;

@Table(name = "accounts")
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    public AccountEntity(String accountNumber, String currency, String userSub) {
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.userSub = userSub;
        this.createdAt = Clock.systemUTC().instant();
        this.updatedAt = Clock.systemUTC().instant();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "currency")
    private String currency;

    @Column(name = "user_sub")
    private String userSub;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

}
