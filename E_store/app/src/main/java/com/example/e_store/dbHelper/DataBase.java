package com.example.e_store.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.e_store.models.Category;
import com.example.e_store.models.Product;
import com.example.e_store.models.ProductReview;
import com.example.e_store.models.User;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    public static String dataBaseNAme = "dataBase";
    SQLiteDatabase db;


    public DataBase(@Nullable Context context) {
        super(context, dataBaseNAme, null, 1);
    }

    @Override
    //create user table
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + DatabaseInfo.Users.TABLE_NAME + "("
                + DatabaseInfo.Users.U_ID + " INTEGER PRIMARY KEY,"
                + DatabaseInfo.Users.UFullname + " text,"
                + DatabaseInfo.Users.ImgUri + " text,"
                + DatabaseInfo.Users.uEmail + " text,"
                + DatabaseInfo.Users.birthday + " text,"
                + DatabaseInfo.Users.address + " text,"
                + DatabaseInfo.Users.uPassword + " text);");

        // create product table
        sqLiteDatabase.execSQL("CREATE TABLE " + DatabaseInfo.ProductTable.TABLE_NAME + "("
                + DatabaseInfo.ProductTable.ID + " INTEGER PRIMARY KEY,"
                + DatabaseInfo.ProductTable.img_urls + " text,"
                + DatabaseInfo.ProductTable.NAME + " text,"
                + DatabaseInfo.ProductTable.details + " text,"
                + DatabaseInfo.ProductTable.price + " text,"
                + DatabaseInfo.ProductTable.Rating + " text);");

        //create category table
        sqLiteDatabase.execSQL("CREATE TABLE " + DatabaseInfo.CategoryTable.TABLE_NAME + "("
                + DatabaseInfo.CategoryTable.ID + " INTEGER PRIMARY KEY,"
                + DatabaseInfo.CategoryTable.NAME + " text);");

        //create product_category table
        sqLiteDatabase.execSQL("CREATE TABLE " + DatabaseInfo.product_category.TABLE_NAME + "(\n" +
                DatabaseInfo.product_category.c_ID + " INTEGER NOT NULL,\n" +
                DatabaseInfo.product_category.p_ID + " INTEGER NOT NULL,\n" +
                "    FOREIGN KEY (" + DatabaseInfo.product_category.c_ID + ") REFERENCES categories (cat_id) ,\n" +
                "    FOREIGN KEY (" + DatabaseInfo.product_category.p_ID + ") REFERENCES products (pro_id)\n" +
                ");");


        //create product_category table
        sqLiteDatabase.execSQL("CREATE TABLE " + DatabaseInfo.CartProduct.TABLE_NAME + "(\n" +
                DatabaseInfo.CartProduct.cart_id + " INTEGER NOT NULL,\n" +
                DatabaseInfo.CartProduct.product_id + " INTEGER NOT NULL,\n" +
                "    FOREIGN KEY (" + DatabaseInfo.CartProduct.cart_id + ") REFERENCES Cart_table (cat_id) ,\n" +
                "    FOREIGN KEY (" + DatabaseInfo.CartProduct.product_id + ") REFERENCES products (pro_id)\n" +
                ");");


        // create cart table
        sqLiteDatabase.execSQL("CREATE TABLE " + DatabaseInfo.Cart.TABLE_NAME + "("
                + DatabaseInfo.Cart.cart_id + " INTEGER PRIMARY KEY,"
                + DatabaseInfo.Cart.creatorId + " text);");


        // create orders table
        sqLiteDatabase.execSQL("CREATE TABLE " + DatabaseInfo.Order.TABLE_NAME + "("
                + DatabaseInfo.Order.order_id + " INTEGER PRIMARY KEY,"
                + DatabaseInfo.Order.totalPrice + " text,"
                + DatabaseInfo.Order.creatorId + " text);");


        // create review table
        sqLiteDatabase.execSQL("CREATE TABLE " + DatabaseInfo.ProductReview.TABLE_NAME + "("
                + DatabaseInfo.ProductReview.review_id + " INTEGER PRIMARY KEY,"
                + DatabaseInfo.ProductReview.product_id + " text,"
                + DatabaseInfo.ProductReview.creatorId + " text,"
                + DatabaseInfo.ProductReview.rating + " text,"
                + DatabaseInfo.ProductReview.reviewContent + " text,"
                + DatabaseInfo.ProductReview.reviewerNmae + " text);");


