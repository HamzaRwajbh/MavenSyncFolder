package com.mobilenoc.syncer.algorithm;

import com.mobilenoc.syncer.lastsync.CheckPoint;

import java.io.File;
import java.io.IOException;

public interface SyncHolder {

    boolean isExist(String name, String path);

    boolean isSyncFile(File src, File trg);

    boolean isNewFile(File file) throws IOException ;

    boolean isModified(File file);

    CheckPoint getCheckPoint();
}
