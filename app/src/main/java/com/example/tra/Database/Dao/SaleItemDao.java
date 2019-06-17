package com.example.tra.Database.Dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.tra.Database.Entities.SaleItems;

public interface SaleItemDao {
    @Insert
    public void insert(SaleItems saleitem);

    @Update
    public void update(SaleItems saleitem);

    @Delete
    public void delete(SaleItems saleitem);
}
