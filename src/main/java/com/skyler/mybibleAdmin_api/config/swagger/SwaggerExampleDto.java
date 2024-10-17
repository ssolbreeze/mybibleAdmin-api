package com.skyler.mybibleAdmin_api.config.swagger;

import com.skyler.mybibleAdmin_api.common.error.ErrorCode;
import com.skyler.mybibleAdmin_api.common.response.Response;
import com.skyler.mybibleAdmin_api.common.response.ResponseUtils;
import io.swagger.v3.oas.models.examples.Example;
import lombok.Builder;
import lombok.Getter;

/**
 * swagger 예시 데이터를 제공하는 DTO
 */
@Getter
@Builder
public class SwaggerExampleDto {
    // swagger 예시로 나오는 json 형식의 객체
    private Example example;
    // swagger 상태코드 별 에러코드 이름
    private String name;
    // swagger 실패 상태 코드
    private int statusCode;

    /**
     * ErrorCode 기반으로 Swagger Example dto 객체를 생성합니다.
     */
    public static SwaggerExampleDto fromErrorCode(ErrorCode errorCode) {
        return SwaggerExampleDto.builder()
                .example(createExample(errorCode))
                .name(errorCode.name())
                .statusCode(errorCode.getHttpStatus().value())
                .build();
    }

    /***
     * ErrorCode 기반으로 Example 객체 생성합니다.
     */
    private static Example createExample(ErrorCode errorCode) {
        Response<Object> failResponse = ResponseUtils.fail(errorCode.getCode(), errorCode.getMessage());

        Example example = new Example();
        example.setValue(failResponse);
        example.setDescription(errorCode.getDescription());

        return example;
    }
}
