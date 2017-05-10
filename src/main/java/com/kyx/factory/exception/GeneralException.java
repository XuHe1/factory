package com.kyx.factory.exception;

public class GeneralException extends RuntimeException {
    private ErrorEnum errorEnum;

    public GeneralException() {
        super(ErrorEnum.UNKNOWN.toString());
        this.errorEnum = ErrorEnum.UNKNOWN;
    }

    public GeneralException(ErrorEnum errorEnum) {
        super(errorEnum.toString());
        this.errorEnum = errorEnum;
    }

    public ErrorEnum getErrorEnum() {
        return errorEnum;
    }

    public void setErrorEnum(ErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

    public static GeneralException newException(ErrorEnum errorEnum) {
        return new GeneralException(errorEnum);
    }
}
