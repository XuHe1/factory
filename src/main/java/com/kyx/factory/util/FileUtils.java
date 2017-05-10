package com.kyx.factory.util;

/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/
public class FileUtils {
    public static String getFileType(String fileName){
        int index = fileName.lastIndexOf(".");
        int length = fileName.length();
        String fileType;
        if( index == -1){
            fileType = "";
        }
        else {
            fileType = fileName.substring(index,length);
        }
        return  fileType;
    }
}
