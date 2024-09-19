package com.web.spartaspring22.controller;

import com.web.spartaspring22.config.JwtUtil;
import com.web.spartaspring22.dto.user.UserDetailResponseDto;
import com.web.spartaspring22.dto.user.UserSaveRequestDto;
import com.web.spartaspring22.dto.user.UserSaveResponseDto;
import com.web.spartaspring22.dto.user.UserSimpleResponseDto;
import com.web.spartaspring22.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/users")
    public ResponseEntity<UserSaveResponseDto> saveUser(@RequestBody UserSaveRequestDto requestDto) {
        UserSaveResponseDto responseDto = userService.saveUser(requestDto);

        String token = jwtUtil.createToken(requestDto.getUserId());

        return ResponseEntity
                .ok()
                .header("Authorization", "Bearer " + token)
                .build();

    }

    @GetMapping("/users")
    public List<UserSimpleResponseDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{userId}")
    public UserDetailResponseDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
    }

}
