package com.example.mall.global.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthPayload {

    private Long subject;
    private Role role;
}
