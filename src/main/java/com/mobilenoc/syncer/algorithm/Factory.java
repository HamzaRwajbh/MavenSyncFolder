package com.mobilenoc.syncer.algorithm;

import com.mobilenoc.entities.SFile;

public class Factory {

    private SFile sFile ;

    public Factory(SFile sFile) {
        this.sFile = sFile;
        manufacture(sFile);
    }

    private void manufacture(SFile sFile) {
        if(sFile.getStatus() == 0){
            newFile();
        }else if(sFile.getStatus() == 1){
            move();
        }else if(sFile.getStatus() == 2){
            delete();
        }else if(sFile.getStatus() == 3){
            rename();
        }else if(sFile.getStatus() == 4){
            rename();
            move();
        }
    }

    private void rename(){

    }

    private void delete(){

    }

    private void move(){

    }

    private void moveAndRename(){

    }

    private void newFile(){

    }
}
