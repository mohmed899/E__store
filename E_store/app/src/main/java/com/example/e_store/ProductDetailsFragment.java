package com.example.e_store;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.e_store.adapters.ReviewAdapter;
import com.example.e_store.dbHelper.DataBase;
import com.example.e_store.dbHelper.DatabaseInfo;
import com.example.e_store.models.Product;
import com.example.e_store.models.ProductReview;

import java.util.ArrayList;
import java.util.List;


public class ProductDetailsFragment extends Fragment implements View.OnClickListener {
    private Product product;
    private View view;
    private String UserId ,  userName;
    private DataBase db;
    private SharedPreferences preferences;
private  List<SlideModel> productImgSlides;
private ArrayList<ProductReview>productReviews;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DataBase(getContext());
        preferences = getContext().getSharedPreferences(DatabaseInfo.SharedPreferences.Preferences, Context.MODE_PRIVATE);
        UserId = preferences.getString(DatabaseInfo.SharedPreferences.userId, "1");
         userName = preferences.getString(DatabaseInfo.SharedPreferences.userName, "No USER");
        String curntProductId = preferences.getString("curntProId", "1");
        product = db.getProductById(curntProductId);
        productReviews = db.getProductReviews(curntProductId);
        Log.i("user from pref ", curntProductId + " name : " + userName);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product_details, container, false);
        loadSlider();



//
        // ratingBar.s

        ImageView backArrowButton = view.findViewById(R.id.backArrowButton);
        ImageSlider slider = view.findViewById(R.id.productDetailsImage);
        TextView productName = view.findViewById(R.id.productTitleHeadLine);
        TextView productDescription = view.findViewById(R.id.productDescription);
        TextView addReview = view.findViewById(R.id.productWriteReview);
        TextView productPrice = view.findViewById(R.id.totalPriceText);

        TextView emptyReviewText =view.findViewById(R.id.emptyReviewText); ;
        RecyclerView reviewsRecycler = view.findViewById(R.id.reviewsRecycler);;

        Button addToCartButton = view.findViewById(R.id.addToCartButton);

        slider.setImageList(productImgSlides);
        productPrice.setText("$" + product.getProductPrice());
        addReview.setOnClickListener(this);
        productName.setText(product.getProductName());
        productDescription.setText(product.getProductDetails());
        backArrowButton.setOnClickListener(this);
        addToCartButton.setOnClickListener(this);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        reviewsRecycler.setLayoutManager(layoutManager3);
        if(productReviews.size()>0)
            emptyReviewText.setVisibility(View.GONE);
        Log.i("reviwe size", productReviews.size()+"");

        ReviewAdapter adapter = new ReviewAdapter(productReviews);
        reviewsRecycler.setAdapter(adapter);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.backArrowButton) {
            Navigation.findNavController(view).navigate(R.id.action_productDetailsFragment_to_exploerFragment);
        } else if (id == R.id.productWriteReview) {
            if (preferences.getBoolean(DatabaseInfo.SharedPreferences.isExistUser, false)&&preferences.getBoolean(DatabaseInfo.SharedPreferences.isRememberMe, false)) {

                ProductDetailsFragmentDirections.ActionProductDetailsFragmentToWriteReviwFragment action =
                        ProductDetailsFragmentDirections.actionProductDetailsFragmentToWriteReviwFragment(UserId, product.getProductId(),userName);
                Navigation.findNavController(view).navigate(action);
            } else {
                ProductDetailsFragmentDirections.ActionProductDetailsFragmentToSignInFragment action =
                        ProductDetailsFragmentDirections.actionProductDetailsFragmentToSignInFragment("");
                Navigation.findNavController(view).navigate(action);

            }
        } else if (id == R.id.addToCartButton) {
            db.addProductToCart(product.getProductId(), db.getCartId(UserId));

            Toast.makeText(getContext(), "add to cart ", Toast.LENGTH_LONG).show();
        }

    }
    void loadSlider(){
         productImgSlides = new ArrayList<>();
        String[] arrOfStr = product.getProductImg().replaceAll(" ", "").trim().split("#", 0);
        for (String s: arrOfStr) {
            productImgSlides.add(new SlideModel(s, ScaleTypes.CENTER_CROP));
        }
    }
}