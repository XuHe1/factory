package com.kyx.factory.web.support;

import com.kyx.factory.support.json.JsonResp;
import com.kyx.factory.support.json.Ok;

/**
 * @author wangle<thisiswangle@gmail.com>
 */
public abstract class BaseResource {
    protected <T> JsonResp<?> ok(T data) {
        return Ok.newOk(data);
    }

    protected JsonResp<?> ok() {
        return Ok.newOk();
    }
}
