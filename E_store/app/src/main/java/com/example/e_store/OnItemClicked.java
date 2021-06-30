package com.example.e_store;

import com.example.e_store.models.Category;
import com.example.e_store.models.Product;

public interface OnItemClicked {
    void OnProductClick(Product product);
    void OnCategoryClick(Category product);

}
