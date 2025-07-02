package com.example.mall.domain.member.service;

import com.example.mall.domain.member.dto.request.SignInRequest;
import com.example.mall.domain.member.dto.request.SignUpRequest;
import com.example.mall.domain.member.dto.response.MemberResponse;
import com.example.mall.domain.member.dto.response.TokenResponse;
import com.example.mall.domain.member.entity.Member;
import com.example.mall.domain.member.repository.MemberRepository;
import com.example.mall.global.jwt.JwtProvider;
import com.example.mall.global.jwt.Role;
import com.example.mall.global.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public MemberResponse signUp(SignUpRequest request) {

        Member member = Member.builder()
            .email(request.getEmail())
            .name(request.getName())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .gender(request.getGender())
            .phone(request.getPhone())
            .nickname(request.getNickname())
            .build();

        Member savedMember = memberRepository.save(member);

        return MemberResponse.of(savedMember);
    }

    @Transactional
    public TokenResponse signIn(SignInRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "user not found"));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "password not match");
        }

        String accessToken = jwtProvider.createToken(member.getId(),
            Role.valueOf(member.getRole()));

        return new TokenResponse(accessToken);
    }
}
