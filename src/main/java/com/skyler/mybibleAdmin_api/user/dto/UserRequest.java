package com.skyler.mybibleAdmin_api.user.dto;

import lombok.Data;

public class UserRequest {
    @Data
    public static class PostUserReq {
        private String loginId;
    }
}
