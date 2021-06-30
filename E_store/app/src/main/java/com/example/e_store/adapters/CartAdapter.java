package com.example.e_store.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_store.OnCartItemChange;
import com.example.e_store.R;
import com.example.e_store.models.Product;

import java.util.ArrayList;
import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    ArrayList<Product> products = new ArrayList<>();
   ArrayList<Integer> qun = new ArrayList<>();
   OnCartItemChange onCartItemChange;
   Context c;


    public CartAdapter(ArrayList<Product> products, OnCartItemChange onCartItemChange,Context c) {
        this.onCartItemChange = onCartItemChange;
        this.products = products;
        this.c =c;
        for (int i =0 ; i<this.products.size();i++)
            qun.add(1);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkout, parent, false);
        return new CartAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String[] arrOfStr = products.get(position).getProductImg().replaceAll(" ","").split("#",0);

        holder.productPrice.setText("$"+products.get(position).getProductPrice());
        holder.productName.setText(products.get(position).getProductName());
        holder.productQun.setText(qun.get(position)+"");
        Glide.with(c).load(arrOfStr[0]).
                into(holder.productImgView);
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCartItemChange.onItemDeleted(products.get(position).getProductId());

                products.remove(position);
                qun.remove(position);
                onCartItemChange.onCartItemChange(qun);
                notifyDataSetChanged();

               // notify();
                Log.i(" delete","ss");
            }
        });
        holder.increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int q= qun.get(position)+1;
                holder.productQun.setText(q+"");
                qun.set(position,q);
                Log.i(" increas","ss");
                onCartItemChange.onCartItemChange(qun);
            }
        });
        holder.decreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int q =1;
                if(qun.get(position)>1)
                  q= qun.get(position)-1;
                holder.productQun.setText(q+"");
                qun.set(position,q);
                Log.i(" decreas","ss");
                onCartItemChange.onCartItemChange(qun);
            }
        });
     //    img by glide

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView productName, productPrice , productQun;
        ImageView productImgView;
        ImageButton increaseBtn, decreaseBtn ;
        ImageView deleteBtn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.itemCartTitle);
            productPrice = itemView.findViewById(R.id.itemCartPrice);
            productImgView = itemView.findViewById(R.id.itemCartImage);
            increaseBtn = itemView.findViewById(R.id.increaseButton);
            decreaseBtn = itemView.findViewById(R.id.decreaseButton);
            deleteBtn = itemView.findViewById(R.id.itemCartDelete);
            productQun = itemView.findViewById(R.id.productQun);


        }
    }
}
