package com.web.spartaspring22.dto.comment;

import lombok.Getter;

@Getter
public class CommentUpdateResponseDto {

    private final Long id;
    private final String comments;

    public CommentUpdateResponseDto(Long id, String comments) {
        this.id = id;
        this.comments = comments;
    }
}
