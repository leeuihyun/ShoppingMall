package com.example.mall.domain.member.controller;

import com.example.mall.domain.member.dto.request.SignInRequest;
import com.example.mall.domain.member.dto.request.SignUpRequest;
import com.example.mall.domain.member.dto.response.MemberResponse;
import com.example.mall.domain.member.dto.response.TokenResponse;
import com.example.mall.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signin")
    public ResponseEntity<MemberResponse> signUp(@RequestBody @Valid SignUpRequest request) {

        MemberResponse response = memberService.signUp(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<TokenResponse> signIn(@RequestBody @Valid SignInRequest request) {

        TokenResponse response = memberService.signIn(request);
        return ResponseEntity.ok(response);
    }
}
