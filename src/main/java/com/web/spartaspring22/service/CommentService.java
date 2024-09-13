package com.web.spartaspring22.service;

import com.web.spartaspring22.dto.comment.*;
import com.web.spartaspring22.dto.user.UserDto;
import com.web.spartaspring22.entity.Comment;
import com.web.spartaspring22.entity.Todo;
import com.web.spartaspring22.entity.User;
import com.web.spartaspring22.repository.CommentRepository;
import com.web.spartaspring22.repository.TodoRepository;
import com.web.spartaspring22.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentSaveResponseDto saveComment(Long todoId, CommentSaveRequestDto requestDto) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(()-> new NullPointerException("할일이 없어요"));

        User user = userRepository.findById(requestDto.getId()).orElseThrow(()-> new NullPointerException("유저가 음써여"));

        Comment newComment = new Comment(requestDto.getComments(), user, todo);

        Comment savedComment = commentRepository.save(newComment);

        return new CommentSaveResponseDto(
                savedComment.getId(), savedComment.getComments(),
                new UserDto(user.getId(), user.getUsername(), user.getEmail()));
    }

    public List<CommentDetailResponseDto> getComments(Long todoId) {
        List<Comment> commentList = commentRepository.findByTodoId(todoId);

        List<CommentDetailResponseDto> dtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            User user = comment.getUser();
            CommentDetailResponseDto dto = new CommentDetailResponseDto(
                    comment.getId(), comment.getComments(),
                    new UserDto(user.getId(), user.getUsername(), user.getEmail()));
            dtoList.add(dto);
        }
        return dtoList;
    }

    public CommentDetailResponseDto getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new NullPointerException("댓글이 없어요"));

        User user = comment.getUser();

        return new CommentDetailResponseDto(comment.getId(), comment.getComments(),
                new UserDto(user.getId(), user.getUsername(), user.getEmail()));
    }

    public CommentUpdateResponseDto updateComment(Long commentId, CommentUpdateRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new NullPointerException("댓글이 없어요"));

        comment.update(requestDto.getComments());
        return new CommentUpdateResponseDto(comment.getId(), comment.getComments());
    }

    public void deleteComments(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new NullPointerException("댓글이 없어요");
        }

        commentRepository.deleteById(commentId);
    }
}
