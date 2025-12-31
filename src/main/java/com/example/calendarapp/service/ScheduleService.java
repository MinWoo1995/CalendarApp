package com.example.calendarapp.service;

import com.example.calendarapp.dto.ScheduleRequestDto;
import com.example.calendarapp.dto.ScheduleResponseDto;
import com.example.calendarapp.entity.Schedule;
import com.example.calendarapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static aQute.bnd.annotation.headers.Category.users;

@Service // 이 클래스가 "주방장(Service)"임을 스프링에게 알림
@RequiredArgsConstructor // Repository를 주입받기 위해 사용
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;//창고 관리자 호출

    //생성
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
    //단건 조회
    @Transactional(readOnly = true)
    public ScheduleResponseDto getOneSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ()->new IllegalArgumentException("해당하는 스케줄이 없습니다.")
        );
        return new ScheduleResponseDto(schedule);
    }
    //다건 조회
    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> getUserNameSchedule(String username) {
        // 1. 일단 창고에 있는 모든 일정을 다 가져와
        List<Schedule> allSchedules = scheduleRepository.findAll();

        // 2. 결과를 담을 빈 접시(리스트)를 준비하고
        List<ScheduleResponseDto> dtos = new ArrayList<>();

        // 3. 하나씩 꺼내서 똑같은 이름을 찾는다.
        if(username != null) {//이름이 널이 아니면 로직을 실행해라
            for (Schedule schedule : allSchedules) {//allSchedules든 내용을 하나씩 꺼내서 schedule여기에 잠시담아서 로직 수행
                if (schedule.getUsername().equals(username)) { // 이름이 같으면
                    dtos.add(new ScheduleResponseDto(schedule)); // 접시에 담기
                }
            }
        }else{//이름이 없으면 입력하라고 해
            for (Schedule schedule : allSchedules) {
                dtos.add(new ScheduleResponseDto(schedule)); // 접시에 담기
            }
            dtos.sort((a, b) -> b.getUpdatedAt().compareTo(a.getUpdatedAt()));
            //b의 시간이 a보다 뒤면 앞으로 보내[이게 제일 어려웠다......]
            return dtos;
        }

        // 4. 만약 다 뒤졌는데 하나도 없다면 예외를 던져
        if (dtos.isEmpty()) {//내용물이 있는지?
            throw new IllegalArgumentException("일치하는 작성자의 게시글이 없습니다.");
        }
        dtos.sort((a, b) -> b.getUpdatedAt().compareTo(a.getUpdatedAt()));
        return dtos;
    }

    //수정
    @Transactional
    public ScheduleResponseDto updateSchedule(Long scheduleId, ScheduleRequestDto request) {
        //더티 체킹
        //DB에서 일치하는 스케줄을 가져온다.
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ()->new IllegalArgumentException("없는 스케줄 입니다.")//값이 없다면 오류 출력
        );
        if(!schedule.getPassword().equals(request.getPassword())) {//패스워드가 일치하지 않으면 오류출력
            //DB에 있는 패스워드와 입력한 패스워드가 일치하는지
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }else{//일치하면 업데이트 실시
            schedule.update(
                    request.getTitle(),
                    request.getContent()
            );
        }
        return new ScheduleResponseDto(schedule);
    }

    //삭제
    @Transactional
    public void deleteSchedule(Long scheduleId, ScheduleRequestDto request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ()->new IllegalArgumentException("없는 스케줄 입니다.")//값이 없다면 오류 출력
        );
        if(schedule.getPassword().equals(request.getPassword())) {
            //스케줄이 있고, 패스워드가 일치하는 경우->삭제가 가능하다
            scheduleRepository.deleteById(scheduleId);
        }else{//스케줄은 있으나 패스워드가 일치하지 않는경우
            throw new IllegalArgumentException("패스워드가 일치 하지 않습니다.");
        }
    }
}
