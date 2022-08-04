package com.sparta.week03test.domain;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String name;
    private String password;
    private String contents;
}
