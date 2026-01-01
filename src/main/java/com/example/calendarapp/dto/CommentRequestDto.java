package com.example.calendarapp.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String content;
    private String username;
    private String password;
    private Long scheduleId;
}
