package com.kyx.factory.util;

import lombok.extern.slf4j.Slf4j;

/**
 * 软硬件版本号转换工具
 *
 * @author h.xu
 * @create 2017-05-31 下午8:14
 **/
@Slf4j
public class VersionUtil {
    public static byte[] ints2bytes(int... args) {
        byte[] ret = new byte[args.length * 4];
        int pos = 0;
        int[] arr$ = args;
        int len$ = args.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            int d = arr$[i$];
            ret[pos++] = (byte)((d & -16777216) >> 24);
            ret[pos++] = (byte)((d & 16711680) >> 16);
            ret[pos++] = (byte)((d & '\uff00') >> 8);
            ret[pos++] = (byte)(d & 255);
        }

        return ret;
    }


    /** 是否HEX字符串 */
    private static boolean isHexChar(String hexString) {
        String hexChars = "0123456789abcdef";
        for (int i = 0; i < hexString.length(); i++) {
            if(! hexChars.contains(String.valueOf(hexString.charAt(i)).toLowerCase())){
                return false;//包含非法字符
            }
        }
        return true;
    }

    /**
     * @param hexString
     * @return 将多个字节的十六进制串转换为十进制串
     */
    public static String hexString2DecimalString(String hexString){
        if(hexString == null){
            return null;
        }
        if(! isHexChar(hexString)){
            return null;
        }
        try {
            Integer data = Integer.parseInt(hexString, 16);
            return String.valueOf(data);
        } catch (NumberFormatException e) {
            log.warn("{}", e);
            e.printStackTrace();
        } catch (Exception e) {
            log.warn("{}", e);
        }
        return null;
    }

    public static String getVestion(String versionStr) {
        int version = Integer.parseInt(versionStr);
        String hexStr1 = ByteConvert.bytes2HexString(ints2bytes(version));

        hexStr1 = hexStr1.substring(4,8);
        String mainVersionStr = hexStr1.substring(0,2);
        String branchVersionStr = hexStr1.substring(2,4);
        return hexString2DecimalString(mainVersionStr) + "." + hexString2DecimalString(branchVersionStr);

    }


}
