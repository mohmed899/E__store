package com.example.e_store;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e_store.dbHelper.DataBase;
import com.example.e_store.dbHelper.DatabaseInfo;
import com.example.e_store.models.User;


public class AcountFragment extends Fragment implements View.OnClickListener {
    private User user;
    private SharedPreferences preferences ;
    private DataBase db ;
    private View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DataBase(getContext());
        preferences = getContext().getSharedPreferences(DatabaseInfo.SharedPreferences.Preferences, Context.MODE_PRIVATE);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_account, container, false);

        if(!preferences.getBoolean(DatabaseInfo.SharedPreferences.isExistUser,false)){
           AcountFragmentDirections.ActionAcountFragmentToSignInFragment action =
                   AcountFragmentDirections.actionAcountFragmentToSignInFragment("");
          //  Navigation.findNavController(view).navigate(action); //get error for some reason

            NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager()
                    .findFragmentById(R.id.fragment2);
            NavController navCo = navHostFragment.getNavController();
            navCo.navigate(action);

        }
        TextView accountProfileName = view.findViewById(R.id.accountProfileName);
        TextView accountProfileEmail = view.findViewById(R.id.accountProfileEmail);
        ImageView logOut = view.findViewById(R.id.logOut);


        accountProfileName.setText(preferences.getString(DatabaseInfo.SharedPreferences.userName,"Null "));
        accountProfileEmail.setText(preferences.getString(DatabaseInfo.SharedPreferences.userMail,"user null"));
        logOut.setOnClickListener(this);

        return  view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if ( id == R.id.logOut){
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear().apply();
            Navigation.findNavController(view).navigate(R.id.action_acountFragment_to_exploerFragment);
        }

    }
}