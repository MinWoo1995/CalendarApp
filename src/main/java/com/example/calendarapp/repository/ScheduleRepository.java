package com.example.calendarapp.repository;

import com.example.calendarapp.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//사용함으로 데이터를 수정 조회 생성이 가능하다
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    //이게 스케줄 객체와(각종 필드와 메서드를 통한 결과물) 디비 생성 id 를 넘기고 받는다라는 뜻
}
