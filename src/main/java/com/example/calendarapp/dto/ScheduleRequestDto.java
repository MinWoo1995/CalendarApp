package com.example.calendarapp.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {//클라이언트가 보낸 주문서
    private String title;
    private String content;
    private String username;
    private String password;
}
