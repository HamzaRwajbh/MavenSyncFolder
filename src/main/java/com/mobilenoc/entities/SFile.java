package com.mobilenoc.entities;

import java.io.File;

public class SFile {

    public static final int NEW = 0 ;
    public static final int MOVE = 1 ;
    public static final int DELETE = 2 ;
    public static final int RENAME = 3 ;
    public static final int RENAME_AND_MOVE = 4 ;

    private File file ;
    private int status ;
    private String newDestination = null ;
    private String newName = null ;


    public SFile(File file, int status, String newDestination, String newName) {
        this.file = file;
        this.status = status;
        this.newDestination = newDestination;
        this.newName = newName;
    }

    public String getNewName() {
        return newName;
    }

    public String getNewDestination() {
        return newDestination;
    }

    public void setStatus(int status){this.status = status; }

    public int getStatus() {
        return status;
    }

    public void setNewDestination(String newDestination) {
        this.newDestination = newDestination;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
