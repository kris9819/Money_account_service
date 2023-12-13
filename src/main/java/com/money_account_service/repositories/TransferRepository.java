package com.money_account_service.repositories;

import com.money_account_service.entities.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransferRepository extends JpaRepository<TransferEntity, Long> {

    Optional<TransferEntity> findByIdempotencyKey(String idempotencyKey);

    @Query(value = "SELECT t.transfer_id, t.title, t.transfer_date, t.idempotency_key, " +
            "t.account_id, t.type, t.created_at, t.updated_at FROM transfers t " +
            "WHERE t.account_id = :accountId", nativeQuery = true)
    List<TransferEntity> findAllTransfersForUser(@Param("accountId") Long accountId);

}
