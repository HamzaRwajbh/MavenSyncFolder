package com.mobilenoc.syncer.lastsync;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PreviousSync {

    private static final Logger LOGGER = Logger.getLogger(PreviousSync.class.getName());

    private final File previousSyncFile = new File("src/main/resources/previous sync.txt");
    private static PreviousSync INSTANCE = null ;
    private List<File> fileList = new ArrayList<>();

    private PreviousSync(){
        fillFileList();
    }

    public static PreviousSync getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new PreviousSync() ;
        }
        return INSTANCE ;
    }

    private void fillFileList() {
        File tempFile;
        String splitLine[] ;
        String line ;
        BufferedReader bufferedReader = null;
        try{
            bufferedReader = new BufferedReader(new FileReader(previousSyncFile));
            while(( line = bufferedReader.readLine()) != null){
                splitLine = line.split(";");
                tempFile = new File(splitLine[0],splitLine[1]);
                fileList.add(tempFile);
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
    }

    public void setCurrentSynchronizedFile(List<File> files)  {
        fileList.clear();
        setFileList(files);
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(previousSyncFile));
            for(File file : files) {
                bufferedWriter.write(file.getParentFile().getPath() + ";" + file.getName());
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

    private void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }

    public List<File> getFileList() {
        return fileList;
    }

    public String getPreviousName(String firstName  , String secondName ){
        String splitLine[] ;
        String line ;
        BufferedReader bufferedReader = null;
        try{
            bufferedReader = new BufferedReader(new FileReader(previousSyncFile));
            while(( line = bufferedReader.readLine()) != null){
                splitLine = line.split(";");
                System.out.println(splitLine[1]);
                if(splitLine[1].equals(firstName)) {
                    return firstName;
                }else
                if (splitLine[1].equals(secondName)){
                    return secondName;
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
}
