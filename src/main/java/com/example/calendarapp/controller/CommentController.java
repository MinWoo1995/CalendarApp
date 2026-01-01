package com.example.calendarapp.controller;

import com.example.calendarapp.dto.CommentRequestDto;
import com.example.calendarapp.dto.CommentResponseDto;
import com.example.calendarapp.dto.ScheduleRequestDto;
import com.example.calendarapp.dto.ScheduleResponseDto;
import com.example.calendarapp.service.CommentService;
import com.example.calendarapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService; // 주방장(Service) 호출 준비

    //댓글 생성
    @PostMapping
    public CommentResponseDto createCount(@RequestBody CommentRequestDto requestDto) {
        return commentService.saveComment(requestDto);
    }
}
