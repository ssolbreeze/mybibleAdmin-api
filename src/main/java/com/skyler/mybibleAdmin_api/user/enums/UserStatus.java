package com.skyler.mybibleAdmin_api.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    ACTIVE("정상"),
    PENDING("가입 승인 대기중"),
    REJECTED("가입 승인 거절됨"),
    DELETED("탈퇴"),
    BLACKLIST("블랙리스트")
    ;

    private final String description;

    public boolean isActive() {
        return this.name().equals(ACTIVE.name());
    }
}
