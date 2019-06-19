package com.example.tra.Database.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Sales.class,
                parentColumns = "id",
                childColumns = "sales_id"
        ),
        @ForeignKey(
                entity = Items.class,
                parentColumns = "id",
                childColumns = "items_id"
        )
})
public class SaleItems {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @Embedded
    public Items item;

    @ColumnInfo(name = "items_id")
    public int amount;

    @ColumnInfo(name = "price_each")
    public int priceEach;

    @ColumnInfo(name = "total_price")
    public int totalPrice;

    public SaleItems(Items item, int amount, int priceEach, int totalPrice) {
        this.item = item;
        this.amount = amount;
        this.priceEach = priceEach;
        this.totalPrice = totalPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Items getItem() {
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
