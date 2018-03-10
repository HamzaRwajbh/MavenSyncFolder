package com.mobilenoc;

import com.mobilenoc.syncer.algorithm.SyncAlgorithm;
import com.mobilenoc.syncer.lastsync.CheckPoint;
import com.mobilenoc.syncer.lastsync.PreviousSync;

import java.io.*;
import java.util.Date;
import java.util.List;

public class Main {


    public static void main(String... args) throws IOException {

        File A = new File("j:/A");
        File B = new File("j:/B");
        List<File> fileA = new SyncAlgorithm().checkUnSyncFiles(A,B);
        List<File> fileB = new SyncAlgorithm().checkUnSyncFiles(B,A);
        PreviousSync.getINSTANCE().setCurrentSynchronizedFile();
        new SyncAlgorithm().findPair(fileA,fileB);
        CheckPoint.getINSTANCE().setCheckPoint(new Date().getTime());

    }
}
