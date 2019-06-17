package com.example.tra.Database.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Sales.class,
                parentColumns = "id",
                childColumns = "sales_id"
        ),
        @ForeignKey(
                entity = Items.class,
                parentColumns = "id",
                childColumns = "items_id"
        )
})
public class SaleItems {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @Embedded
    public Items item;

    @ColumnInfo(name = "items_id")
    public String items;

    @ColumnInfo(name = "sales_id")
    public String lastName;


}
