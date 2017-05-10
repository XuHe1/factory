package com.kyx.factory.support.json;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

/**
 * 所有数据模型类的基类.
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/
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
