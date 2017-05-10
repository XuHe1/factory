package com.kyx.factory.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 判断登录名称类型.
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/
public class UserAgentUtils {
    private final static String USER_AGENT = "User-Agent";

    public static String getUserAgent(HttpServletRequest request) {
        return cleanXSS(request.getHeader(USER_AGENT));
    }

    private static String cleanXSS(String value) {
        //You'll need to remove the spaces from the html entities below
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
    }
}
