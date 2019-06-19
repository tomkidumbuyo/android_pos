package com.example.tra.pos.SalesFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tra.Database.Entities.SaleItems;
import com.example.tra.R;

import java.util.ArrayList;
import java.util.List;

public class SalesItemListAdapter extends RecyclerView.Adapter<SalesItemListAdapter.SalesItemListHolder> {
    private Context context; //context
    private List<SaleItems> salesItems = new ArrayList<>();
    View convertView;

    SalesItemListAdapter(Context context){
        this.context = context;
    }

    class SalesItemListHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textViewName;
        private TextView textViewPriceEach;
        private TextView textViewAmount;
        private TextView textViewTotal;

        public SalesItemListHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            textViewName = itemView.findViewById(R.id.item_name);
            textViewPriceEach = itemView.findViewById(R.id.item_price_each);
            textViewAmount = itemView.findViewById(R.id.item_amount);
            textViewTotal = itemView.findViewById(R.id.item_total_price);
        }

    }



    @NonNull
    @Override
    public SalesItemListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.selected_list_pos_items, parent, false);
        }

        return new SalesItemListAdapter.SalesItemListHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesItemListHolder holder, int position) {

        SaleItems currentItem = salesItems.get(position);
        holder.textViewName.setText(currentItem.getItem().getName());
        holder.textViewPriceEach.setText(String.valueOf(currentItem.getPriceEach()));
        holder.textViewAmount.setText(currentItem.getAmount());
        holder.textViewTotal.setText(currentItem.getTotalPrice());

    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public int getItemCount() {
        return salesItems.size();
    }

    public void setItems(List<SaleItems> items){
        this.salesItems =  items;
        notifyDataSetChanged();
    }

}
