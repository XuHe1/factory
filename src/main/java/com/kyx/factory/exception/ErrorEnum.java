package com.kyx.factory.exception;


public enum ErrorEnum {
    UNKNOWN(ErrorCode.UNKNOWN, "未知错误"),
    PARAM_INVALID(ErrorCode.PARAM_INVALID, "参数不合法"),
    ALREADY_EXISTS(ErrorCode.ALREADY_EXISTS, "sn号已存在"),
    NOT_EXISTS(ErrorCode.NOT_EXISTS, "资源不存在"),
    BAD_REQUEST(ErrorCode.BAD_REQUEST, "非法请求"),
    SN_CONFLICT(ErrorCode.SN_CONFLICT, "SN冲突");
    ;

    private String code;
    private String msg;
    ErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

    public static ErrorEnum getByCode(String code) {
        for (ErrorEnum failure : ErrorEnum.values()) {
            if (failure.getCode().equals(code))
                return failure;
        }

        return UNKNOWN;
    }

    @Override
    public String toString() {
        return "<ErrorEnum>{" +
            "code='" + code + '\'' +
            ", msg='" + msg + '\'' +
            '}';
    }
}
