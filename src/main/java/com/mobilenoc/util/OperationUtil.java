package com.mobilenoc.util;


import com.mobilenoc.entities.SFile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class OperationUtil {
    private static final Logger LOGGER = Logger.getLogger(OperationUtil.class.getName());

    public static void copy(File file , File destination) throws IOException {
        FileUtils.copyFile(file, destination);
    }

    public static void copy(File file , String destination) throws IOException {
        copy(file,new File(destination));
    }


    public static void move(File file , String destination) throws IOException {
        FileUtils.copyFile(file,new File(destination));
        SFile temp = (SFile) file ;
        temp.setStatus(SFile.DELETE);
    }

    public static void move(File file , File destination){

    }

    public static void rename(File file , String newName){

    }

    public static void delete(File file){

    }
}
