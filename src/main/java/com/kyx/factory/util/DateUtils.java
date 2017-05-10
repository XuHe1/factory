package com.kyx.factory.util;

import java.util.Date;

/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/
public class DateUtils {
    public static Date now() {
        return new Date();
    }

    public static Date fromUnixtimestamp(long timestamp) {
        return new Date(timestamp * 1000);
    }
}
