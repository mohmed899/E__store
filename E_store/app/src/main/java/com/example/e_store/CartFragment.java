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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_store.adapters.CartAdapter;
import com.example.e_store.dbHelper.DataBase;
import com.example.e_store.dbHelper.DatabaseInfo;
import com.example.e_store.models.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;



public class CartFragment extends Fragment implements OnCartItemChange , View.OnClickListener {

    private  View view;
    private ArrayList<Product> productList ;
    DataBase db ;
    CartAdapter adapter;
    RecyclerView recyclerView;
    TextView totalPrice;
    float total =0;
    String cartId;
    private SharedPreferences sharedPreferences;
    public CartFragment() {
        // Required empty public constructor
    }



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

        view =  inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = view.findViewById(R.id.cart_recycler);
         totalPrice = view.findViewById(R.id.totalPriceText);
        Button checkoutBtn = view.findViewById(R.id.checkoutButton);
        checkoutBtn.setOnClickListener(this);
        intiRecycler();
        totalPrice.setText("$"+getTotalPrice());

totalPrice.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       // Navigation.findNavController(view).navigate(R.id.action_cartFragment_to_writeReviwFragment);
    }
});

        Log.i( "cart size= " , productList.size() +"" );

        return  view;
    }


    private void intiRecycler(){
         cartId = db.getCartId(sharedPreferences.getString(DatabaseInfo.SharedPreferences.userId,"1"));
        productList = db.getProductInCart(cartId);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new CartAdapter(productList,this,getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
    float getTotalPrice(){
         total =0;

        for (Product product:productList) {
            total+=Float.parseFloat(product.getProductPrice());
        }
        return  total;
    }

    @Override
    public void onCartItemChange(ArrayList<Integer> list) {

        for (int i =0 ; i <productList.size();i++)
            total+=Float.parseFloat(productList.get(i).getProductPrice())*list.get(i);
            totalPrice.setText( "$"+String.valueOf(total));
    }

    @Override
    public void onItemDeleted(String productId) {
//        Log.i("from cart frag ",productId);
        db.deleteProductFromCart(cartId,productId);
    }

    @Override
    public void onClick(View v) {
//       if(productList.size()==0)
       //    Toast.makeText(getContext(), "No proudct added", Toast.LENGTH_SHORT).show();
        //
        db.addOrder(total+"",sharedPreferences.getString(DatabaseInfo.SharedPreferences.userId,"1"));
        CartFragmentDirections.ActionCartFragmentToOrderFragment action = CartFragmentDirections.actionCartFragmentToOrderFragment()
                ;
        action.setTotalPrice(totalPrice.getText().toString());
        Navigation.findNavController(view).navigate(action);


    }
}