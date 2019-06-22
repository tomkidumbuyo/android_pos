package com.example.tra.pos.SalesFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tra.Database.Entities.Items;
import com.example.tra.R;

import java.util.ArrayList;
import java.util.List;

public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.ItemsListHolder> {

    private Context context; //context
    private List<Items> items = new ArrayList<>();
    View convertView;
    SalesFragment fragment;
    Items currentItem;

    ItemsListAdapter(Context context, SalesFragment fragment){
        this.fragment = fragment;
        this.context = context;
    }

    class ItemsListHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textViewName;
        private TextView textViewPriceEach;


        public ItemsListHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            textViewName = itemView.findViewById(R.id.item_name);
            textViewPriceEach = itemView.findViewById(R.id.item_price_each);
        }

    }



    @NonNull
    @Override
    public ItemsListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.list_pos_items, parent, false);

            convertView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    fragment.addItemToSale(currentItem);
                }
            });
        }
        return new ItemsListHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsListHolder holder, int position) {
        currentItem = items.get(position);
        holder.textViewName.setText(currentItem.getName());
        holder.textViewPriceEach.setText(String.valueOf(currentItem.getPrice()));

//        holder.textViewAmount.setText();
//        holder.textViewName.setText();

    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Items> items){
        this.items =  items;
        notifyDataSetChanged();
    }


}

