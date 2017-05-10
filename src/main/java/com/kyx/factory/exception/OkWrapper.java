package com.kyx.factory.exception;


/**
 * Created by wangle on 5/16/16.
 */
class OkWrapper<T> {
    private T data;

    public OkWrapper(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
