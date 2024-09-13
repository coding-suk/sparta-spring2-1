package com.web.spartaspring22.dto;

import lombok.Getter;

@Getter
public class TodoSaveResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final String userName;

    public TodoSaveResponseDto(Long id, String title, String contents, String userName) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.userName = userName;
    }
}
