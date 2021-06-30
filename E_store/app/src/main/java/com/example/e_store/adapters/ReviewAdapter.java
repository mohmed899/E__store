package com.example.e_store.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_store.OnItemClicked;
import com.example.e_store.R;
import com.example.e_store.models.Category;
import com.example.e_store.models.ProductReview;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

    ArrayList<ProductReview> reviewList = new ArrayList<>();


    public ReviewAdapter(ArrayList<ProductReview> reviewLest) {
        this.reviewList = reviewLest;

    }



    @NonNull
    @Override
    public ReviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ReviewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.MyViewHolder holder, int position) {
        holder.rateUsername.setText(reviewList.get(position).getReviewerName());
        holder.rateReviewText.setText(reviewList.get(position).getReviewContent());
        holder.ratingBar.setRating(Float.parseFloat(reviewList.get(position).getRating()));

    }


    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {
        RatingBar ratingBar;
        TextView rateUsername,rateReviewText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rateUsername = itemView.findViewById(R.id.rateUsername);
            rateReviewText = itemView.findViewById(R.id.rateReviewText);
            ratingBar = itemView.findViewById(R.id.ratingBar);


        }
    }
}
