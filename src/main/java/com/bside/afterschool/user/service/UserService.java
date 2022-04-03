package com.bside.afterschool.user.service;

import com.bside.afterschool.auth.security.jwt.JwtToken;
import com.bside.afterschool.auth.security.jwt.JwtTokenProvider;
import com.bside.afterschool.user.domain.User;
import com.bside.afterschool.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    /**
     * 회원 ID 조회
     * @param token
     * @return
     */
    public Long getUserId(String token) {
        JwtToken authToken = jwtTokenProvider.convertAuthToken(token);

        Claims claims = authToken.getTokenClaims(); // Token 검증
        if (claims == null) {
            return null;
        }

        try {
            User user =  userRepository.findBySocialId(claims.getSubject());
            return user.getId();

        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자가 존재하지 않습니다.");
        }
    }

}