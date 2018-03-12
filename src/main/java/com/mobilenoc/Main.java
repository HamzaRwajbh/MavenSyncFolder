package com.mobilenoc;

import com.mobilenoc.syncer.SyncController;
import com.mobilenoc.syncer.algorithm.SyncAlgorithm;
import com.mobilenoc.syncer.lastsync.CheckPoint;
import com.mobilenoc.syncer.lastsync.PreviousSync;
import com.mobilenoc.util.OperationUtil;

import java.io.*;
import java.text.StringCharacterIterator;
import java.util.Date;
import java.util.List;

public class Main {


    public static void main(String... args) throws IOException {

        File A = new File("c:/A");
        File B = new File("c:/B");
        SyncController s = new SyncController(A,B);
    }
}
