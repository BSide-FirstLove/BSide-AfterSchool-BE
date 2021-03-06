package com.bside.afterschool.user.repository;

import com.bside.afterschool.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    User findMemberById(Long memberId);

    User findMemberBySocialId(Long socialId);

    /**
     * 회원조회 by socialID
     * TODO querydsl 적용필요
     * @param socialId
     * @return
     */
    @Query(value="SELECT * FROM user WHERE social_id = :socialId", nativeQuery = true)
    User findBySocialId(String socialId);
}