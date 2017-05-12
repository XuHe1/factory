package com.kyx.factory.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/
public class DateUtils {
    private static final String PATTERN_DATE = "yyyyMMddHHmmss";

    public static Date now() {
        return new Date();
    }

    public static Date fromUnixtimestamp(long timestamp) {
        return new Date(timestamp * 1000);
    }

    public static String toNormalDate(Date dateTime) {
        if (dateTime == null) {
            return null;
        }
        return DateFormatUtils.format(dateTime, PATTERN_DATE);
    }
}
