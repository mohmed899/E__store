package com.example.e_store;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.e_store.dbHelper.DataBase;
import com.example.e_store.dbHelper.DatabaseInfo;
import com.example.e_store.models.User;


public class SignInFragment extends Fragment implements View.OnClickListener {
    private DataBase db;
   private User user;
   private CheckBox rememberMe;
    SharedPreferences sharedPreferences ;
    private  View view;
 EditText userEmail, userPassword;
 private  String fpass;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getContext().getSharedPreferences(DatabaseInfo.SharedPreferences.Preferences, Context.MODE_PRIVATE);

                db = new DataBase(getContext());
        if (getArguments() != null) {
            SignInFragmentArgs args = SignInFragmentArgs.fromBundle(getArguments());
            fpass = args.getForgotenPass();

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_sign_in, container, false);
         Button signInBtn = view.findViewById(R.id.signIn);
         TextView signUpNewUser = view.findViewById(R.id.signUpNewUser);
         rememberMe = view.findViewById(R.id.rememberCheckBox);
         userEmail = view.findViewById(R.id.userEmail);
         userPassword = view.findViewById(R.id.userPassword);
         if(!fpass.isEmpty())
             userPassword.setText(fpass);
         TextView forgetPassText = view.findViewById(R.id.forgetPassTextView);
         forgetPassText.setOnClickListener(this);
         signInBtn.setOnClickListener(this);
         signUpNewUser.setOnClickListener(this);


        return  view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.signUpNewUser) {
            Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_signUpFragment);
        } else if (id == R.id.signIn) {
            if (checkValidUser()) {
                saveInSharedPreferences();
                Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_productDetailsFragment);
            }
        } else if(id== R.id.forgetPassTextView){
            SignInFragmentDirections.ActionSignInFragmentToPassWordRestFragment action =SignInFragmentDirections
                    .actionSignInFragmentToPassWordRestFragment();
            action.setUserEmail(userEmail.getText().toString());
            Navigation.findNavController(view).navigate(action);
        }
    }

    boolean checkValidUser()
    {
        if(!db.isEmailExist(userEmail.getText().toString())){
            userEmail.setError("user not exist");
            return  false;
        }

        user = db.getUserByMail(userEmail.getText().toString());
        if(user == null)
            return  false;
        if(!user.getuPassword().equals(userPassword.getText().toString().trim()))
        {
            userPassword.setError("wrong Password");
            return  false;

        }
return true;
    }

    private  void saveInSharedPreferences(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(DatabaseInfo.SharedPreferences.isExistUser,true);
                    editor.putBoolean(DatabaseInfo.SharedPreferences.isRememberMe,rememberMe.isChecked());
                    editor.putString(DatabaseInfo.SharedPreferences.userName,user.getFullName());
                    editor.putString(DatabaseInfo.SharedPreferences.userId,user.getU_ID());
                    editor.putString(DatabaseInfo.SharedPreferences.userMail,user.getuEmail());
                    editor.apply();
    }

}