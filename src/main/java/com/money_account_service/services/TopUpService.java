package com.money_account_service.services;

import com.money_account_service.dtos.request.TopUpRequestDto;
import com.money_account_service.entities.TransferEntity;
import com.money_account_service.repositories.TransferRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Clock;
import java.util.Optional;

@AllArgsConstructor
public class TopUpService {

    private TransferRepository transferRepository;

    private Clock clock;
    @Transactional
    public TransferEntity topUp(TopUpRequestDto topUpRequestDto, Long accountId) {
        TransferEntity transferEntity = new TransferEntity("TOP_UP", topUpRequestDto.idempotencyKey(), accountId, "TOP_UP", clock);

        Optional<TransferEntity> transferEntityFoundByIdempotencyKey = transferRepository.findByIdempotencyKey(transferEntity.getIdempotencyKey());

        if (transferEntityFoundByIdempotencyKey.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This transaction already exists");
        }

        return transferRepository.save(transferEntity);
    }
}
