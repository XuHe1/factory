package com.kyx.factory.util;

import java.util.Date;

/**
 * @author wangle<thisiswangle@gmail.com>
 */
public class DateUtils {
    public static Date now() {
        return new Date();
    }

    public static Date fromUnixtimestamp(long timestamp) {
        return new Date(timestamp * 1000);
    }
}
