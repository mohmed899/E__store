package com.example.e_store;

import java.util.ArrayList;

public interface OnCartItemChange {
    void onCartItemChange(ArrayList<Integer> list);
    void onItemDeleted(String productId);
}
