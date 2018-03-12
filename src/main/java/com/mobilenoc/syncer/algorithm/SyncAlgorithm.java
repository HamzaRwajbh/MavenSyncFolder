package com.mobilenoc.syncer.algorithm;

import com.mobilenoc.entities.SFile;
import com.mobilenoc.syncer.SyncController;
import com.mobilenoc.syncer.lastsync.PreviousSync;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.mobilenoc.util.OperationUtil.*;

public class SyncAlgorithm extends ImpSyncHolder {

    private PreviousSync previousSync = PreviousSync.getINSTANCE();

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



    public void findPair(List<File> A , List<File> B ) throws IOException {

        SFile sFile ;
        String newName ;
        String newLocation ;
        File file ;

        for(int k = 0 ; k <A.size() ; k++){
            for(int i = 0 ; i<B.size() ; i++){
                if(A.get(k).getName().equals(B.get(i).getName())) {
                    System.out.println(A.get(k) + " is found in " + B.get(i));
                    newLocation = previousSync.getNewLocation(A.get(k) , B.get(i)) ;

                    System.out.println( newLocation + " new Location");
                    //sFile = new SFile()
                    A.remove(A.get(k)) ;
                    B.remove(i);
                    k--;
                    break;
                }
                if(isRename(A.get(k) , B.get(i))){
                    System.out.println(A.get(k) + " and " + B.get(i) + " Renamed");
                    newName = PreviousSync.getINSTANCE().getNewName(A.get(k).getName(), B.get(i).getName());
                    //System.out.println(newName);
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

    public void syncFolder(File source, File target) throws IOException {

        //check files exist in sources and not found in target & check files are updated in source
        for (File i : source.listFiles()) {
            //if it is the directory
            if (i.isDirectory()) {
                //if the file is directory ? true : recall the syncFolder again until find file with extension
                if (isExist(i.getName(), target.getAbsolutePath())) {
                    //System.out.println(i.getAbsolutePath() + " is a Directory and Exist");
                    syncFolder(i, new File(target.getAbsolutePath(), i.getName()));
                } else {
                    //System.out.println(i.getAbsolutePath() + "is a Directory and not Exist");
                    System.out.println(new File(target.getAbsolutePath(), i.getName()).mkdir() + " " + i.getAbsolutePath() + " is created");
                    syncFolder(i, new File(target.getAbsolutePath(), i.getName()));
                }// if the file with extension
            } else {
                if (isExist(i.getName(), target.getAbsolutePath())) {
                    if (!isSyncFile(i, new File(target, i.getName()))) { //check if the file is NOT sync ? true : copy to target
                        //System.out.println(i.getAbsolutePath() + " is not sync , the copy is done");
                        //copy
                    } else { // if the file is NOT sync ? false : skip this file and continue
                        continue;
                    }
                } else {
                    //copy
                }

            }
        }
    }

    public boolean isRename(File a , File b) throws IOException {
        return FileUtils.contentEquals(a , b);
    }

}
