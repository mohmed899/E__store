package com.example.e_store.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_store.OnItemClicked;
import com.example.e_store.R;
import com.example.e_store.models.Product;

import java.util.ArrayList;

public class ExploreProductAdapter extends RecyclerView.Adapter<ExploreProductAdapter.MyViewHolder> {
    ArrayList<Product> products = new ArrayList<>();
    OnItemClicked itemClicked;
    boolean isSmallItem = false;
    Context c;

    public ExploreProductAdapter(ArrayList<Product> products, OnItemClicked itemClicked, boolean isSmallItem,Context c) {
        this.products = products;
        this.itemClicked = itemClicked;
        this.isSmallItem = isSmallItem;
        this.c =c;
    }

    public ExploreProductAdapter(ArrayList<Product> products, OnItemClicked itemClicked,Context c) {
        this.products = products;
        this.itemClicked = itemClicked;
        this.c =c;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ExploreProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (isSmallItem)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exploer_small, parent, false);
        else
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exploer, parent, false);
        return new ExploreProductAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreProductAdapter.MyViewHolder holder, int position) {
        String[] arrOfStr = products.get(position).getProductImg().replaceAll(" ","").split("#",0);
        holder.productName.setText(products.get(position).getProductName());
        holder.productPrice.setText(products.get(position).getProductPrice() + holder.dol);
        Glide.with(c).load(arrOfStr[0]).
                into(holder.img);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productPrice;
        ImageView img;
        String dol = "$";

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            if (isSmallItem) {
                productName = itemView.findViewById(R.id.exploreTitleTextViewSmall);
                productPrice = itemView.findViewById(R.id.explorePriceTextViewSmall);
                img = itemView.findViewById(R.id.exploreImageViewSmall);
            } else {
                productName = itemView.findViewById(R.id.exploreTitleTextView);
                productPrice = itemView.findViewById(R.id.explorePriceTextView);
                img = itemView.findViewById(R.id.exploreImageView);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() != -1)
                        itemClicked.OnProductClick(products.get(getAdapterPosition()));
                         Log.d(" is null",products.get(getPosition()).getProductId());
                }
            });
        }
    }
}
//                   if(getAdapterPosition()==null)
//                  Log.d("null",getAdapterPosition()+"");  l

                       // if(itemClicked!=null)
