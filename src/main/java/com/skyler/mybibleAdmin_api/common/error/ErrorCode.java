package com.skyler.mybibleAdmin_api.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    OK(0, HttpStatus.OK, "OK", "성공시 발생"),

    // Internal Server Error
    INTERNAL_ERROR(-1, HttpStatus.INTERNAL_SERVER_ERROR, "서버에 오류가 발생했습니다.", "서버 오류 발생 시"),
    METHOD_NOT_ALLOWED(-2, HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP Method 요청입니다.", "지원하지 않는 http 메소드인 경우"),
    METHOD_(-3, HttpStatus.METHOD_NOT_ALLOWED, "www", "이외 에러인 경우 발생합니다.")

    ;

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;
    private final String description;

//    public String getMessage(Throwable throwable) {
//        return this.getMessage(this.getMessage(this.getMessage() + " - " + throwable.getMessage()));
//    }
//
//    public String getMessage(String message) {
//        return Optional.ofNullable(message)
//                .filter(Predicate.not(String::isBlank))
//                .orElse(this.getMessage());
//    }
}
