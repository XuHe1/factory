package com.kyx.factory.web.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 现有工厂
 *
 * @author h.xu
 * @create 2017-05-12 下午3:57
 **/

public enum  FactoryEnum {
    JinXin("JinXin", 1);
    private String factoryName;
    private int lineCount;

    private static Map<String, Integer> map = new HashMap<>();
    static {
        for(FactoryEnum factory : FactoryEnum.values()) {
            map.put(factory.factoryName, factory.lineCount);
        }
    }

    FactoryEnum(String factoryName, int lineCount) {
        this.factoryName = factoryName;
        this.lineCount = lineCount;
    }


    public static Integer getLineCountByFactory(String factoryName) {
        return map.get(factoryName);
    }

    public static Set getAllFactory() {
        return map.keySet();
    }
}
