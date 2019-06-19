package com.example.tra.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.tra.Database.Dao.ItemDao;
import com.example.tra.Database.Dao.SaleDao;
import com.example.tra.Database.Dao.SaleItemDao;
import com.example.tra.Database.Entities.Items;
import com.example.tra.Database.Entities.SaleItems;
import com.example.tra.Database.Entities.Sales;

@Database(entities = {Items.class, SaleItems.class, Sales.class},version = 1)
@TypeConverters({DateTimeConverter.class,DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    abstract public ItemDao itemDao();
    abstract public SaleItemDao saleItemDao();
    abstract public SaleDao saleDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .addCallback(roomCallback)
                            .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private ItemDao itemDao;

        public PopulateDbAsyncTask(AppDatabase db) {
            itemDao = db.itemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            itemDao.insert(new Items("cocacola",2000));
            return null;
        }
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
