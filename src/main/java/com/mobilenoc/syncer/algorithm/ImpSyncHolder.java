package com.mobilenoc.syncer.algorithm;

import com.mobilenoc.syncer.lastsync.CheckPoint;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

public class ImpSyncHolder implements SyncHolder {

    protected CheckPoint checkPoint = this.getCheckPoint();

    @Override
    public boolean isExist(String name, String path) {
        File temp = new File(path + File.separator + name);
        return temp.exists();
    }

    @Override
    public boolean isSyncFile(File src, File trg) {
        long targetModifiedTime = trg.lastModified();
        long sourceModifiedTime = src.lastModified();
        if (sourceModifiedTime <= targetModifiedTime) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isNewFile(File file) throws IOException {
        BasicFileAttributes attribute = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
        long fileCreationTime = attribute.creationTime().toMillis();
        if (fileCreationTime >= checkPoint.getCheckPoint()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isModified(File file) {
        return file.lastModified()>=checkPoint.getCheckPoint();
    }

    @Override
    public CheckPoint getCheckPoint() {
        return CheckPoint.getINSTANCE();
    }


}
