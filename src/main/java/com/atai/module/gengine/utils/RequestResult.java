package com.atai.module.gengine.utils;

import lombok.Data;

@Data
public class RequestResult<T> {
    private int code;
    private boolean success;
    private String msg;
    private T data;

    public RequestResult(T data) {
        this.data = data;
        this.success = true;
    }

    public static <T> RequestResult  withSuccess(T data) {
        return new RequestResult<T>(data);
    }
}
