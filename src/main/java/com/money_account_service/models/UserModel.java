package com.money_account_service.models;

import lombok.Builder;

@Builder
public record UserModel (String userSub, String email, String name) {
}
