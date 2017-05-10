package com.kyx.factory.web.support;

import com.kyx.factory.support.json.JsonResp;
import com.kyx.factory.support.json.Ok;

/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/
public abstract class BaseResource {
    protected <T> JsonResp<?> ok(T data) {
        return Ok.newOk(data);
    }

    protected JsonResp<?> ok() {
        return Ok.newOk();
    }
}
