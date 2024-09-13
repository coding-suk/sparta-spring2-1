package com.web.spartaspring22.dto.todo;

import com.web.spartaspring22.dto.user.UserDto;
import com.web.spartaspring22.entity.User;
import lombok.Getter;

@Getter
public class TodoSaveResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final UserDto user;

    public TodoSaveResponseDto(Long id, String title, String contents, UserDto user) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.user = user;
    }
}
