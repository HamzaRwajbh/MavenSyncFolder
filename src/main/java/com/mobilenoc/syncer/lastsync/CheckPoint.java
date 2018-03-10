package com.mobilenoc.syncer.lastsync;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckPoint {

    private static final Logger LOGGER = Logger.getLogger(CheckPoint.class.getName());
    private static CheckPoint INSTANCE = null;
    private Properties properties = new Properties();
    private long checkPoint;
    private boolean firstSync ;


    private CheckPoint() {
        if (this.isCheckPointCreated()) {
            this.firstSync = false;
        } else {
            this.firstSync = true;
        }

    }

    public static CheckPoint getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new CheckPoint();
        }
        return INSTANCE ;
    }



    private boolean isCheckPointCreated() {
        try {
            FileInputStream in = new FileInputStream("src/main/resources/config.properties");
            this.properties.load(in);
            in.close();
            if ( this.properties.getProperty("CHECK_POINT").isEmpty() ) {
                return false;
            }else
                checkPoint =Long.parseLong(properties.getProperty("CHECK_POINT"));
        } catch (IOException e) {
            LOGGER.log(Level.WARNING , "CheckPoint file does not exist !");
            e.printStackTrace();
        }

        return true;
    }

    public void setCheckPoint(long date) {
        this.checkPoint = date;

        try {
            FileOutputStream out = new FileOutputStream("src/main/resources/config.properties");
            this.properties.setProperty("CHECK_POINT", ""+date);
            this.properties.store(out, null);
            out.close();
            firstSync = false ;
        } catch (IOException e) {
            e.fillInStackTrace();
        }

    }

    public long getCheckPoint() {
        return this.checkPoint;
    }

    public boolean isFirstSync() {
        return this.firstSync;
    }
}
