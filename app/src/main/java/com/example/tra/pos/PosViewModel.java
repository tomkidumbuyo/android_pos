package com.example.tra.pos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tra.Database.Entities.Items;
import com.example.tra.Database.Entities.SaleItems;
import com.example.tra.Repositories.ItemRepository;

import java.util.List;

public class PosViewModel extends AndroidViewModel {
    private ItemRepository itemRepository;
    private LiveData<List<Items>> allItems;
    private List<SaleItems> _saleItems;
    private MutableLiveData<List<SaleItems>> saleItems = new MutableLiveData<List<SaleItems>>();

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

    public LiveData<List<SaleItems>> getSaleItems(){
        return saleItems;
    }

    public void addItemToSale(Items item){

        SaleItems saleItem = new SaleItems(item.id,1,item.price,item.price);
        _saleItems.add(saleItem);
        saleItems.postValue(_saleItems);

    }

}
