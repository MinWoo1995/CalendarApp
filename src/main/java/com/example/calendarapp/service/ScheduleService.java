package com.example.calendarapp.service;

import com.example.calendarapp.dto.ScheduleRequestDto;
import com.example.calendarapp.dto.ScheduleResponseDto;
import com.example.calendarapp.entity.Schedule;
import com.example.calendarapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // 이 클래스가 "주방장(Service)"임을 스프링에게 알림
@RequiredArgsConstructor // Repository를 주입받기 위해 사용
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;//창고 관리자 호출

    @Transactional//트렌젝션 단위로 묶기
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {
        // 1. [요리 시작] 사용자가 준 접시(DTO)에서 재료를 꺼내 실제 식재료(Entity)를 만듭니다.
        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getContent(),
                requestDto.getUsername(),
                requestDto.getPassword()
        );
                // 2. [창고 저장] 창고 관리자에게 식재료를 보관하라고 시킵니다.
                // 이때 DB가 ID와 생성/수정 시간을 자동으로 채워준 완성본을 돌려줍니다.
                Schedule savedSchedule = scheduleRepository.save(schedule);

        // 3. [서빙 준비] 완성된 식재료를 다시 예쁜 접시(Response DTO)에 담아 매니저(Controller)에게 전달합니다.
        return new ScheduleResponseDto(savedSchedule);
    };

}
