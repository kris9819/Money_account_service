package com.money_account_service.repositories;

import com.money_account_service.entities.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransferRepository extends JpaRepository<TransferEntity, Long> {

    Optional<TransferRepository> findByIdempotencyKey(String idempotencyKey);
}
