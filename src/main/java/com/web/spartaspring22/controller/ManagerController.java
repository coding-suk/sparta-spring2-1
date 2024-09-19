package com.web.spartaspring22.controller;

import com.web.spartaspring22.dto.manager.ManagerSaveRequestDto;
import com.web.spartaspring22.dto.manager.ManagerSaveResponseDto;
import com.web.spartaspring22.dto.manager.ManagerSimpleResponseDto;
import com.web.spartaspring22.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping("/todos/{todoId}/managers")
    public ManagerSaveResponseDto saveManager(@PathVariable Long todoId, @RequestBody ManagerSaveRequestDto requestDto) {
        return managerService.saveManager(todoId, requestDto);
    }

    @GetMapping("/todos/{todoId}/managers")
    public List<ManagerSimpleResponseDto> getManagers(@PathVariable Long todoId) {
        return managerService.getManagers(todoId);
    }

}
