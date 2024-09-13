package com.web.spartaspring22.dto;

import lombok.Getter;

@Getter
public class TodoDetailResponseDto {

    private final Long id;
    private final String title;
    private final String contents;
    private final String uesrName;

    public TodoDetailResponseDto(Long id, String title, String contents, String uesrName) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.uesrName = uesrName;
    }
}
