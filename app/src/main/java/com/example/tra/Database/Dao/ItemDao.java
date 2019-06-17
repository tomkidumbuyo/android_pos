package com.example.tra.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tra.Database.Entities.Items;

import java.util.List;

@Dao
public interface ItemDao {

    @Insert
    void insert(Items item);

    @Update
    void update(Items item);

    @Delete
    public void delete(Items item);

    @Query("SELECT * FROM items")
    LiveData<List<Items>> getAll();

    @Query("SELECT * FROM items")
    LiveData<List<Items>> findById(String id);


    void deleteAll();
}
