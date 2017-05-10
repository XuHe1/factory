package com.kyx.factory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by wangle on 5/16/16.
 */
public class Ok<T> extends ResponseEntity<OkWrapper<T>> {
    private static class Empty {}
    public Ok(T body) {
        super(new OkWrapper<>(body), HttpStatus.OK);
    }

    public static <T> Ok<T> newOk(T body) {
        return new Ok<>(body);
    }

    public static Ok newOk() {
        return new Ok<>(new Empty());
    }
}
