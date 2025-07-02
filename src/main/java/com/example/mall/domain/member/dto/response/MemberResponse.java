package com.example.mall.domain.member.dto.response;

import com.example.mall.domain.member.Gender;
import com.example.mall.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponse {

    private Long id;
    private String nickname;
    private String phone;
    private Gender gender;

    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getId(), member.getNickname(), member.getPhone(),
            member.getGender());
    }
}
