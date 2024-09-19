package com.web.spartaspring22.dto.user;

import lombok.Getter;

@Getter
public class UserSaveResponseDto {

    private final Long id;
    private final String username;
    private final String email;
    private final String token;

    public UserSaveResponseDto(Long id, String username, String email, String token) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.token = token;
    }
}
