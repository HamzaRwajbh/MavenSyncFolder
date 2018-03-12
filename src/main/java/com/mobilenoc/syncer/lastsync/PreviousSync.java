package com.mobilenoc.syncer.lastsync;

import com.mobilenoc.syncer.SyncController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.mobilenoc.util.OperationUtil.*;

public class PreviousSync {

    private static final Logger LOGGER = Logger.getLogger(PreviousSync.class.getName());

    private final File previousSyncFile = new File("src/main/resources/previous sync.txt");
    private static PreviousSync INSTANCE = null ;
    private List<File> fileList = new ArrayList<>();

    private PreviousSync(){}

    public static PreviousSync getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new PreviousSync() ;
        }
        return INSTANCE ;
    }

    public void setCurrentSynchronizedFile(File file)  {
        List<File> files = getAllFile(file);
        BufferedWriter bufferedWriter = null;
        String location ;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(previousSyncFile));
            for(File subFile : files) {
                location = subFile.getParentFile().getPath().replace(SyncController.getFirstFolder().getAbsolutePath(),"MAIN");
                bufferedWriter.write(location + ";" + subFile.getName());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    public String getNewName(String firstName  , String secondName ){
        String splitLine[] ;
        String line ;
        BufferedReader bufferedReader = null;
        try{
            bufferedReader = new BufferedReader(new FileReader(previousSyncFile));
            while(( line = bufferedReader.readLine()) != null){
                splitLine = line.split(";");
                if(splitLine[1].equals(firstName)) {
                    return secondName;
                }else
                if (splitLine[1].equals(secondName)){
                    return firstName;
                }
            }
        }catch (IOException e){
            LOGGER.log(Level.WARNING,"previous sync file does not exist !");
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null ;
    }

    public String getNewLocation(File firstFile, File secondFile) {
        String splitLine[];
        String line;
        BufferedReader bufferedReader = null;
        String firstParentLocation = replaceLocationToMain(firstFile.getParentFile().getAbsolutePath()) ;
        String secondParentLocation = replaceLocationToMain(secondFile.getParentFile().getAbsolutePath());
        try{
            bufferedReader = new BufferedReader(new FileReader(previousSyncFile));
            while(( line = bufferedReader.readLine()) != null){
                splitLine = line.split(";");
                if(splitLine[1].equals(firstFile.getName()) && splitLine[0].equals(firstParentLocation)) {
                    return secondParentLocation;
                }else
                if (splitLine[1].equals(secondFile.getName()) && splitLine[0].equals(secondParentLocation)){
                    return firstParentLocation;
                }
            }
        }catch (IOException e){
            LOGGER.log(Level.WARNING,"previous sync file does not exist !");
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null ;
    }

    public static String replaceLocationToMain(String location){
        String pathA = SyncController.getFirstFolder().getAbsolutePath() ;
        String pathB = SyncController.getSecondFolder().getAbsolutePath() ;
        if(location.contains(pathA)){
            return location.replace(pathA,"MAIN") ;
        }else if(location.contains(pathB))
            return location.replace(pathB,"MAIN");
        return null ;
    }

}
