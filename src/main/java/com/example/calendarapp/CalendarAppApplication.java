package com.example.calendarapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing//자동 날짜 시간 기록 기능
public class CalendarAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalendarAppApplication.class, args);
    }

}
