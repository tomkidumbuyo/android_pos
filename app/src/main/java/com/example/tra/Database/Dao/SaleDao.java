package com.example.tra.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tra.Database.Entities.Items;
import com.example.tra.Database.Entities.Sales;

@Dao
public interface SaleDao {
    @Insert
    public void insert(Sales sale);

    @Update
    public void update(Sales sale);

    @Delete
    public void delete(Sales sale);

    @Query("SELECT * FROM items WHERE id = :id LIMIT 1")
    LiveData<Items> findById(int id);
}
