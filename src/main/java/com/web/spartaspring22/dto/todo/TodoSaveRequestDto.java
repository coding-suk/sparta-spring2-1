package com.web.spartaspring22.dto.todo;

import lombok.Getter;

@Getter
public class TodoSaveRequestDto {

    private String title;
    private String contents;
    private Long userId;

}
