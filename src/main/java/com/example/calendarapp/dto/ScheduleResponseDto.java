package com.example.calendarapp.dto;

import com.example.calendarapp.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();//DB에서 생성된 아이디 번호 담아서 응답보내기
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.username = schedule.getUsername();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
        //패스워드는 포함하지 않는다.
    }
}
