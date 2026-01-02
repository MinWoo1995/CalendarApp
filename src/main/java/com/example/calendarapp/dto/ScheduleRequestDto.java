package com.example.calendarapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {//클라이언트가 보낸 주문서
    @NotBlank(message = "제목은 필수입니다.") // null, "", " " 모두 방어
    @Size(max = 30, message = "제목은 최대 30자 이내여야 합니다.")
    private String title;
    @NotBlank(message = "내용은 필수입니다.")
    @Size(max = 200, message = "내용은 최대 200자 이내여야 합니다.")
    private String content;
    @NotBlank(message = "작성자명은 필수입니다.")
    private String username;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
