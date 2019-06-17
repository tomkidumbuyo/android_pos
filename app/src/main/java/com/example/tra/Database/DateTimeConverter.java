package com.example.tra.Database;

import androidx.room.TypeConverter;

import java.sql.Time;

public class DateTimeConverter {
    @TypeConverter
    public static Time fromTimestamp(Long value) {
        return value == null ? null : new Time(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Time date) {
        return date == null ? null : date.getTime();
    }
}
