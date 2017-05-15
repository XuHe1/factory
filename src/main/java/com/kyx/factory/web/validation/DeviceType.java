package com.kyx.factory.web.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 所有设备
 *
 * @author h.xu
 * @create 2017-05-12 下午4:12
 **/
public enum  DeviceType {
    look("look", "L");

    private String deviceName;
    private String snPrefix;

    private static Map<String, String> map = new HashMap<>();
    static {
        for(DeviceType device : DeviceType.values()) {
            map.put(device.deviceName, device.snPrefix);
        }
    }


    DeviceType(String deviceName, String snPrefix) {
        this.deviceName = deviceName;
        this.snPrefix = snPrefix;
    }

    public static Set getAllType() {
        return map.keySet();
    }

    public static String getSnPrefix(String deviceName) {
        return map.get(deviceName);
    }

}
