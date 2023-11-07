package com.money_account_service.services;

import com.money_account_service.dtos.request.TransferRequestDto;
import com.money_account_service.entities.TransferEntity;
import com.money_account_service.models.UserModel;
import com.money_account_service.repositories.TransferRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class TransferService {

    private TransferRepository transferRepository;

    public TransferEntity transfer(TransferRequestDto transferRequestDto, UserModel userModel) {
        TransferEntity transferEntity = new TransferEntity(transferRequestDto.title(), transferRequestDto.idempotencyKey(), "TRANSFER");

        Optional<TransferEntity> transferEntityFoundByIdempotencyKey = Optional.ofNullable(transferRepository.findByIdempotencyKey(transferEntity.getIdempotencyKey()));

        if (transferEntityFoundByIdempotencyKey.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This transaction already exists");
        }
        return transferRepository.save(transferEntity);
    }

    public TransferEntity getTransferDetails(Long id, UserModel userModel) {
        Optional<TransferEntity> transferEntityOptional = transferRepository.findById(id);

        return transferEntityOptional.orElseThrow(
                () ->new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Transaction with %d id doesn't exist", id)));
    }

    public List<TransferEntity> getTransferHistory(UserModel userModel) {
        Optional<List<TransferEntity>> optionalTransferEntityList = Optional.ofNullable(transferRepository.findAllTransfersForUser(userModel.userSub()));

        return optionalTransferEntityList.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "You don't have any transactions yet"));
    }
}
