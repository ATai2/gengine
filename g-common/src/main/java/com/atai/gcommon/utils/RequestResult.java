package com.atai.gcommon.utils;

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

    public static <T> RequestResult withSuccess(T data) {
        return new RequestResult<T>(data);
    }

    public static RequestResult withError(String msg) {
        RequestResult requestResult = new RequestResult(null);
        requestResult.setSuccess(false);
        requestResult.setMsg(msg);
        return requestResult;
    }

    public static RequestResult withError() {
        RequestResult requestResult = new RequestResult(null);
        requestResult.setSuccess(false);
        requestResult.setMsg("error");
        return requestResult;
    }
}
