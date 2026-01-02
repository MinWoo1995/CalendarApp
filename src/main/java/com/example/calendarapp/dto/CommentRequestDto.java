package com.example.calendarapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @NotBlank(message = "내용은 필수입니다.")
    @Size(max = 100, message = "내용은 최대 100자 이내여야 합니다.")
    private String content;
    @NotBlank(message = "작성자명을 입력해주세요")
    private String username;
    @NotBlank(message = "패스워드를 입력해주세요")
    private String password;
    @NotNull
    private Long scheduleId;
}
