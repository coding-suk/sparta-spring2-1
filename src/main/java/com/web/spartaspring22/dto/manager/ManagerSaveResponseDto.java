package com.web.spartaspring22.dto.manager;

import lombok.Getter;

@Getter
public class ManagerSaveResponseDto {

    private final Long id;

    public ManagerSaveResponseDto(Long id) {
        this.id = id;
    }
}
