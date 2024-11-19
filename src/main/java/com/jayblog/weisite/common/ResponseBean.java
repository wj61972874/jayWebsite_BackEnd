package com.jayblog.weisite.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBean<T> {
    private int code;
    private String message;
    private T result;

    public static <T> ResponseBean<T> success(T result) {
        return new ResponseBean<>(0, "Success", result);
    }

    public static <T> ResponseBean<T> error(String message) {
        return new ResponseBean<>(500, message, null);
    }
}