package com.web.spartaspring22.service;

import com.web.spartaspring22.config.JwtUtil;
import com.web.spartaspring22.dto.user.UserDetailResponseDto;
import com.web.spartaspring22.dto.user.UserSaveRequestDto;
import com.web.spartaspring22.dto.user.UserSaveResponseDto;
import com.web.spartaspring22.dto.user.UserSimpleResponseDto;
import com.web.spartaspring22.entity.User;
import com.web.spartaspring22.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public UserSaveResponseDto saveUser(UserSaveRequestDto requestDto) {

        if(userRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("이메일은 이미 사용");
        }

        User newUser = new User(requestDto.getUsername(), requestDto.getEmail());

        User savedUser = userRepository.save(newUser);

        return new UserSaveResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());

    }

    public List<UserSimpleResponseDto> getUsers() {
        List<User> userList = userRepository.findAll();

        List<UserSimpleResponseDto> dtoList = new ArrayList<>();
        for (User user : userList) {
            UserSimpleResponseDto dto = new UserSimpleResponseDto(user.getId(), user.getUsername());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public UserDetailResponseDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new NullPointerException("사용자가 없어요"));

        return new UserDetailResponseDto(user.getId(), user.getUsername(), user.getEmail());

    }

    public void delete(Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new NullPointerException("사용자가 없어요");
        }

        userRepository.deleteById(userId);
    }
}
