package com.example.tra.Database.Dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.tra.Database.Entities.Items;
import com.example.tra.Database.Entities.Sales;

public interface SaleDao {
    @Insert
    public void insert(Sales sale);

    @Update
    public void update(Sales sale);

    @Delete
    public void delete(Sales sale);
}
