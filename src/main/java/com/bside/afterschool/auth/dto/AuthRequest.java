package com.bside.afterschool.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String accessToken;

    // TODO 회원가입 추가입력항목
    private String nickname;        // 회원명
    private String enterYear;   // 입학연도
    private String endYear;     // 졸업연도
    private String instagramUrl;
    private String job;         // 직업
    private String description; // 하고싶은 말
    private String placeId;     // 구글 placd id

    private Boolean isRegist;
}
