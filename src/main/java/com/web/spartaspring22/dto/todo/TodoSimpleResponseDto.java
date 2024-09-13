package com.web.spartaspring22.dto.todo;

import com.web.spartaspring22.dto.user.UserDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoSimpleResponseDto {

    private final Long id;
    private final String title;
    private final String contents;
    private final int commentCount;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public TodoSimpleResponseDto(Long id, String title, String contents, int commentCount, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.commentCount = commentCount;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
