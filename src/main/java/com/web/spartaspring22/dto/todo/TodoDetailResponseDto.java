package com.web.spartaspring22.dto.todo;

import com.web.spartaspring22.dto.user.UserDto;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class TodoDetailResponseDto {

    private final Long id;
    private final String title;
    private final String contents;
    private final UserDto uesr;
    private final int comentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public TodoDetailResponseDto(Long id, String title, String contents, UserDto user, int comentCount, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.uesr = user;
        this.comentCount = comentCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
