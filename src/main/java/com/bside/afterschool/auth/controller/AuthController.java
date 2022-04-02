package com.bside.afterschool.auth.controller;

import com.bside.afterschool.auth.security.jwt.JwtHeaderUtil;
import com.bside.afterschool.auth.security.jwt.JwtToken;
import com.bside.afterschool.auth.security.jwt.JwtTokenProvider;
import com.bside.afterschool.auth.service.AuthService;
import com.bside.afterschool.common.util.ApiResponse;
import com.bside.afterschool.auth.dto.AuthRequest;
import com.bside.afterschool.auth.dto.AuthResponse;
import com.bside.afterschool.auth.service.KakaoAuthService;
import com.bside.afterschool.common.util.CommonRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final KakaoAuthService kakaoAuthService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;

    /**
     * KAKAO 소셜 로그인 기능
     * @param authRequest
     * @return
     */
    @PostMapping(value = "/kakao")
    public ResponseEntity<?> kakaoAuthRequest(@RequestBody AuthRequest authRequest) {
        return new ResponseEntity<>(new CommonRespDto<>(1, "성공 /auth/kakao success", kakaoAuthService.login(authRequest)), HttpStatus.OK);
    }

    /**
     * TODO 회원가입 테스트필요
     * @param authRequest
     * @return
     */
    @PostMapping("/regist")
    public ResponseEntity<?> authRegist(@RequestBody AuthRequest authRequest) {
        System.out.println("###########################################");
        System.out.println("authRequest >>>>>>>>>>>>>>> " + authRequest);
        System.out.println("###########################################");
        return new ResponseEntity<>(new CommonRespDto<>(1, "성공 /auth/regist success", kakaoAuthService.regist(authRequest)), HttpStatus.OK);
    }

    /**
     * appToken 갱신
     * @param request
     * @return
     */
    @GetMapping("/refreshToken")
    public ResponseEntity<?> refreshToken (HttpServletRequest request) {
        String appToken = JwtHeaderUtil.getAccessToken(request);
        JwtToken authToken = jwtTokenProvider.convertAuthToken(appToken);
        if (!authToken.validate()) { // 형식에 맞지 않는 token
            return ApiResponse.forbidden(null);
        }

        AuthResponse authResponse = authService.updateToken(authToken);
        if (authResponse == null) { // token 만료
            return new ResponseEntity<>(new CommonRespDto<>(9999, "HttpStatus.FORBIDDEN 403", null), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(new CommonRespDto<>(1, "HttpStatus.OK 200", authResponse), HttpStatus.OK);
    }

}
