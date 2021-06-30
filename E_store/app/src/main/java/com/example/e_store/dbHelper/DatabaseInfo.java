package com.example.e_store.dbHelper;

public class DatabaseInfo {
    public static final String DATABASE_NAME = "my_biggest_fear";
    public static  class ProductTable{
        public static final String TABLE_NAME = "products";
        public static final String ID = "pro_id";
        public static final String NAME = "product_name";
        public static final String img_urls = "Img_url1";
        public static final String price = "product_price";
        public static final String Rating= "rating";
        public static final String details= "details";


    }
    public static  class CategoryTable{
        public static final String TABLE_NAME = "categories";
        public static final String ID = "cat_id";
        public static final String NAME = "Category_name";
    }

    public static  class product_category{
        public static final String TABLE_NAME = " product_category";
        public static final String p_ID = "product_id";
        public static final String  c_ID= "category_id";
    }

    public static class Users{
        public static final String TABLE_NAME = "Users";
        public static final String UFullname = "Name";
        public static final String uEmail = "Email";
        public static final String birthday = "birthday";
        public static final String address = "address";
        public static final String uPassword = "Password";
        public static final String U_ID = "User_id";
        public static final String ImgUri = "URI";

    }
    public static  class CartProduct{
        public static final String TABLE_NAME = "Cart_product";
        public static final String cart_id = "cartId";
        public static final String product_id = "product_id";

    }

    public static class Cart{
        public static final String TABLE_NAME = "Cart_table";
        public static final String cart_id = "cart_id";
        public static final String creatorId = "user_id";

    }
    public static class Order{
        public static final String TABLE_NAME = "orders_table";
        public static final String order_id = "order_id";
        public static final String totalPrice = "totalPrice";
        public static final String creatorId = "user_id";

    }
    public static class ProductReview{
        public static final String TABLE_NAME = "product_review";
        public static final String review_id = "review_id";
        public static final String product_id = "product_id";
        public static final String creatorId = "user_id";
        public static final String reviewContent = "content";
        public static final String rating = "rating";
        public static final String reviewerNmae =" reviwerName";
    }

    public static class SharedPreferences{
        public static final String Preferences = "E_store_PreferencesName";
        public static final String isExistUser = "existUser";
        public static final String isRememberMe = "rememberMe";
        public static final String userName = "userName";
        public static final String userId = "UserId";
        public static final String userMail = "UserMail";
    }
}
