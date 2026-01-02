package com.example.calendarapp.dto;

import com.example.calendarapp.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String content;
    private String username;
    private Long scheduleId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = comment.getUsername();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
        //this.scheduleId= comment.getScheduleId();

        //Comment에 scheduleId라는 필드가 없음으로, 연결된 schedule 객체에서 Id를 가져온다
        if(comment.getSchedule() != null) {
            this.scheduleId = comment.getSchedule().getId();
        }
    }
}
