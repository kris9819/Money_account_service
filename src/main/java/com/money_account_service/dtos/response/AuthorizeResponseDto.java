package com.money_account_service.dtos.response;

import lombok.Builder;

@Builder
public record AuthorizeResponseDto (String userSub, Boolean authorized){
}
