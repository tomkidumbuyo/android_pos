package com.example.tra.Database.Entities;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import com.example.tra.Database.Dao.ItemDao;
import com.example.tra.Repositories.ItemRepository;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Sales.class,
                parentColumns = "id",
                childColumns = "sale_id"
        ),
        @ForeignKey(
                entity = Items.class,
                parentColumns = "id",
                childColumns = "item_id"
        )
})
public class SaleItems {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "item_id")
    public int item;

    @ColumnInfo(name = "sale_id")
    public int sale;

    //@ColumnInfo(name = "amount")
    public int amount;

    @ColumnInfo(name = "price_each")
    public int priceEach;

    @ColumnInfo(name = "total_price")
    public int totalPrice;

    public SaleItems(int item, int amount, int priceEach, int totalPrice) {
        this.item = item;
        this.amount = amount;
        this.priceEach = priceEach;
        this.totalPrice = totalPrice;
    }

    public void setSale(Sales sale) {
        this.sale = sale.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getItem() {

        return item;
    }

    public int getAmount() {
        return amount;
    }

    public int getPriceEach() {
        return priceEach;
    }

    public int getTotalPrice() {
        return totalPrice;
    }




}
