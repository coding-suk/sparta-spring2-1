package com.web.spartaspring22.dto;

import lombok.Getter;

@Getter
public class CommentSaveResponseDto {

    private final Long id;
    private final String comments;
    private final String username;

    public CommentSaveResponseDto(Long id, String comments, String username) {
        this.id = id;
        this.comments = comments;
        this.username = username;
    }
}
