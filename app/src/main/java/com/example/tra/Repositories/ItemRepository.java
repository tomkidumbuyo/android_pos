package com.example.tra.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.tra.Database.AppDatabase;
import com.example.tra.Database.Dao.ItemDao;
import com.example.tra.Database.Entities.Items;

import java.util.List;

public class ItemRepository {

    private ItemDao itemDao;
    private LiveData<List<Items>> all;
    private  AppDatabase database;

    public ItemRepository(Application application){
        database = AppDatabase.getInstance(application);
        itemDao = database.itemDao();
        all =  itemDao.getAll();
    }

    public void insert(Items item){
        new InsertItemAsyncTask(itemDao).execute(item);
    }
    public void update(Items item){
        new UpdateItemAsyncTask(itemDao).execute(item);
    }
    public void delete(Items item){
        new DeleteItemAsyncTask(itemDao).execute(item);
    }

    public Items findById(int id){
        return itemDao.findById(id);
    }

    public LiveData<List<Items>> getAll(){
        return all;
    }

    private static class InsertItemAsyncTask extends AsyncTask<Items, Void, List<Items>> {

        private ItemDao itemDao;

        private InsertItemAsyncTask(ItemDao itemDao){
            this.itemDao = itemDao;
        }

        @Override
        protected List<Items> doInBackground(Items... items) {
            itemDao.insert(items[0]);
            return null;
        }
    }

    private static class UpdateItemAsyncTask extends AsyncTask<Items, Void, List<Items>> {

        private ItemDao itemDao;

        private UpdateItemAsyncTask(ItemDao itemDao){
            this.itemDao = itemDao;
        }

        @Override
        protected List<Items> doInBackground(Items... items) {
            itemDao.update(items[0]);
            return null;
        }
    }

    private static class DeleteItemAsyncTask extends AsyncTask<Items, Void, List<Items>> {

        private ItemDao itemDao;

        private DeleteItemAsyncTask(ItemDao itemDao){
            this.itemDao = itemDao;
        }

        @Override
        protected List<Items> doInBackground(Items... items) {
            itemDao.delete(items[0]);
            return null;
        }
    }




}
