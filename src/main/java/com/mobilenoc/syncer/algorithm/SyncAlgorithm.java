package com.mobilenoc.syncer.algorithm;

import com.mobilenoc.entities.SFile;
import com.mobilenoc.syncer.lastsync.PreviousSync;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class SyncAlgorithm extends ImpSyncHolder {

    public List<File> checkUnSyncFiles(File source, File target) throws IOException {
        List<File> tempFileList = new ArrayList<>();
        Set<String> subTarget = new HashSet<>(Arrays.asList(target.list()));
        for (File subFile : source.listFiles()) {
            if (!subTarget.contains(subFile.getName())) {
                if (subFile.isDirectory() || ( !isNewFile(subFile) && !isModified(subFile) ) ) {
                    if(subFile.isDirectory()) {
                        tempFileList.addAll(getAllFile(subFile));
                    }else {
                        tempFileList.add(subFile);
                    }
                }
            } else if (subFile.isDirectory()) {
                tempFileList.addAll(checkUnSyncFiles(subFile , new File(target , subFile.getName())));
            }
        }
        return tempFileList ;
    }


    public List<File> getAllFile(File file) {
        List<File> tempFileList = new ArrayList<>();
        for(File i : file.listFiles()) {
            if (i.isDirectory())
                tempFileList.addAll(getAllFile(i));
            else
                tempFileList.add(i);
        }
        return tempFileList ;
    }

    public void findPair(List<File> A , List<File> B ) throws IOException {

        for(int k = 0 ; k <A.size() ; k++){
            for(int i = 0 ; i<B.size() ; i++){
                if(A.get(k).getName().equals(B.get(i).getName())) {
                    System.out.println(A.get(k) + " is found in " + B.get(i));
                    A.remove(A.get(k)) ;
                    B.remove(i);
                    k--;
                    break;
                }else if(isRename(A.get(k) , B.get(i))){
                    System.out.println(A.get(k) + " and " + B.get(i) + " Renamed");
                    System.out.println(PreviousSync.getINSTANCE().getPreviousName(A.get(k).getName(), B.get(i).getName()) + "is Previous name");
                    A.remove(A.get(k));
                    B.remove(B.get(i));
                    k--;
                    break;
                }
            }
        }
        System.out.println(A);
        System.out.println(B);
    }

    public boolean isRename(File a , File b) throws IOException {
        return FileUtils.contentEquals(a , b);
    }
}
