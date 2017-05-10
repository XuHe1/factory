package com.kyx.factory.util;


/**
 * 判断登录名称类型.
 *
 * @author wangle<thisiswangle@gmail.com>
 */
public class LoginNameUtils {
    public static enum LoginNameEnum {
        PHONE_NUMBER, EMAIL, USERNAME
    }

    public static LoginNameEnum decide(String loginName) {
        int digitalCount = 0;
        for (char c : loginName.toCharArray()) {
            if (c == '@') {
                return LoginNameEnum.EMAIL;
            }

            if (c >= '0' && c <= '9') {
                digitalCount ++;
            }
        }

        if (digitalCount == loginName.length())
            return LoginNameEnum.PHONE_NUMBER;

        return LoginNameEnum.USERNAME;
    }
}
