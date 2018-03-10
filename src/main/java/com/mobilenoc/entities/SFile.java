package com.mobilenoc.entities;

import java.io.File;

public class SFile extends File {

    private boolean syncStatus = true ;
    public static final String MOVE = "MOVE" ;
    public static final String NEW = "NEW" ;
    public static final String DELETE = "DELETE" ;
    public static final String RENAME = "RENAME" ;
    private String status ;

    public SFile(String pathname) {
        super(pathname);
    }

    public SFile(String parent, String child) {
        super(parent, child);
    }

    public SFile(File parent, String child) {
        super(parent, child);
    }

    public SFile(File file) {
        super(file.getAbsolutePath());
    }

    public void setSyncStatus(boolean syncStatus){
        this.syncStatus = syncStatus ;
    }


    public boolean isSyncStatus() {
        return syncStatus;
    }
    public void setStatus(String status){this.status = status; }
}
