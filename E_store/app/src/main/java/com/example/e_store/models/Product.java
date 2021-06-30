package com.example.e_store.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.e_store.dbHelper.DatabaseInfo;

public class Product implements Parcelable {

      String productId ;
      String productImg ;
      String productName ;
      String productDetails;
      String productPrice ;
      String ProductRate ;


    public Product(String productImg, String productName, String productDetails, String productPrice, String productRate) {
        this.productImg = productImg;
        this.productName = productName;
        this.productDetails = productDetails;
        this.productPrice = productPrice;
        ProductRate = productRate;
    }

    public Product(String productId, String productImg, String productName, String productDetails, String productPrice, String productRate) {
        this.productId = productId;
        this.productImg = productImg;
        this.productName = productName;
        this.productDetails = productDetails;
        this.productPrice = productPrice;
        ProductRate = productRate;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    protected Product(Parcel in) {
        productId = in.readString();
        productName = in.readString();
        productImg = in.readString();
        productPrice = in.readString();
        ProductRate = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductRate() {
        return ProductRate;
    }

    public void setProductRate(String productRate) {
        ProductRate = productRate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productId);
        dest.writeString(productName);
        dest.writeString(productImg);
        dest.writeString(productPrice);
        dest.writeString(ProductRate);
    }
}
