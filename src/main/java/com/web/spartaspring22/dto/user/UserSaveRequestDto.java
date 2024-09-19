package com.web.spartaspring22.dto.user;

import lombok.Getter;

@Getter
public class UserSaveRequestDto {

    private Long userId;
    private String username;
    private String email;

}
