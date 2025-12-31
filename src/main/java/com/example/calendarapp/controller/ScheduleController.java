package com.example.calendarapp.controller;

import com.example.calendarapp.dto.ScheduleRequestDto;
import com.example.calendarapp.dto.ScheduleResponseDto;
import com.example.calendarapp.entity.Schedule;
import com.example.calendarapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON 형태로 응답을 보낼 때 사용//클라이언트가 제이슨으로 받아야 구현이 가능하기 때문
@RequestMapping("/api/schedules") // 공통 경로 설정//외부에서 api를 접근하는 경로(여기로 보내 주세요)
@RequiredArgsConstructor // 생성자 주입을 위해 사용 (Service 연결용)//new없이 사용하기위해서
//의존성의 문제인데 @RequiredArgsConstructor 없이 new해서 생성하게 되면, 스프링 관리를 벗어난 야생의 객체가 되고 문제시 스프링의 관리밖의
//영역의 문제가 발생하게 되고, 트렌젝션시 적용되지 않는 문제도 발생
public class ScheduleController {
    private final ScheduleService scheduleService; // 주방장(Service) 호출 준비

    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        //@RequestBody는 요청온 내용이 스케줄요청디티오 타입의 리퀘스트 디티오이다
        //클라이언트가 http 바디라는 상자에서 스프링이 이 상자를 뜯어서 ScheduleRequestDto양식에 맞춰 쏙쏙 넣어준다
        // 매니저(Controller)는 주문(DTO)을 받아서 주방장(Service)에게 넘깁니다.
        return scheduleService.saveSchedule(requestDto);
    }
    //스케줄생성이라는 메소드를 스케줄응답디티오 타입으로 반환을 할건데,
    //필요한 매개변수가, 스케줄요청디티오 타입형태의 리퀘스트디티오를 받아서
    //스케줄서비스 클래스의 세이브스캐줄 메서드를 실행하는 매개변수로 던저주면서 이 메서드는 끝이난다
    //응답타입으로 메소드를 정의하는 이유는 최종 레퍼지토리를 다녀온 데이터를 클라이언트까지 다시 전달하기 위해서
}
