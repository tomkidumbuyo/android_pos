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

import com.example.tra.Database.AppDatabase;
import com.example.tra.Database.Dao.ItemDao;
import com.example.tra.Database.Entities.Items;
import com.example.tra.Database.Entities.SaleItems;
import com.example.tra.R;
import com.example.tra.Repositories.ItemRepository;
import com.example.tra.pos.PosActivity;

import java.util.ArrayList;
import java.util.List;

public class SalesItemListAdapter extends RecyclerView.Adapter<SalesItemListAdapter.SalesItemListHolder> {
    private PosActivity context; //context
    private List<SaleItems> salesItems = new ArrayList<>();
    View convertView;
    Fragment fragment;

    SalesItemListAdapter(PosActivity context,Fragment fragment){
        this.context = context;
        this.fragment = fragment;
    }

    class SalesItemListHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textViewName;
        private TextView textViewPriceEach;
        private TextView textViewAmount;
        private TextView textViewTotal;

        public SalesItemListHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.selected_item_image);
            textViewName = itemView.findViewById(R.id.selected_item_name);
            textViewPriceEach = itemView.findViewById(R.id.selected_item_price_each);
            textViewAmount = itemView.findViewById(R.id.selected_item_amount);
            textViewTotal = itemView.findViewById(R.id.selected_item_total_price);
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

        // ItemRepository itemRepository = new ItemRepository()


        SaleItems currentItem = salesItems.get(position);

        ItemRepository itemRepository = new ItemRepository(context.getApplication());
        Items item = itemRepository.findById(currentItem.getItem());
        currentItem.setAmount(item.getPrice());

        holder.textViewName.setText(item.getName());
        holder.textViewPriceEach.setText(String.valueOf(item.getPrice()));
        holder.textViewAmount.setText(String.valueOf(currentItem.getAmount()));
        holder.textViewTotal.setText(String.valueOf(currentItem.getTotalPrice()));

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
