package com.money_account_service.repositories;

import com.money_account_service.entities.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransferRepository extends JpaRepository<TransferEntity, Long> {

    Optional<TransferRepository> findByIdempotencyKey(String idempotencyKey);

    @Query(value = "SELECT t.transfer_id, t.title, t.transfer_date, t.idempotency_key, " +
            "t.type, t.created_at, t.updated_at FROM transfers t " +
            "INNER JOIN ledger l ON l.ledger_record_id = t.transfer_id " +
            "INNER JOIN accounts a ON a.account_id = l.debit_account_id " +
            "OR a.account_id = l.credit_account_id " +
            "WHERE a.user_sub = :userSub", nativeQuery = true)
    Optional<List<TransferEntity>> findAllTransfersForUser(@Param("userSub") String userSub);
}
