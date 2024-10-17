package com.skyler.mybibleAdmin_api.config.swagger;

import com.skyler.mybibleAdmin_api.common.error.ApiErrorCodeExamples;
import com.skyler.mybibleAdmin_api.common.error.ErrorCode;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Admin Test")
                        .description("Admin Test")
                        .version("v1.0"));
    }

    /**
     * API 컨트롤러 메서드에 정의된 `@ApiErrorCodeExamples` 어노테이션을 확인하여 API 오류코드에 대한 예시를 swagger 문자에 추가합니다.
     */
    @Bean
    public OperationCustomizer operationCustomizer() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            ApiErrorCodeExamples apiErrorCodeExamples = handlerMethod.getMethodAnnotation(ApiErrorCodeExamples.class);

            if (apiErrorCodeExamples != null) {
                generateErrorCodeResponseExample(operation, apiErrorCodeExamples.value());
            }

            return operation;
        };
    }

    /**
     * ErrorCode 기반으로 swagger 예시 데이터 생성합니다.
     */
    private void generateErrorCodeResponseExample(Operation operation, ErrorCode[] errorCodes) {
        ApiResponses apiResponses = operation.getResponses();

        // 각 ErrorCode 기반으로 swagger 예시 데이터 가져옴
        Map<Integer, List<SwaggerExampleDto>> statusWithExampleHolders = Arrays.stream(errorCodes)
                .map(SwaggerExampleDto::fromErrorCode)
                .collect(Collectors.groupingBy(SwaggerExampleDto::getStatusCode));

        // 예시 데이터를 응답 객체에 추가
        addExamplesToResponses(apiResponses, statusWithExampleHolders);
    }

    /***
     * swagger 예시 데이터를 응답 객체에 추가합니다.
     */
    private void addExamplesToResponses(ApiResponses apiResponses, Map<Integer, List<SwaggerExampleDto>> statusWithExampleList) {
        statusWithExampleList.forEach(
                (statusCode, v) -> {
                    Content content = new Content();
                    MediaType mediaType = new MediaType();
                    ApiResponse apiResponse = new ApiResponse();

                    v.forEach(
                            swaggerExample -> mediaType.addExamples(
                                    swaggerExample.getName(),
                                    swaggerExample.getExample()
                            )
                    );

                    content.addMediaType("application/json", mediaType);
                    apiResponse.setContent(content);
                    apiResponses.addApiResponse(String.valueOf(statusCode), apiResponse);
                }
        );
    }
}
