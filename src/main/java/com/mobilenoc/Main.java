package com.mobilenoc;

import com.mobilenoc.cheker.lastsync.PreviousSync;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<File> fileList = new ArrayList<>();

    public static  void fillFileLis(File file){

            for (File i : file.listFiles()) {
                System.out.println(i.getAbsolutePath());
                try {
                    if (i.isDirectory() && i != null) {
                        fileList.add(i);
                        fillFileLis(i);
                    } else
                        fileList.add(i);

                } catch (NullPointerException e) {
                    continue;
                }
            }
    }
    public static void main(String... args) {
        File f = new File("C:/A");
        fillFileLis(f);
        PreviousSync.getINSTANCE().setCurrentSynchronizedFile(fileList);
    }
}
