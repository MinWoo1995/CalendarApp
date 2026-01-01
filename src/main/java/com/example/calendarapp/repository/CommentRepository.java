package com.example.calendarapp.repository;

import com.example.calendarapp.dto.CommentResponseDto;
import com.example.calendarapp.entity.Comment;
import com.example.calendarapp.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    long countByScheduleId(Long scheduleId);//Comment 저장소에서 특정 scheduleId를 가진 데이터가 몇개인지 세어줘라

    List<Comment> findByScheduleId(Long scheduleId);//특정 일정의 모든 댓글을 리스트형태로 가져와라

}
