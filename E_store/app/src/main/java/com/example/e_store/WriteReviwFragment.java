package com.example.e_store;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.e_store.dbHelper.DataBase;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class WriteReviwFragment extends BottomSheetDialogFragment {
String userId;
String productId;
String userName;
DataBase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            WriteReviwFragmentArgs args = WriteReviwFragmentArgs.fromBundle(getArguments());
            Log.i("reviwe usr id ",args.getUserId());
            Log.i("reviwe pro id ",args.getProductId());
            userId =args.getUserId();
            productId= args.getProductId();
            userName = args.getUserName();

        }
        db = new DataBase(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_write_reviw, container, false);
        RatingBar rating = view.findViewById(R.id.writeReviewRatingBar);
        EditText review = view.findViewById(R.id.reviewText);
        Button sendReviewButton = view.findViewById(R.id.sendReviewButton);
        sendReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addproductReview(productId, userId, review.getText().toString(), rating.getRating() + "",userName );


                Toast.makeText(getContext(), "review added", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        });

    //    getContext().getFrag
    return  view;
    }
}