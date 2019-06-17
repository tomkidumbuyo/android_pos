package com.example.tra;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by ua on 11/19/2018.
 */

public class MyApplication extends Application {

    private Boolean isStarting = true;
    private SQLiteDatabase db;

    public Boolean getIsStarting() {
        return isStarting;
    }

    public void setIsStarting(Boolean someVariable) {
        this.isStarting = someVariable;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

//    public void setMusicPlayer(MusicPlayer musicPlayer) {
//        this.musicPlayer = musicPlayer;
//    }
//
//    public MusicPlayer getMusicPlayer() {
//        return musicPlayer;
//    }
}
