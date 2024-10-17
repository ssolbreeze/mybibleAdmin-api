package com.skyler.mybibleAdmin_api.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

public record Response<T>(@Schema(description = "응답 성공여부 : true / false") boolean success,
                          @Schema(description = "응답 코드 : 0 (성공) / 이외 (에러)") int code,
                          @Schema(description = "응답 메세지") String message,
                          @Schema(description = "성공 데이터") @JsonInclude(JsonInclude.Include.NON_NULL) T data) {

}
