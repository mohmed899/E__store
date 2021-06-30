package com.example.e_store;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e_store.dbHelper.DataBase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavController.OnDestinationChangedListener {
DataBase db;
Context c ;
    TextView searchBar;
    BottomNavigationView navigationView;
    ImageView voiceSearch ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c= this;
         searchBar  = findViewById(R.id.search_field);
        searchBar.setOnClickListener(this);


         navigationView = findViewById(R.id.bottom_nav);
        voiceSearch =  findViewById(R.id.voice_search_img);
     //   navigationView.set
       NavigationUI.setupWithNavController(navigationView, Navigation.findNavController(this,R.id.fragment2 ));
        db = new DataBase(this);
      //  NavigationUI.onNavDestinationSelected()
        Navigation.findNavController(this,R.id.fragment2 ).addOnDestinationChangedListener(this);
    //    db.addProduct("chair","img","100$","5");
    //    db.addProduct("chair","img","100$","5");
     // db.getProductById("1");
       // Log.i("pro name", s);
     //db.intt();


    }

    @Override
    public void onClick(View v) {

        Navigation.findNavController(this,R.id.fragment2 ).navigate(R.id.searchFragment);

    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        int id = destination.getId();
        if(id == R.id.exploerFragment){
            searchBar.setVisibility(View.VISIBLE);
            navigationView.setVisibility(View.VISIBLE);
            voiceSearch.setVisibility(View.VISIBLE);
        }
        else if(id == R.id.acountFragment ||id == R.id.cartFragment){
            searchBar.setVisibility(View.GONE);
            navigationView.setVisibility(View.VISIBLE);
            voiceSearch.setVisibility(View.GONE);
        }
        else //if (id == R.id.searchFragment ||destination.getId() == R.id.productDetailsFragment)
             {
            searchBar.setVisibility(View.GONE);
            navigationView.setVisibility(View.GONE);
                 voiceSearch.setVisibility(View.GONE);
        }

    }
}