package com.example.e_store;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_store.adapters.CartAdapter;
import com.example.e_store.adapters.ExploreProductAdapter;
import com.example.e_store.dbHelper.DataBase;
import com.example.e_store.dbHelper.DatabaseInfo;
import com.example.e_store.models.Product;

import java.util.ArrayList;
import java.util.List;


public class SummaryFragment extends Fragment  {
DataBase db ;
String address;

    private ArrayList<Product> productList ;
    String cartId;
    private SharedPreferences sharedPreferences;
    private View view;
    RecyclerView recyclerView;
    ExploreProductAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DataBase(getContext());
        sharedPreferences = this.getActivity().getSharedPreferences(DatabaseInfo.SharedPreferences.Preferences, Context.MODE_PRIVATE);

        if (getArguments() != null) {
            SummaryFragmentArgs args = SummaryFragmentArgs.fromBundle(getArguments());
            address = args.getAdsress();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_summary, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        TextView shippingAddress = view.findViewById(R.id.shapping_address_tv);
        shippingAddress.setText(address);
        Button checkoutBtn = view.findViewById(R.id.paybtn);
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "order Confirme", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.action_summaryFragment_to_exploerFragment);
            }
        });
        intiRecycler();




        return view;
    }

    private void intiRecycler(){
        cartId = db.getCartId(sharedPreferences.getString(DatabaseInfo.SharedPreferences.userId,"1"));
        productList = db.getProductInCart(cartId);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        adapter = new ExploreProductAdapter(productList,null,true,getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}