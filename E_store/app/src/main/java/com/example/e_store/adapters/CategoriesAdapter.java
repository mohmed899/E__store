package com.example.e_store.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_store.OnItemClicked;
import com.example.e_store.R;
import com.example.e_store.models.*;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {

    ArrayList<Category> categoriesList = new ArrayList<>();
    OnItemClicked itemClicked;

    public CategoriesAdapter(ArrayList<Category> categoriesList, OnItemClicked itemClicked) {
        this.categoriesList = categoriesList;
        this.itemClicked = itemClicked;
    }

    int iconIndex []={
            R.drawable.icon_mens,
            R.drawable.icon_womens,
            R.drawable.icon_devices,
            R.drawable.icon_gaming,
            R.drawable.icon_gadgets,
    };

    @NonNull
    @Override
    public CategoriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gategory, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(position<5)
        holder.imageView.setBackgroundResource(iconIndex[position]);
        holder.textView.setText(categoriesList.get(position).getName());

    }


    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
          imageView = itemView.findViewById(R.id.item_image);
          textView = itemView.findViewById(R.id.item_text);

          itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(getAdapterPosition()!=-1)
                      itemClicked.OnCategoryClick(categoriesList.get(getAdapterPosition()));
              }
          });
        }
    }
}
