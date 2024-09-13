package com.web.spartaspring22.dto.comment;

import com.web.spartaspring22.dto.user.UserDto;
import lombok.Getter;

@Getter
public class CommentSaveResponseDto {

    private final Long id;
    private final String comments;
    private final UserDto user;

    public CommentSaveResponseDto(Long id, String comments, UserDto user) {
        this.id = id;
        this.comments = comments;
        this.user = user;
    }
}
