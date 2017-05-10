package com.kyx.factory.exception;


public enum AppFailure {
    UNKNOWN(AppFailureCode.UNKNOWN, "未知错误"),
    PARAM_INVALID(AppFailureCode.PARAM_INVALID, "参数不合法"),
    ALREADY_EXISTS(AppFailureCode.ALREADY_EXISTS, "sn号已存在"),
    NOT_EXISTS(AppFailureCode.NOT_EXISTS, "资源不存在"),
    BAD_REQUEST(AppFailureCode.BAD_REQUEST, "非法请求"),
    SN_CONFLICT(AppFailureCode.SN_CONFLICT, "SN冲突");
    private String code;
    private String msg;
    AppFailure(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

    public static AppFailure getByCode(String code) {
        for (AppFailure failure : AppFailure.values()) {
            if (failure.getCode().equals(code))
                return failure;
        }

        return UNKNOWN;
    }

    @Override
    public String toString() {
        return "<AppFailure>{" +
            "code='" + code + '\'' +
            ", msg='" + msg + '\'' +
            '}';
    }
}
