package com.mobilenoc.cheker.algorithm;

import com.mobilenoc.cheker.lastsync.CheckPoint;

import java.io.File;
import java.io.IOException;

public interface SyncHolder {

    boolean isExist(String name, String path);

    boolean isSyncFile(File src, File trg);

    boolean isNewFile(File file) throws IOException ;

    boolean isModified(File file);

    CheckPoint getCheckPoint();
}
