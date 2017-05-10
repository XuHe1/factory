package com.kyx.factory.util;

/**
 * Created by wangle on 08/12/2016.
 */
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
