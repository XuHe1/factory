package com.kyx.factory.exception;


public enum ErrorEnum {
    UNKNOWN(ErrorCode.UNKNOWN, "未知错误"),
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
