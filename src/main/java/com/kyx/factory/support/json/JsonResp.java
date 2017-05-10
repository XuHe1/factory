package com.kyx.factory.support.json;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

/**
 * @author wangle<thisiswangle@gmail.com>
 */
public class JsonResp<T> extends ResponseEntity<T> {
    public JsonResp(HttpStatus status) {
        super(status);
    }

    public JsonResp(T body, HttpStatus status) {
        super(body, status);
    }

    public JsonResp(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
    }

    public JsonResp(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
    }
}
