package com.web.spartaspring22.controller;

import com.web.spartaspring22.dto.todo.*;
import com.web.spartaspring22.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public TodoSaveResponseDto saveTodo(@RequestBody TodoSaveRequestDto requestDto) {
        return todoService.saveTodo(requestDto);
    }

    @GetMapping("/todos")
    public Page<TodoSimpleResponseDto> getTodos(
            @RequestParam(defaultValue = "1")int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return todoService.getTodos(page, size);
    }

    @GetMapping("/todos/{todoId}")
    public TodoDetailResponseDto getTodo(@PathVariable Long todoId) {
        return todoService.getTodo(todoId);
    }

    @PutMapping("/todos/{todoId}")
    public TodoUpdateResponseDto updateTodo(@PathVariable Long todoId, @RequestBody TodoUpdateRequestDto requestDto) {
        return todoService.updateTodo(todoId, requestDto);
    }

}
