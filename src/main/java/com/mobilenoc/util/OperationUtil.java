package com.mobilenoc.util;


import com.mobilenoc.entities.SFile;
import com.mobilenoc.syncer.SyncController;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OperationUtil {

    private static final Logger LOGGER = Logger.getLogger(OperationUtil.class.getName());

    private OperationUtil(){}

    public static void copy(File file , File destination) throws IOException {
        FileUtils.copyFile(file, destination);
    }

    public static void move(File file , File destination){

    }

    public static void rename(File file , String newName){

    }

    public static void delete(File file){

    }

    public static List<File> getAllFile(File file) {
        List<File> tempFileList = new ArrayList<>();
        for(File i : file.listFiles()) {
            if (i.isDirectory())
                tempFileList.addAll(getAllFile(i));
            else
                tempFileList.add(i);
        }
        return tempFileList ;
    }

}
