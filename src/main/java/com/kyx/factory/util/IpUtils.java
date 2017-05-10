package com.kyx.factory.util;

import com.google.common.net.InetAddresses;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/
public class IpUtils {
    private static final String[] PROXY_REMOTE_IP_ADDRESS_HEADERS = {"X-Forwarded-For", "X-Real-IP"};
    /**
     * Convert ip string to int.
     * @param ipString ip
     * @return int
     */
    public static Integer ipStringToInteger(String ipString) {
        if (StringUtils.isBlank(ipString))
            return 0;

        InetAddress address = InetAddresses.forString(ipString);
        return InetAddresses.coerceToInteger(address);
    }

    /**
     * Convert integer to ip string back.
     * @param ip int
     * @return ip string
     */
    public static String integerToIpString(Integer ip) {
        return InetAddresses.fromInteger(ip).getHostAddress();
    }

    /**
     * Get Real IP from request
     * @param request HttpServletRequest Object
     * @return IP string in xx.xx.xx.xx
     */
    public static String getRemoteIp(HttpServletRequest request) {
        for (String header : PROXY_REMOTE_IP_ADDRESS_HEADERS) {
            String ip = request.getHeader(header);
            if (ip != null && ip.trim().length() > 0) {
                return getRemoteIpFromForward(ip.trim());
            }
        }
        return request.getRemoteHost();
    }

    private static String getRemoteIpFromForward(String xForwardIp) {
        int commaOffset = xForwardIp.indexOf(',');
        if (commaOffset < 0) {
            return xForwardIp;
        }
        return xForwardIp.substring(0 , commaOffset);
    }
}
