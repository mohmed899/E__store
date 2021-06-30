package com.example.e_store;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_store.adapters.ExploreProductAdapter;
import com.example.e_store.dbHelper.DataBase;
import com.example.e_store.dbHelper.DatabaseInfo;
import com.example.e_store.models.Category;
import com.example.e_store.models.Product;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class searchFragment extends Fragment implements OnItemClicked, TextWatcher {

    private final int VOICE_REQUEST = 1999;
    private ArrayList<Product> productList = new ArrayList<>();
    private RecyclerView productSearchRecycler;
    private DataBase db;
    private ExploreProductAdapter adapter;
    private View view;
    private String categoryNAme;
    private EditText searchField;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            searchFragmentArgs args = searchFragmentArgs.fromBundle(getArguments());
            if (args.getCategory() == null) {
                Log.i("is null", categoryNAme);
                categoryNAme = "";
            }
            categoryNAme = args.getCategory();
            Log.i("cat", categoryNAme);

        }
        db = new DataBase(getContext());
        productList = db.getProductByCategory(categoryNAme);
        Log.i("cat list size", productList.size() + "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);
        adapter = new ExploreProductAdapter(productList, this,getContext());
        productSearchRecycler = view.findViewById(R.id.productSearchRecycler);
        searchField = view.findViewById(R.id.search_field);
        searchField.addTextChangedListener(this);
        ImageView voice_search_img = view.findViewById(R.id.voice_search_img);
        voice_search_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(intent, VOICE_REQUEST);
            }
        });
        productSearchRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        productSearchRecycler.setAdapter(adapter);


        return view;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        productList = db.getProductInSearch(s.toString());
        adapter.setProducts(productList);
        productSearchRecycler.setAdapter(adapter);
        //    adapter.notifyDataSetChanged();
        Log.i("  text change", s.toString());
        Log.i("  text chane list size ", productList.size() + "");

    }

    @Override
    public void OnProductClick(Product product) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(DatabaseInfo.SharedPreferences.Preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("curntProId", product.getProductId());
        editor.apply();
        Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_productDetailsFragment);
        Log.i("  go too", "s.toString()");

    }

    @Override
    public void OnCategoryClick(Category product) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VOICE_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (result != null) {
                    searchField.setText(result.get(0));
                    // showCinema("cinemaBysearch",speachResult);
                }
            }
        }
    }


}