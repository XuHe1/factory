package com.kyx.factory.util;

/**
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/
public class SqlUtils {
    public static boolean toBoolean(int intBoolean){
        return intBoolean == 1;
    }

    public static int toInt(boolean booleanValue){
        return booleanValue ? 1 : 0;
    }
}
