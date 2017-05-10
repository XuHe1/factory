package com.kyx.factory.support.json;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class JsonObject<T> {
    private T data;
    private String code;
    private String msg;
    private Object meta;

}
