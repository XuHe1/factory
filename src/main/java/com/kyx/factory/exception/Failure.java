package com.kyx.factory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by wangle on 5/26/16.
 */
public class Failure<T> extends ResponseEntity<FailureWrapper<T>> {
    public Failure(AppFailure appFailure) {
        super((FailureWrapper<T>) new FailureWrapper<>(appFailure.getCode(), appFailure.getMsg()),
                HttpStatus.BAD_REQUEST);
    }

    public Failure(AppFailure appFailure, T debug) {
        super(new FailureWrapper<>(appFailure.getCode(), appFailure.getMsg(), debug),
                HttpStatus.BAD_REQUEST);
    }

    public Failure(AppFailure appFailure, T debug, HttpStatus statusCode) {
        super(new FailureWrapper<>(appFailure.getCode(), appFailure.getMsg(), debug),
                statusCode);
    }

    public Failure(AppFailure appFailure, HttpStatus statusCode) {
        super((FailureWrapper<T>) new FailureWrapper<>(appFailure.getCode(), appFailure.getMsg()), statusCode);
    }


    public static <T> Failure<T> newFailure(AppFailure appFailure) {
        return new Failure<>(appFailure);
    }

    public static <T> Failure<T> newFailure(AppFailure appFailure, T debug) {
        return new Failure<>(appFailure, debug);
    }
}
