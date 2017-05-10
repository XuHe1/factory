package com.kyx.factory.exception;

/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/
public class FailureWrapper<T> {
    private String code;
    private String msg;
    private T debug;

    public FailureWrapper(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public FailureWrapper(String code, String msg, T debug) {
        this.code = code;
        this.msg = msg;
        this.debug = debug;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getDebug() {
        return debug;
    }

    public void setDebug(T debug) {
        this.debug = debug;
    }
}
