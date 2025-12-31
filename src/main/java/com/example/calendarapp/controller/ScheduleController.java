package com.example.calendarapp.controller;

import com.example.calendarapp.dto.ScheduleRequestDto;
import com.example.calendarapp.dto.ScheduleResponseDto;
import com.example.calendarapp.entity.Schedule;
import com.example.calendarapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // JSON 형태로 응답을 보낼 때 사용//클라이언트가 제이슨으로 받아야 구현이 가능하기 때문
@RequestMapping("/api/schedules") // 공통 경로 설정//외부에서 api를 접근하는 경로(여기로 보내 주세요)
@RequiredArgsConstructor // 생성자 주입을 위해 사용 (Service 연결용)//new없이 사용하기위해서
//의존성의 문제인데 @RequiredArgsConstructor 없이 new해서 생성하게 되면, 스프링 관리를 벗어난 야생의 객체가 되고 문제시 스프링의 관리밖의
//영역의 문제가 발생하게 되고, 트렌젝션시 적용되지 않는 문제도 발생
public class ScheduleController {
    private final ScheduleService scheduleService; // 주방장(Service) 호출 준비

    //스케줄 생성
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

    //스케줄 단건 조회
    @GetMapping("/{scheduleId}")//이런식의 주소로 Get이 온다면 이 메서드로 처리 하겠다는 뜻
    public ResponseEntity<ScheduleResponseDto> getOneSchedule(@PathVariable Long scheduleId){
        //ResponseEntity 봉투에 ScheduleResponseDto 내용을 담아 돌려주는 getOneSchedule메서드 라는뜻
        //ResponseEntity인 이유는 단순히 데이터만 주는 게 아니라, "성공(200 OK)" 같은 상태 코드까지 깔끔하게 포장해서 보내기 위해서
        //@PathVariable는 주소창에 써진 {scheduleId} 값을 가져와서 Long scheduleId 여기에 담으라는 뜻
        ScheduleResponseDto result = scheduleService.getOneSchedule(scheduleId);
        //scheduleService 클래스에 getOneSchedule메서드를 scheduleId값을 넣어 실행하고 결과를 result에 담아라
        return ResponseEntity.status(HttpStatus.OK).body(result);
        //body(result)결과물을 status(HttpStatus.OK)를 표시하여 ResponseEntity봉투에 담아 클라이언트에세 응답을 줘라

    }
}
