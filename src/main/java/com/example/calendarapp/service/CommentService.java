package com.example.calendarapp.service;

import com.example.calendarapp.dto.CommentRequestDto;
import com.example.calendarapp.dto.CommentResponseDto;
import com.example.calendarapp.dto.ScheduleRequestDto;
import com.example.calendarapp.dto.ScheduleResponseDto;
import com.example.calendarapp.entity.Comment;
import com.example.calendarapp.entity.Schedule;
import com.example.calendarapp.repository.CommentRepository;
import com.example.calendarapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // 이 클래스가 "주방장(Service)"임을 스프링에게 알림
@RequiredArgsConstructor // Repository를 주입받기 위해 사용
public class CommentService {
    private final CommentRepository commentRepository;//댓글 창고 관리자 호출
    private final ScheduleRepository scheduleRepository;//일정 창고 관리자 호출

    //댓글 생성
    @Transactional//트렌젝션 단위로 묶기
    public CommentResponseDto saveComment(CommentRequestDto requestDto) {

        //댓글 달기전에 해당 일정이 있는지 확인하기
        scheduleRepository.findById(requestDto.getScheduleId()).orElseThrow(
                () ->new IllegalArgumentException("해당 일정이 존재하지 않습니다.")
        );
        //해당 일정에 댓글이 10개 미만인지 확인하기
        Long count = commentRepository.countByScheduleId(requestDto.getScheduleId());
        if (count >= 10) {
            throw new IllegalArgumentException("해당 일정에 댓글이 10개를 초과하였습니다.");
        }

        // 1. [요리 시작] 사용자가 준 접시(DTO)에서 재료를 꺼내 실제 식재료(Entity)를 만듭니다.
        Comment comment = new Comment(
                requestDto.getContent(),
                requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.getScheduleId()
        );
        // 2. [창고 저장] 창고 관리자에게 식재료를 보관하라고 시킵니다.
        // 이때 DB가 ID와 생성/수정 시간을 자동으로 채워준 완성본을 돌려줍니다.
        Comment savedComment = commentRepository.save(comment);

        // 3. [서빙 준비] 완성된 식재료를 다시 예쁜 접시(Response DTO)에 담아 매니저(Controller)에게 전달합니다.
        return new CommentResponseDto(savedComment);
    };

}
