package com.example.e_store;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.e_store.dbHelper.DataBase;
import com.example.e_store.dbHelper.DatabaseInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class SignUpFragment extends Fragment implements View.OnClickListener {
    private Calendar cal;
    private DatePickerDialog.OnDateSetListener mDate;
    private EditText userName, userEmail, userPassword, confirmPassword, userBirthDay;
    private SharedPreferences sharedPreferences;
    private DataBase db;
    private View view;
    private String userID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DataBase(getContext());
        sharedPreferences = this.getActivity().getSharedPreferences(DatabaseInfo.SharedPreferences.Preferences, Context.MODE_PRIVATE);
        cal = Calendar.getInstance();
        mDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, monthOfYear);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        userBirthDay = view.findViewById(R.id.UserBirthday);
        userEmail = view.findViewById(R.id.UserEmail);
        userPassword = view.findViewById(R.id.UserPassword);
        userName = view.findViewById(R.id.UserFullNmae);
        confirmPassword = view.findViewById(R.id.ConfirmPassword);
        Button signUpBtn = view.findViewById(R.id.button_signUp);

        userBirthDay.setOnClickListener(this);
        signUpBtn.setOnClickListener(this);





        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.UserBirthday: {
                new DatePickerDialog(getContext(), mDate, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show();
                break;

            }

            case R.id.button_signUp: {
                if (isValidUser(
                        userName.getText().toString(),
                        userBirthDay.getText().toString(),
                        userEmail.getText().toString(),
                        userPassword.getText().toString(),
                        confirmPassword.getText().toString()))
                {
                    // safe user in

//
                    userID = db.addUser(userName.getText().toString(), userEmail.getText().toString(), userPassword.getText().toString(), userBirthDay.getText().toString(), "ee");
                    SaveInPref();
                    Log.i("user id = ", userID);
                      Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_productDetailsFragment);
                }
                break;
            }
        }

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        userBirthDay.setText(sdf.format(cal.getTime()));
    }

    private boolean isValidUser(String _name, String _birthday, String _email, String _password, String _passwordConfirm) {
        if (_name.isEmpty()) {
            userName.setError("Name is required");
            return false;
        }
        if (_birthday.isEmpty()) {
            userBirthDay.setError("Birthday is required");
            return false;
        }

        if (_email.isEmpty()) {
            userEmail.setError("Email is required");
            return false;
        }
        if (db.isEmailExist(userEmail.getText().toString().trim())) {
            userEmail.setError("Email is already exist");
            return false;
        }

        if (_password.isEmpty()) {
            userPassword.setError("Password is required");
            return false;
        }
//    if (_password.length()<=6){
//        confirmPassword.setError("Password length must be more than 6");
//        return false;
        //  }
        if (!_password.equals(_passwordConfirm)) {
            confirmPassword.setError("Passwords don't match");
            return false;
        }
        return true;
    }

    private void SaveInPref() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(DatabaseInfo.SharedPreferences.isExistUser, true);
        editor.putString(DatabaseInfo.SharedPreferences.userName, userName.getText().toString());
        editor.putString(DatabaseInfo.SharedPreferences.userId, userID);
        editor.putString(DatabaseInfo.SharedPreferences.userMail,userEmail.getText().toString());
        editor.apply();

    }
}