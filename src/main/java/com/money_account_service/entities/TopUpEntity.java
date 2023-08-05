//package com.money_account_service.entities;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.Instant;
//
//@Table(name = "top_ups")
//@Entity
//@Builder
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class TopUpEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "top_up_id")
//    private Long topUpId;
//
//    @Column(name = "balance")
//    private Long balance;
//
//    @Column(name = "currency")
//    private String currency;
//
//    @Column(name = "exchange_rate")
//    private Long exchangeRate;
//
//    @Column(name = "action_date")
//    private Instant actionDate;
//
//    @Column(name = "idempotency_key")
//    private String idempotencyKey;
//
//    @Column(name = "account_id")
//    private String accountId;
//
//    @Column(name = "created_at")
//    private Instant createdAt;
//
//    @Column(name = "updated_at")
//    private Instant updatedAt;
//}