////////////////////////////////////////////////////////////////////////////////////
        //insert val
//
//        sqLiteDatabase.execSQL("INSERT INTO "
//                + DatabaseInfo.ProductTable.TABLE_NAME +
//                "("
//                + DatabaseInfo.ProductTable.NAME
//                + ',' +
//                DatabaseInfo.ProductTable.price
//                + ',' +
//                DatabaseInfo.ProductTable.Rating
//                + ',' +
//                DatabaseInfo.ProductTable.img_urls
//                + ")" +
//
//                "VALUES" +
//
//                "\t(\"pro3\",\"100#\",\"5\",\"img_url\");"
//        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addProduct(String name, String img, String price, String rate, String details) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.ProductTable.NAME, name);
        cv.put(DatabaseInfo.ProductTable.details, details);
        cv.put(DatabaseInfo.ProductTable.img_urls, img);
        cv.put(DatabaseInfo.ProductTable.price, price);
        cv.put(DatabaseInfo.ProductTable.Rating, rate);
        db = getWritableDatabase();
        db.insert(DatabaseInfo.ProductTable.TABLE_NAME, null, cv);
        Log.d("test res", "Fname");
        db.close();

    }

    public void addCategory(String name) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.CategoryTable.NAME, name);
        db = getWritableDatabase();
        db.insert(DatabaseInfo.CategoryTable.TABLE_NAME, null, cv);
        Log.d("test res", "Fname");
        db.close();

    }

    public void addOrder(String totalPrice , String userId) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.Order.creatorId, userId);
        cv.put(DatabaseInfo.Order.totalPrice, totalPrice);
        db = getWritableDatabase();
        db.insert(DatabaseInfo.Order.TABLE_NAME, null, cv);
        Log.d("test res", "Fname");
        db.close();

    }

    public void addCart(String userId) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.Cart.creatorId, userId);
        db = getWritableDatabase();
        db.insert(DatabaseInfo.Cart.TABLE_NAME, null, cv);
        Log.d("test res", "Fname");
        db.close();

    }


    // initialize adb

    public void addProductToCart(String productId, String cartId) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.CartProduct.product_id, productId);
        cv.put(DatabaseInfo.CartProduct.cart_id, cartId);
        db = getWritableDatabase();
        db.insert(DatabaseInfo.CartProduct.TABLE_NAME, null, cv);
        Log.d("test res", "product to cart ");
        db.close();

    }


    /// start query

    public void addProductCategory(String productID, String categoryId) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.product_category.p_ID, productID);
        cv.put(DatabaseInfo.product_category.c_ID, categoryId);
        db = getWritableDatabase();
        db.insert(DatabaseInfo.product_category.TABLE_NAME, null, cv);
        Log.d("test res", "Fname");
        db.close();

    }

    // get categories

    public String addUser(String fullName, String uEmail, String uPassword, String uBirthDay, String img_url) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.Users.UFullname, fullName);
        cv.put(DatabaseInfo.Users.uEmail, uEmail);
        cv.put(DatabaseInfo.Users.uPassword, uPassword);
        cv.put(DatabaseInfo.Users.birthday, uBirthDay);
        cv.put(DatabaseInfo.Users.ImgUri, img_url);
        db = getWritableDatabase();
        db.insert(DatabaseInfo.Users.TABLE_NAME, null, cv);
        Log.d("test res", "Fname");
        db.close();
        String userId = getUserId(uEmail, fullName);
        addCart(userId);
        return userId;

    }

    public void addCart(String cartId, String userId) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.Cart.cart_id, cartId);
        cv.put(DatabaseInfo.Cart.creatorId, userId);
        db = getWritableDatabase();
        db.insert(DatabaseInfo.Cart.TABLE_NAME, null, cv);
        Log.d("test res", "Fname");
        db.close();

    }

    public void addCartProduct(String cartId, String productId) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.CartProduct.cart_id, cartId);
        cv.put(DatabaseInfo.CartProduct.product_id, productId);
        db = getWritableDatabase();
        db.insert(DatabaseInfo.CartProduct.TABLE_NAME, null, cv);
        Log.d("test res", "Fname");
        db.close();

    }

    public void addproductReview(String productId, String userId, String content, String rating,String reviewerNmae) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseInfo.ProductReview.product_id, productId);
        cv.put(DatabaseInfo.ProductReview.creatorId, userId);
        cv.put(DatabaseInfo.ProductReview.reviewContent, content);
        cv.put(DatabaseInfo.ProductReview.reviewerNmae, reviewerNmae);
        cv.put(DatabaseInfo.ProductReview.rating, rating);

        db = getWritableDatabase();
        db.insert(DatabaseInfo.ProductReview.TABLE_NAME, null, cv);
        Log.d("test res", "Fname");
        db.close();

    }

    public Product getProductById(String filter) {

        String sql = " select * from " + DatabaseInfo.ProductTable.TABLE_NAME +
                " where " + DatabaseInfo.ProductTable.ID + " = " + "'"+ filter+"'";
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if( cursor!=null){
            cursor.moveToFirst();
            return new Product(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5));


        }

        return null ;
    }



    public void intt() {
//        addUser("mohamed khaled", "admin", "admin", "1999", "uri");
//
//        addProduct("shoes-HEX", "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/11981344/2021/4/21/b01d9582-5571-422f-8f50-6e1dffcc93721618980766825-HRX-by-Hrithik-Roshan-Men-Blue-Mesh-Running-Shoes-5516189807-1.jpg#https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/11981344/2021/4/21/ad1db41e-13d5-4ba3-9589-a7404a7a1d731618980766808-HRX-by-Hrithik-Roshan-Men-Blue-Mesh-Running-Shoes-5516189807-2.jpg#https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/11981344/2021/4/21/4981b096-81d1-49e9-8ef7-ae8c5149984a1618980766755-HRX-by-Hrithik-Roshan-Men-Blue-Mesh-Running-Shoes-5516189807-5.jpg",
//                "200", "5", "A pair of blue running sports shoes, has regular styling, lace-up detail Mesh upperCushioned footbed");
//
//String ur2 = "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12366546/2021/1/14/8377d32b-97e6-44c8-82e1-19ed45b15cca1610612682740-US-Polo-Assn-Men-Blue-ZENO-Walking-Shoes-3741610612681266-1.jpg" +
//        "#" +
//        "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12366546/2021/1/14/109bcc96-2bfc-4f7f-8078-5e268f5e12271610612682690-US-Polo-Assn-Men-Blue-ZENO-Walking-Shoes-3741610612681266-4.jpg" +
//        "#" +
//        "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12366546/2021/1/14/afbf3bad-4e9a-4a49-a4e5-80eec8a79cac1610612682671-US-Polo-Assn-Men-Blue-ZENO-Walking-Shoes-3741610612681266-5.jpg";
//
//        addProduct("shoes",ur2.trim() , "50", "5", "A pair of blue walking sports shoes, has regular styling, slip-on detail" +
//                "Textile upper" +
//                "Cushioned footbed");
//
//        addProduct("Shirt", "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/13298404/2021/4/20/9c3c3ef9-dcad-462c-8266-cc28dabcd2671618909196245-Levis-Men-Tshirts-111618909195597-1.jpg" +
//                "#" +
//                "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/13298404/2021/4/20/359a8923-e7f0-4519-988d-d19009c5b5e81618909196227-Levis-Men-Tshirts-111618909195597-2.jpg" +
//                "#" +
//                "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/13298404/2021/4/20/c881d858-6101-4496-9a62-6049d27d40961618909196191-Levis-Men-Tshirts-111618909195597-4.jpg", "300", "5", "Black and Orange striped T-shirt, has a polo collar, and short sleeves");
//
//        addProduct("Watch", "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/13036796/2021/1/6/bb6d18c9-39c1-4632-bc4a-f452606965ef1609906124788-WROGN-Men-Silver-Toned-Analogue-Watch-WRG00048A-802160990612-1.jpg" +
//                "#" +
//                "" +
//                "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/13036796/2021/1/6/e55fab4c-3b23-4135-9c9f-90d33e9c7f9f1609906124752-WROGN-Men-Silver-Toned-Analogue-Watch-WRG00048A-802160990612-2.jpg" +
//                "#" +
//                "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/13036796/2021/1/6/0bdda145-4ee7-45fe-8155-3f60a72b11ae1609906124709-WROGN-Men-Silver-Toned-Analogue-Watch-WRG00048A-802160990612-3.jpg", "70", "5", "it's super friendly product go to buy ot now");
//
//        addProduct("Shoes", "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12721068/2021/2/17/15ae8152-bbbe-4b2d-ad6d-e746c424df2d1613544261871-Shezone-Women-Beige-Solid-Ballerinas-241613544259949-1.jpg #" +
//                "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12721068/2021/2/17/0bf4f4ba-7ddf-484c-b244-37bc23e0dba61613544261853-Shezone-Women-Beige-Solid-Ballerinas-241613544259949-2.jpg #" +
//                "" +
//                "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12721068/2021/2/17/5bfacecc-fc1e-49a5-bc99-5e3afae495d91613544261761-Shezone-Women-Beige-Solid-Ballerinas-241613544259949-7.jpg", "300", "5", "Black and pink printed woven wrap dress with tie-up detail, has a v-neck, three-quarter sleeves");
//        addProduct("Dress", "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/10856160/2019/11/5/f0172470-408a-4b8b-a1e5-045bd98a5f8b1572938035104-SASSAFRAS-Women-Dresses-9581572938032856-1.jpg #" +
//                "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/10856160/2019/11/5/a73ea78b-7e8b-4bce-840c-17417808d2c01572938035053-SASSAFRAS-Women-Dresses-9581572938032856-2.jpg ", "500", "3.5", "Black and pink printed woven wrap dress with tie-up detail, has a v-neck, three-quarter sleeves");
//
//        addProduct("Wkon Dress", "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12288048/2020/8/25/41a01beb-5e04-4af6-849b-051eb8595e401598351182046-SASSAFRAS-Women-Dresses-2421598351180777-1.jpg #" +
//                "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12288048/2020/8/26/4acea92b-7e72-4aaa-9671-cea6985092251598437260455-SASSAFRAS-Women-Black-Solid-Semi-Sheer-Off-Shoulder-Maxi-Tie-2.jpg #" +
//                "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/12288048/2020/8/25/5c7e69fb-fbe5-450a-b288-0ea4048d25a51598353815752-SASSAFRAS-Women-Dresses-581598353814976-5.jpg", "75", "1", "Blue embroidered semi-stitched dress material" +
//                "Blue embroidered kurta fabric with sequins");
//
//        addProduct("Makeup", "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/14269682/2021/6/9/0ac1c777-2efc-4b87-bf1f-d02c0f00cb2e1623230345006-Renee-The-Fab10-Combo-Lipstick-7341623230344857-1.jpg #" +
//                "" +
//                "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/14269682/2021/6/9/fb2c7519-7d61-4d6a-94ec-892f434690251623230344981-Renee-The-Fab10-Combo-Lipstick-7341623230344857-4.jpg #" +
//                "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/14269682/2021/6/9/2a85b3c2-3902-41f4-8ae6-be1b8f5608d41623230344974-Renee-The-Fab10-Combo-Lipstick-7341623230344857-5.jpg", "750", "5", "Enjoy the burst of pop & the naughtiness of nude with Fab 10 ComboWhat's better than having one stick with 5 shades? Having two of them with 10 luscious colors that'll sort you ");
//
//        addProduct("Spray", "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2019/6/11/56e401f0-10f6-4186-ad39-64ee86e15ff61560231060790-1.jpg #" +
//                "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2019/6/11/67adf514-5b85-434e-9313-8dae91a5bb581560231060824-2.jpg #" +
//                "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2019/6/11/d9ded563-5afb-497f-bb97-31edecb415121560231060847-3.jpg", "150", "5", "The Roman Night Eau De Parfum");
//
//        addProduct("Lamp", "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2020/10/18/90ae5b67-aa1e-4272-b9b3-c002acb173281602976759214-1.jpg #" +
//                "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2020/10/18/8158ca39-aeff-4b93-98f8-777c21ccbbd31602976759255-2.jpg #" +
//                "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/productimage/2020/10/18/b062fdf5-6ce6-4d5a-8e2c-1d0f93fc23e91602976759305-3.jpg", "250", "5", "Type: Traditional Floor Lamp with Shade" +
//                "Colour: White");
//
//        addProduct("Lamb2", "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/9246299/2019/4/9/d0c95231-b756-4418-9b63-f71c5b270b041554792507357-Homesake-Black-Solid-Handcrafted-Table-Top-Lamp-889155479250-1.jpg #" +
//                "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/9246299/2019/4/9/ab035898-de66-4e10-bb7a-4d74be087f181554792507280-Homesake-Black-Solid-Handcrafted-Table-Top-Lamp-889155479250-3.jpg", "10", "5", "it's super friendly product go to buy ot now");
//
//        addProduct("Immortals: Fenyx Rising", "https://c1.neweggimages.com/ProductImageCompressAll1280/74-170-329-V05.jpg #" +
//                "https://c1.neweggimages.com/ProductImageCompressAll1280/74-170-329-V02.jpg", "100", "5", "FIGHT THE UNDEAD IN AN ALL-NEW HORROR STORY NO TREYARCH TITLE WOULD BE COMPLETE WITHOUT ITS SIGNATURE ZOMBIES OFFERING");
//
//        addProduct("Watch Dogs Legion", "https://c1.neweggimages.com/ProductImageCompressAll1280/74-170-327-S01.jpg #" +
//                "https://images10.newegg.com/BizIntell/item/74/170/74-170-327/Watch_Dogs_Legion.jpg #" +
//                "https://images10.newegg.com/BizIntell/item/74/170/74-170-327/pre-order-bonus.jpg", "150", "5", "FIGHT THE UNDEAD IN AN ALL-NEW HORROR STORY NO TREYARCH TITLE WOULD BE COMPLETE WITHOUT ITS SIGNATURE ZOMBIES OFFERING");
//
//        addProduct("Diablo III Eternal Collection", "https://c1.neweggimages.com/ProductImageCompressAll1280/A0ZX_1_201806281055042677.jpg #" +
//                "https://images10.newegg.com/BizIntell/item/74/117/74-117-318/g03_042915.jpg", "180", "5", "FIGHT THE UNDEAD IN AN ALL-NEW HORROR STORY NO TREYARCH TITLE WOULD BE COMPLETE WITHOUT ITS SIGNATURE ZOMBIES OFFERING");
//
//        addProduct("COD", "https://c1.neweggimages.com/ProductImageCompressAll1280/74-117-318-11.jpg #" +
//                "https://images10.newegg.com/BizIntell/item/74/117/74-117-318/g04_042915.jpg", "250", "5", "FIGHT THE UNDEAD IN AN ALL-NEW HORROR STORY NO TREYARCH TITLE WOULD BE COMPLETE WITHOUT ITS SIGNATURE ZOMBIES OFFERING");
//
//
//
//        addProduct("Speaker", "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/8468439/2019/1/14/99d4ba83-f7d1-4f80-b838-6610c674929a1547445645482-JBL-Charge-4-Powerful-Portable-Speaker-with-Built-in-Powerba-1.jpg#https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/8468439/2019/1/14/867678cc-5de8-4bc9-8269-c650f49c98c81547445645445-JBL-Charge-4-Powerful-Portable-Speaker-with-Built-in-Powerba-2.jpg", "100", "5", "Introducing the JBL Charge 4 portable Bluetooth speaker with full-spectrum,");
//        addProduct("Head phone ", "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/13645614/2021/2/17/ba7ff36a-bc4a-4cb8-8e89-f636e195ea281613538582105-Boult-Audio-ProBass-Curve-Wireless-Neckband-Earphones-with-1-1.jpg#" +
//                "" +
//                "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/13645614/2021/2/17/9f245cd7-7aea-4149-b51e-40ba535131af1613538582083-Boult-Audio-ProBass-Curve-Wireless-Neckband-Earphones-with-1-2.jpg #" +
//                "https://assets.myntassets.com/h_1440,q_90,w_1080/v1/assets/images/13645614/2021/2/17/9d7a9e2c-b865-4be2-a8ed-43c948a367c81613538582028-Boult-Audio-ProBass-Curve-Wireless-Neckband-Earphones-with-1-5.jpg", "350", "5", "Boult Audio Probass Curve Pro is the stylish neckband with premium finish.");
//




//        addCategory("men");
//        addCategory("women");
//        addCategory("games");
//        addCategory("gadget");
//        addCategory("mobile");


        addProductCategory("6", "2");
        addProductCategory("7", "2");
        addProductCategory("8", "2");
        addProductCategory("9", "2");
        addProductCategory("12", "3");
        addProductCategory("13", "3");
        addProductCategory("14", "3");
        addProductCategory("15", "3");
        addProductCategory("16", "4");
        addProductCategory("17", "4");


    }

    //get cart
    public String getCartId(String userId) {

        String sql = " select " + DatabaseInfo.Cart.cart_id + " from " + DatabaseInfo.Cart.TABLE_NAME + " where " + DatabaseInfo.Cart.creatorId + " = " + "'" + userId + "'";
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }
    public ArrayList< ProductReview> getProductReviews(String productId) {
ArrayList<ProductReview> productReviews = new ArrayList<>();
        String sql = " select *"  + " from " + DatabaseInfo.ProductReview.TABLE_NAME + " where " + DatabaseInfo.ProductReview.product_id + " = " + "'" + productId + "'";
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor!= null){
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                productReviews.add(new ProductReview(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(4),
                        cursor.getString(3),
                        cursor.getString(5)
                       ));
                cursor.moveToNext();
            }
            cursor.close();

        }

        return productReviews;
    }
    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> categoryList = new ArrayList<>();
        String sql = " select * from " + DatabaseInfo.CategoryTable.TABLE_NAME;
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                categoryList.add(new Category(cursor.getString(1)));
                cursor.moveToNext();
            }
        }
        Log.i("category list size ", categoryList.size() + "");
        return categoryList;
    }



    // product
    public ArrayList<Product> getBestSaleProducts() {
        ArrayList<Product> categoryList = new ArrayList<>();
        String sql = " select * from " + DatabaseInfo.ProductTable.TABLE_NAME + " where " + DatabaseInfo.ProductTable.price + " > " + "100";
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                categoryList.add(new Product(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)));
                cursor.moveToNext();
            }
        }
        Log.i("product list size ", categoryList.size() + "");
        return categoryList;
    }

    public ArrayList<Product> getRecommendedProducts() {
        ArrayList<Product> categoryList = new ArrayList<>();
        String sql = " select * from " + DatabaseInfo.ProductTable.TABLE_NAME + " where " + DatabaseInfo.ProductTable.Rating + " > " + 4;
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                categoryList.add(new Product(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)));
                cursor.moveToNext();
            }
        }
        Log.i("product list size ", categoryList.size() + "");
        return categoryList;
    }

    private String getUserId(String mail, String name) {
        String sql = " select " + DatabaseInfo.Users.U_ID + " from " +
                DatabaseInfo.Users.TABLE_NAME + " where " + DatabaseInfo.Users.uEmail + " = " + "'" + mail + "'" + " And " +
                DatabaseInfo.Users.UFullname + " = " + "'" + name + "'";
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
            return cursor.getString(0);

        }
        return "fail";
    }

    public ArrayList<Product> getProductInSearch(String searchText) {
        ArrayList<Product> productList = new ArrayList<>();
        if(!searchText.isEmpty()){
        searchText +="%";


        //String productId, String productImg, String productName, String productDetails, String productPrice, String productRate)
        String sql = "Select " + DatabaseInfo.ProductTable.ID + ',' + DatabaseInfo.ProductTable.img_urls + ',' + DatabaseInfo.ProductTable.NAME + ',' +
                DatabaseInfo.ProductTable.details + ',' + DatabaseInfo.ProductTable.price + ',' + DatabaseInfo.ProductTable.Rating
                + " from " + DatabaseInfo.product_category.TABLE_NAME +
                " INNER JOIN " + DatabaseInfo.ProductTable.TABLE_NAME + " on " + DatabaseInfo.ProductTable.TABLE_NAME + '.' + DatabaseInfo.ProductTable.ID + " = " + DatabaseInfo.product_category.TABLE_NAME + '.' + DatabaseInfo.product_category.p_ID +" " +
                "INNER JOIN " + DatabaseInfo.CategoryTable.TABLE_NAME + " on " + DatabaseInfo.CategoryTable.TABLE_NAME + '.' + DatabaseInfo.CategoryTable.ID + " = " + DatabaseInfo.product_category.TABLE_NAME + '.' + DatabaseInfo.product_category.c_ID +
                " where " + DatabaseInfo.ProductTable.NAME +" LIKE " + "'" +searchText + "'" +" OR "+ DatabaseInfo.CategoryTable.NAME +" LIKE " + "'" +searchText + "'";
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                productList.add(new Product(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)));
                cursor.moveToNext();
            }
            cursor.close();
        }
        }
        return productList;
    }

    public ArrayList<Product> getProductByCategory(String searchText) {
        ArrayList<Product> productList = new ArrayList<>();
        if(!searchText.isEmpty()){



            //String productId, String productImg, String productName, String productDetails, String productPrice, String productRate)
            String sql = "Select " + DatabaseInfo.ProductTable.ID + ',' + DatabaseInfo.ProductTable.img_urls + ',' + DatabaseInfo.ProductTable.NAME + ',' +
                    DatabaseInfo.ProductTable.details + ',' + DatabaseInfo.ProductTable.price + ',' + DatabaseInfo.ProductTable.Rating
                    + " from " + DatabaseInfo.product_category.TABLE_NAME +
                    " INNER JOIN " + DatabaseInfo.ProductTable.TABLE_NAME + " on " + DatabaseInfo.ProductTable.TABLE_NAME + '.' + DatabaseInfo.ProductTable.ID + " = " + DatabaseInfo.product_category.TABLE_NAME + '.' + DatabaseInfo.product_category.p_ID +" " +
                    "INNER JOIN " + DatabaseInfo.CategoryTable.TABLE_NAME + " on " + DatabaseInfo.CategoryTable.TABLE_NAME + '.' + DatabaseInfo.CategoryTable.ID + " = " + DatabaseInfo.product_category.TABLE_NAME + '.' + DatabaseInfo.product_category.c_ID +
                    " where " + DatabaseInfo.CategoryTable.NAME +" = " + "'" +searchText + "'";
            db = getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor != null) {
                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    productList.add(new Product(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5)));
                    cursor.moveToNext();
                }
                cursor.close();
            }
        }
        return productList;
    }

    public ArrayList<Product> getProductInCart(String cartId) {
        ArrayList<Product> productList = new ArrayList<>();
        //String productId, String productImg, String productName, String productDetails, String productPrice, String productRate)
        String sql = "Select " + DatabaseInfo.ProductTable.ID + ',' + DatabaseInfo.ProductTable.img_urls + ',' + DatabaseInfo.ProductTable.NAME + ',' +
                DatabaseInfo.ProductTable.details + ',' + DatabaseInfo.ProductTable.price + ',' + DatabaseInfo.ProductTable.Rating
                + " from " + DatabaseInfo.CartProduct.TABLE_NAME +
                " INNER JOIN " + DatabaseInfo.Cart.TABLE_NAME + " on " + DatabaseInfo.Cart.TABLE_NAME + '.' + DatabaseInfo.Cart.cart_id + " = " + DatabaseInfo.CartProduct.TABLE_NAME + '.' + DatabaseInfo.CartProduct.cart_id + "\n" +
                "INNER JOIN " + DatabaseInfo.ProductTable.TABLE_NAME + " on " + DatabaseInfo.ProductTable.TABLE_NAME + '.' + DatabaseInfo.ProductTable.ID + " = " + DatabaseInfo.CartProduct.TABLE_NAME + '.' + DatabaseInfo.CartProduct.product_id +
                " where " + DatabaseInfo.Cart.cart_id + " = " + "'" + cartId + "'";
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                productList.add(new Product(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return productList;
    }

    public boolean isEmailExist(String userEmail) {

        boolean exist = true;
        String sql = " select " + DatabaseInfo.Users.uEmail + " from " +
                DatabaseInfo.Users.TABLE_NAME + " where " + DatabaseInfo.Users.uEmail + " = " + "'" + userEmail + "'";
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.getCount() == 0)
                exist = false;
            cursor.close();

        }

        return exist;
    }

    public User getUserByMail(String userEmail) {
        String sql = " select " +
                DatabaseInfo.Users.U_ID + "," + DatabaseInfo.Users.UFullname + "," + DatabaseInfo.Users.ImgUri
                + "," + DatabaseInfo.Users.uEmail + "," + DatabaseInfo.Users.birthday
                + "," + DatabaseInfo.Users.address + "," + DatabaseInfo.Users.uPassword + " from " +
                DatabaseInfo.Users.TABLE_NAME + " where " + DatabaseInfo.Users.uEmail + " = " + "'" + userEmail + "'";
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() != 0)
                return new User(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                );
        }
        return null;
    }

    public void deleteProductFromCart(String cartId, String ProductId) {
        db = getWritableDatabase();
        db.delete(DatabaseInfo.CartProduct.TABLE_NAME, DatabaseInfo.CartProduct.cart_id + "='" + cartId + "'" + " AND " + DatabaseInfo.CartProduct.product_id + "='" + ProductId + "'", null);
        Log.i("itend delet", "from data");
        db.close();
    }
}
