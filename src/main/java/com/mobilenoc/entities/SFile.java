package com.mobilenoc.entities;

import java.io.File;

public class SFile extends File {

    private boolean syncStatus = true ;
    private boolean moveStatus = false ;
    private boolean renameStatus = false ;
    private boolean newStatus = false ;
    private boolean deleteStatus = false ;

    public SFile(String pathname) {
        super(pathname);
    }

    public SFile(String parent, String child) {
        super(parent, child);
    }

    public SFile(File parent, String child) {
        super(parent, child);
    }

    public void setSyncStatus(boolean status){

        if(status == true) {
            syncStatus = true;
            moveStatus = false;
            renameStatus = false;
            newStatus = false;
            deleteStatus = false;
        }else
            syncStatus = false;

    }

    public boolean getSyncStatus(){
        return syncStatus ;
    }

    public boolean isMoveStatus() {
        return moveStatus;
    }

    public void setMoveStatus(boolean moveStatus) {
        this.moveStatus = moveStatus;
    }

    public boolean isRenameStatus() {
        return renameStatus;
    }

    public void setRenameStatus(boolean renameStatus) {
        this.renameStatus = renameStatus;
    }

    public boolean isNewStatus() {
        return newStatus;
    }

    public void setNewStatus(boolean newStatus) {
        this.newStatus = newStatus;
    }

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}
