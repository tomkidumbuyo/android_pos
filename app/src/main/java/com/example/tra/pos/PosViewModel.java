package com.example.tra.pos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tra.Database.Entities.Items;
import com.example.tra.Repositories.ItemRepository;

import java.util.List;

public class PosViewModel extends AndroidViewModel {
    private ItemRepository itemRepository;
    private LiveData<List<Items>> allItems;

    public PosViewModel(@NonNull Application application) {
        super(application);
        itemRepository = new ItemRepository(application);
        allItems = itemRepository.getAll();
    }

    public void insertItem(Items item){
        itemRepository.insert(item);
    }

    public void updateItem(Items item){
        itemRepository.update(item);
    }

    public void deleteItem(Items item){
        itemRepository.delete(item);
    }

    public LiveData<List<Items>> getAllItems(){
        return allItems;
    }

}
