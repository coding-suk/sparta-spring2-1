package com.web.spartaspring22.service;

import com.web.spartaspring22.dto.manager.ManagerSaveRequestDto;
import com.web.spartaspring22.dto.manager.ManagerSaveResponseDto;
import com.web.spartaspring22.dto.manager.ManagerSimpleResponseDto;
import com.web.spartaspring22.dto.user.UserDto;
import com.web.spartaspring22.entity.Manager;
import com.web.spartaspring22.entity.Todo;
import com.web.spartaspring22.entity.User;
import com.web.spartaspring22.repository.ManagerRepository;
import com.web.spartaspring22.repository.TodoRepository;
import com.web.spartaspring22.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagerService {

    private final TodoRepository todoRepository;
    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;

    @Transactional
    public ManagerSaveResponseDto saveManager(Long todoId, ManagerSaveRequestDto requestDto) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(()->
                new NullPointerException("사용자가 없어요"));

        User user = userRepository.findById(requestDto.getTodoUserId()).orElseThrow(()->
                new NullPointerException("일정만든 유저가 아님"));

        if(!(todo.getUser() != null && ObjectUtils.nullSafeEquals(user.getId(), todo.getUser().getId()))) {
            throw new NullPointerException("일정을 만든 유저가 아님");
        }

        User manager = userRepository.findById(requestDto.getManagerUserId()).orElseThrow(()->
                new NullPointerException("사용자가 음써요"));

        Manager newManager = new Manager(manager, todo);
        Manager savedManager = managerRepository.save(newManager);

        return new ManagerSaveResponseDto(savedManager.getId());
    }

    public List<ManagerSimpleResponseDto> getManagers(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(()->
                new NullPointerException("사용자가 없어요"));

        List<Manager> managerList = managerRepository.findByTodoId(todo.getId());

        List<ManagerSimpleResponseDto> dtoList = new ArrayList<>();
        for (Manager manager : managerList) {
            User user = manager.getUser();
            ManagerSimpleResponseDto dto = new ManagerSimpleResponseDto(manager.getId(),
                    new UserDto(user.getId(), user.getUsername(), user.getEmail()));
            dtoList.add(dto);
        }
        return dtoList;
    }
}
