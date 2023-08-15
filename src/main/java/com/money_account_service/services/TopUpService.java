package com.money_account_service.services;

import com.money_account_service.dtos.request.TopUpRequestDto;
import com.money_account_service.entities.TransferEntity;
import com.money_account_service.models.UserModel;
import com.money_account_service.repositories.TransferRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
public class TopUpService {

    private TransferRepository transferRepository;
    @Transactional
    public TransferEntity topUp(TopUpRequestDto topUpRequestDto, UserModel userModel) {
        TransferEntity transferEntity = new TransferEntity("TOP_UP", topUpRequestDto.idempotencyKey(), "TOP_UP");

        if (transferRepository.findByIdempotencyKey(transferEntity.getIdempotencyKey()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This transaction already exists");
        }

        return transferRepository.save(transferEntity);
    }
}
