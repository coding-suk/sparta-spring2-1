package com.web.spartaspring22.service;

import com.web.spartaspring22.dto.todo.*;
import com.web.spartaspring22.dto.user.UserDto;
import com.web.spartaspring22.entity.Todo;
import com.web.spartaspring22.entity.User;
import com.web.spartaspring22.repository.TodoRepository;
import com.web.spartaspring22.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    @Transactional
    public TodoSaveResponseDto saveTodo(TodoSaveRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(()-> new NullPointerException("없는 유저입니다"));

        Todo newTodo = new Todo(requestDto.getTitle(), requestDto.getContents(), user);

        Todo savedTodo = todoRepository.save(newTodo);

        return new TodoSaveResponseDto(
                savedTodo.getId(), savedTodo.getTitle(), savedTodo.getContents(),
                new UserDto(user.getId(), user.getUsername(), user.getEmail()));
    }

    public Page<TodoSimpleResponseDto> getTodos(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Todo> todos = todoRepository.findAllByOrderByModifiedAtDesc(pageable);

        return todos.map(todo ->  new TodoSimpleResponseDto(
                todo.getId(),
                todo.getTitle(),
                todo.getContents(),
                todo.getComments().size(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
                )
        );
    }

    public TodoDetailResponseDto getTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(()-> new NullPointerException("할일이 없어요"));

        User user = todo.getUser();

        return new TodoDetailResponseDto(
                todo.getId(),
                todo.getTitle(),
                todo.getContents(),
                new UserDto(user.getId(), user.getUsername(), user.getEmail()),
                todo.getComments().size() ,
                todo.getCreatedAt(),
                todo.getModifiedAt());

    }

    @Transactional
    public TodoUpdateResponseDto updateTodo(Long todoId, TodoUpdateRequestDto requestDto) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(()-> new NullPointerException("할일이 없어요"));

        todo.update(requestDto.getTitle(),requestDto.getContents());

        return new TodoUpdateResponseDto(todo.getId(), todo.getTitle(), todo.getContents());
    }

}
