package com.bside.afterschool.user.domain;

import com.bside.afterschool.auth.enumerate.RoleType;
import com.bside.afterschool.common.domain.BaseEntity;
import com.bside.afterschool.place.domain.Place;
import com.bside.afterschool.post.domain.Post;
import com.bside.afterschool.user.enumerate.UserProvider;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;            // id

    @JsonIgnoreProperties({"user"})
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Place> placeList = new ArrayList<>();

    @JsonIgnoreProperties({"user"})
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> postList = new ArrayList<>();

    @Column(nullable = false)
    private String name;        // 이름

    @Column
    private String email;       // 이메일

    @Column
    private String gender;      // 성별

    @Column(name = "social_id", nullable = false)
    private String socialId;    // social Id

    @Enumerated(EnumType.STRING)
    @Column(name = "user_provider", nullable = false)
    private UserProvider userProvider;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", nullable = false)
    private RoleType roleType;

    @Column(name = "enter_year")
    private String enterYear;   // 입학연도

    @Column(name = "end_year")
    private String endYear;     // 졸업연도

    @Column(name = "instagram_url")
    private String instagramUrl;

    @Column(name = "job")
    private String job;         // 직업

    @Lob
    private String description; // 하고싶은 말

    @Transient
    @Column(name = "profile_image_path")
    private String profileImagePath;    // TODO profileImagePath 컬럼 사용유무 확인필요
}
