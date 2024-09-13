package com.web.spartaspring22.controller;

import com.web.spartaspring22.dto.comment.*;
import com.web.spartaspring22.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/todos/{todoId}/comments")
    public CommentSaveResponseDto saveComment(@PathVariable Long todoId, @RequestBody CommentSaveRequestDto requestDto) {
        return commentService.saveComment(todoId, requestDto);
    }

    @GetMapping("/todos/{todoId}/comments")
    public List<CommentDetailResponseDto> getComments(@PathVariable Long todoId) {
        return commentService.getComments(todoId);
    }

    @GetMapping("/todos/comments/{commentId}")
    public CommentDetailResponseDto getComment(@PathVariable Long commentId) {
        return commentService.getComment(commentId);
    }

    @PutMapping("todos/comments/{commentId}")
    public CommentUpdateResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto requestDto) {
        return commentService.updateComment(commentId, requestDto);
    }

    @DeleteMapping("/todos/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComments(commentId);
    }

}
