package com.example.mall.domain.member.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInRequest {

    @Email(message = "이메일 형식이 유효하지 않습니다.")
    @NotNull(message = "이름은 필수 항목입니다.")
    private String email;

    @NotNull(message = "비밀번호는 필수 항목입니다.")
    private String password;
}
