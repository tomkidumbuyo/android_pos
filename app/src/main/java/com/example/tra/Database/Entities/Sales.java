package com.example.tra.Database.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.sql.Time;

@Entity
public class Sales {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "time")
    public Time time;

    @ColumnInfo(name = "date")
    public Date date;

}
