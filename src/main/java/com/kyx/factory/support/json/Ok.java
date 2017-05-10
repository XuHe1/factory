package com.kyx.factory.support.json;

import org.springframework.http.HttpStatus;

public class Ok<T> extends JsonResp<JsonObject<T>> {
    private static class Empty {}

    public Ok(T body) {
        super(new JsonObject<>(body, null, null, null), HttpStatus.OK);
    }

    public static <T> Ok<T> newOk(T body) {
        return new Ok<>(body);
    }

    public static Ok newOk() {
        return new Ok<>(new Empty());
    }
}
