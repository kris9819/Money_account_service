package com.money_account_service.services;

import com.money_account_service.repositories.TransferRepository;
import com.money_account_service.utility.UserServiceClient;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransferService {

    private TransferRepository transferRepository;
    private UserServiceClient userServiceClient;


//    public TransferResponseDto transfer(TransferRequestDto transferRequestDto) {
//        if (authorizeRequest(transferRequestDto.accessToken()).authorized()) {
////            TransferEntity transferEntity = transferRepository.save(RequestMapper.transferRequestToTransferEntity(transferRequestDto));
////            return ResponseMapper.transferEntityToTransferResponse(transferEntity);
//            return null;
//        }
//        return null;
//    }
//
//    //    public TransferResponseDto getTransferDetails(Long id) {
////        Optional<TransferEntity> transferEntityOptional = transferRepository.findById(id);
////        return transferEntityOptional.map(ResponseMapper::transferEntityToTransferResponse).orElse(null);
////    }
//
//    private AuthorizeResponseDto authorizeRequest(String accessToken) {
//        Optional<AuthorizeResponseDto> authorizeResponseDtoOptional = restTemplateWrapper.authorizeRequest(
//                new AuthorizeRequestDto(accessToken));
//
//        if (authorizeResponseDtoOptional.isPresent()) {
//            return authorizeResponseDtoOptional.get();
//        } else {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Failed to authorize request");
//        }
//    }
}
