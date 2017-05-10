package com.kyx.factory.web.validation;

import com.baidu.unbiz.fluentvalidator.Result;
import com.google.common.collect.Lists;

/**
 * @author wangle<thisiswangle@gmail.com>
 */
public class SimpleResult extends Result {
    private SimpleResult() {}
    public static SimpleResult build(String reason) {
        SimpleResult simpleResult = new SimpleResult();
        simpleResult.setErrors(Lists.newArrayList(reason));
        simpleResult.setIsSuccess(false);
        return simpleResult;
    }
}
