package com.example.tra.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tra.Database.Entities.Items;
import com.example.tra.Database.Entities.SaleItems;

@Dao
public interface SaleItemDao {
    @Insert
    public void insert(SaleItems saleitem);

    @Update
    public void update(SaleItems saleitem);

    @Delete
    public void delete(SaleItems saleitem);

    @Query("SELECT * FROM items WHERE id = :id LIMIT 1")
    public LiveData<Items> findById(int id);
}
