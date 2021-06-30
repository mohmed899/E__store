package com.example.e_store;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_store.dbHelper.DataBase;
import com.example.e_store.models.User;


public class PassWordRestFragment extends Fragment {

    private View view;
    private String userEmail;
    private String userPass;
    private DataBase db ;
    private User user;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DataBase(getContext());
        if (getArguments() != null) {
            PassWordRestFragmentArgs args = PassWordRestFragmentArgs.fromBundle(getArguments());
            userEmail = args.getUserEmail();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pass_word_rest, container, false);
        EditText EmailAddressRest = view.findViewById(R.id.EmailAddressRest);
        EmailAddressRest.setText(userEmail);
        EditText forgotenPass = view.findViewById(R.id.forgotenPass);
        Button getPassBtn = view.findViewById(R.id.getPassBtn);
        getPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              userEmail=   EmailAddressRest.getText().toString();
              if(!userEmail.isEmpty()){
                  if(!db.isEmailExist(userEmail)){
                      Toast.makeText(getContext(), "No such User", Toast.LENGTH_SHORT).show();
                  }
                  else {

                       user = db.getUserByMail(userEmail);

                      PassWordRestFragmentDirections.ActionPassWordRestFragmentToSignInFragment  action =
                              PassWordRestFragmentDirections.actionPassWordRestFragmentToSignInFragment(user.getuPassword());
                    //  action.setForgotenPass(user.getuPassword());
                      Navigation.findNavController(view).navigate(action);

                  }

              }
            }
        });

        return  view;
    }
}