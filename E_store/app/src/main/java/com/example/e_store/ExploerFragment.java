package com.example.e_store;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_store.adapters.CategoriesAdapter;
import com.example.e_store.adapters.ExploreProductAdapter;
import com.example.e_store.dbHelper.DataBase;
import com.example.e_store.dbHelper.DatabaseInfo;
import com.example.e_store.models.Category;
import com.example.e_store.models.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ExploerFragment extends Fragment implements  OnItemClicked{

   DataBase db ;
    View vi ;
    private SharedPreferences sharedPreferences;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DataBase(getContext());
        sharedPreferences = this.getActivity().getSharedPreferences(DatabaseInfo.SharedPreferences.Preferences, Context.MODE_PRIVATE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_exploer, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(db.getAllCategories(),this);
        ExploreProductAdapter productAdapterBestSale = new ExploreProductAdapter(db.getBestSaleProducts(), this,getContext());
        ExploreProductAdapter productAdapterRecommended = new ExploreProductAdapter(db.getRecommendedProducts(),this,getContext());

        RecyclerView CategoryRecycler = view.findViewById(R.id.categoriesRecycler);
        RecyclerView bestSaleRecycler = view.findViewById(R.id.bestSellingRecyclerView);
        RecyclerView recommendRecycler= view.findViewById(R.id.recommendRecyclerView);

        bestSaleRecycler.setLayoutManager(layoutManager);
        recommendRecycler.setLayoutManager(layoutManager2);
        CategoryRecycler.setLayoutManager(layoutManager3);

        CategoryRecycler.setAdapter(categoriesAdapter);
        recommendRecycler.setAdapter(productAdapterBestSale);
        bestSaleRecycler.setAdapter(productAdapterRecommended);
       //  fragment = view.findViewById(R.id.fragment2);
        vi =view;



        return view;
    }

    @Override
    public void OnProductClick(Product product) {
        Log.i(product.getProductName(), " has been clicked");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("curntProId", product.getProductId());
        editor.apply();
        Log.i("prduct pushed", product.getProductId());

//        ProductDetailsFragment fragment = new ProductDetailsFragment();
//        fragment.setArguments(bundle);
//    ExploerFragmentDirections.ActionExploerFragmentToProductDetailsFragment action =
//            ExploerFragmentDirections.actionExploerFragmentToProductDetailsFragment(product);

        Navigation.findNavController(vi).navigate(R.id.action_exploerFragment_to_productDetailsFragment);
        BottomNavigationView navigationView = vi.findViewById(R.id.bottom_nav);
      //  navigationView.setVisibility(View.GONE);

    }

    @Override
    public void OnCategoryClick(Category category) {

        ExploerFragmentDirections.ActionExploerFragmentToSearchFragment action =
           ExploerFragmentDirections.actionExploerFragmentToSearchFragment();
                         action.setCategory(category.getName());
                Navigation.findNavController(vi).navigate(action);

    }
}