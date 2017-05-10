package com.kyx.factory.util;

/**
 * Created by wangle on 5/25/16.
 */
public class SqlUtils {
    public static boolean toBoolean(int intBoolean){
        return intBoolean == 1;
    }

    public static int toInt(boolean booleanValue){
        return booleanValue ? 1 : 0;
    }
}
