package com.kyx.factory.exception;


/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/
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
