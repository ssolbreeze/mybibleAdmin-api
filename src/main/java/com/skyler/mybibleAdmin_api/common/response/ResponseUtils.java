package com.skyler.mybibleAdmin_api.common.response;

public class ResponseUtils {

    public static <T> Response<T> success(T data) {
        return new Response<>(true, 0, "success", data);
    }

    public static <T> Response<T> fail(int code, String msg) {
        return new Response<>(false, code, msg, null);
    }
}
