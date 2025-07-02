package com.example.mall.domain.member.dto.request;

import com.example.mall.domain.member.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignUpRequest {

    @Email(message = "유효하지 않은 이메일 형식입니다.")
    @NotNull(message = "이메일은 필수 항목입니다.")
    private String email;

    @NotNull(message = "비밀번호는 필수 항목입니다.")
    @Size(min = 8, max = 100, message = "비밀번호는 8자 이상 100자 이하여야 합니다.")
    private String password;

    @NotNull(message = "이름은 필수 항목입니다.")
    private String name;

    @NotNull(message = "역할은 필수 항목입니다.")
    private String role;

    @NotNull(message = "닉네임은 필수 항목입니다.")
    private String nickname;

    @NotNull(message = "핸드폰 번호는 필수 항목입니다.")
    private String phone;

    @NotNull(message = "성별은 필수 항목입니다.")
    private Gender gender;
}
