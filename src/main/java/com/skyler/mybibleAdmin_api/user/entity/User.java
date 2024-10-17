package com.skyler.mybibleAdmin_api.user.entity;

import com.skyler.mybibleAdmin_api.user.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Table(name = "users")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long userId;

    private String loginId; // 로그인 아이디

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;

    @Builder
    public User(String loginId) {
        this.loginId = loginId;
        this.userStatus = UserStatus.ACTIVE;
        this.createdDt = LocalDateTime.now();
        this.updatedDt = LocalDateTime.now();
    }

    public boolean isAccessDenied() {
        return userStatus.isActive();
    }
}
