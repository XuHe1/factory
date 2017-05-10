package com.kyx.factory.support.json;

import com.kyx.factory.exception.ErrorEnum;
import org.springframework.http.HttpStatus;

/**
 * Created by wangle on 5/26/16.
 */
public class Error<T> extends JsonResp<JsonObject<T>> {
    public Error(ErrorEnum errorEnum) {
        super(new JsonObject<>(null, errorEnum.getCode(), errorEnum.getMsg(), null),
                HttpStatus.BAD_REQUEST);
    }

    public Error(ErrorEnum errorEnum, T meta) {
        super(new JsonObject<>(null, errorEnum.getCode(), errorEnum.getMsg(), meta),
                HttpStatus.BAD_REQUEST);
    }

    public Error(ErrorEnum errorEnum, T meta, HttpStatus statusCode) {
        super(new JsonObject<>(null, errorEnum.getCode(), errorEnum.getMsg(), meta),
                statusCode);
    }

    public Error(ErrorEnum errorEnum, HttpStatus statusCode) {
        super(new JsonObject<>(null, errorEnum.getCode(), errorEnum.getMsg(), null),
                statusCode);
    }

    public static <T> Error<T> newError(ErrorEnum errorEnum) {
        return new Error<>(errorEnum);
    }

    public static <T> Error<T> newError(ErrorEnum errorEnum, T debug) {
        return new Error<>(errorEnum, debug);
    }
}
