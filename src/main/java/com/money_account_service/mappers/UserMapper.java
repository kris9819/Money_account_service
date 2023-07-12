package com.money_account_service.mappers;

import com.money_account_service.dtos.response.AuthorizeResponseDto;
import com.money_account_service.models.UserModel;

public class UserMapper {

    public static UserModel authorizeRequestDtoToUserModel (AuthorizeResponseDto authorizeResponseDto) {
        return UserModel.builder()
                .userSub(authorizeResponseDto.userSub())
                .email(authorizeResponseDto.email())
                .name(authorizeResponseDto.name())
                .build();
    }
}
