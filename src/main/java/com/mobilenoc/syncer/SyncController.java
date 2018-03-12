package com.mobilenoc.syncer;

import com.mobilenoc.syncer.algorithm.SyncAlgorithm;
import com.mobilenoc.syncer.lastsync.CheckPoint;
import com.mobilenoc.syncer.lastsync.PreviousSync;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class SyncController {

    private static File firstFolder ;
    private static File secondFolder ;
    private SyncAlgorithm syncAlgorithm;

    public SyncController(File firstFolder, File secondFolder) throws IOException {
        this.firstFolder = firstFolder;
        this.secondFolder = secondFolder;
        syncAlgorithm = new SyncAlgorithm() ;

        List<File> fileA = syncAlgorithm.checkUnSyncFiles(firstFolder,secondFolder);
        List<File> fileB = syncAlgorithm.checkUnSyncFiles(secondFolder,firstFolder);

        syncAlgorithm.findPair(fileA,fileB);

        PreviousSync.getINSTANCE().setCurrentSynchronizedFile(firstFolder);
        CheckPoint.getINSTANCE().setCheckPoint(new Date().getTime());
    }

    public static File getFirstFolder() {
        return firstFolder;
    }

    public static File getSecondFolder() {
        return secondFolder;
    }
}
