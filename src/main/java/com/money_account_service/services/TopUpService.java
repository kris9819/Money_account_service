package com.money_account_service.services;

import com.money_account_service.repositories.TopUpRepository;
import com.money_account_service.utility.UserServiceClient;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TopUpService {

    private TopUpRepository topUpRepository;
    private UserServiceClient userServiceClient;


//    public TopUpResponseDto topUp(TopUpRequestDto topUpRequestDto) {
////        if (authorizeRequest(topUpRequestDto.accessToken()).authorized()) {
//////            TopUpEntity topUpEntity = topUpRepository.save(RequestMapper.topUpRequestToTopUpEntity(topUpRequestDto));
//////            return ResponseMapper.topUpEntityToTopUpResponse(topUpEntity);
////            return null;
////        }
////        return null;
//    }
//
//    private AuthorizeResponseDto authorizeRequest(String accessToken) {
////        Optional<AuthorizeResponseDto> authorizeResponseDtoOptional = restTemplateWrapper.authorizeRequest(
////                new AuthorizeRequestDto(accessToken));
////
////        if (authorizeResponseDtoOptional.isPresent()) {
////            return authorizeResponseDtoOptional.get();
////        } else {
////            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Failed to authorize request");
////        }
//    }
}
