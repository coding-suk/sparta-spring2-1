package com.web.spartaspring22.dto.todo;

import lombok.Getter;

@Getter
public class TodoUpdateRequestDto {

    private String title;
    private String contents;
    private String userName;

}
