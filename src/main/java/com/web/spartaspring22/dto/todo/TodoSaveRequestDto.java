package com.web.spartaspring22.dto;

import lombok.Getter;

@Getter
public class TodoSaveRequestDto {

    private String title;
    private String contents;
    private String userName;

}
