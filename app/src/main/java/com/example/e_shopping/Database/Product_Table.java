package com.example.e_shopping.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.e_shopping.Product_item;

import java.util.ArrayList;

public class Product_Table {

    protected static final String TABLE_NAME="products";

    protected static final String TABLE_ID="id";
    protected static final String PRODUCT_NAME="pname";
    protected static final String PRODUCT_PRICE="price";

    private DBHelper ourHelper;

    private final Context ourContext;

    private SQLiteDatabase ourDataBase;


    public Product_Table open(){
        ourHelper=new DBHelper(ourContext);
        ourDataBase=ourHelper.getReadableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }

    public Product_Table(Context ourContext) {
        this.ourContext = ourContext;
    }


    public void productInsert(ArrayList<Product_item> product_items){

        for (int i = 0; i <product_items.size() ; i++) {

            Product_item pi=product_items.get(i);

            String Pname =pi.getName();
            float price = pi.getPrice();

            Log.d("Pname=",Pname);

            ContentValues values = new ContentValues();
            values.put(Product_Table.PRODUCT_NAME, Pname);
            values.put(Product_Table.PRODUCT_PRICE, (int)price);
            ourDataBase.insert(Product_Table.TABLE_NAME,null,values);

        }

    }

    public ArrayList<Product_item> getProduct(){

        ArrayList<Product_item> arrayList =new ArrayList<>();
        String[] projection = {
                Product_Table.TABLE_ID,
                Product_Table.PRODUCT_NAME,
                Product_Table.PRODUCT_PRICE
        };

        Cursor cursor = ourDataBase.query(
                Product_Table.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        int iid=cursor.getColumnIndex(Product_Table.TABLE_ID);
        int iPname=cursor.getColumnIndex(Product_Table.PRODUCT_NAME);
        int iPrice=cursor.getColumnIndex(Product_Table.PRODUCT_PRICE);
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            Product_item product_item=new Product_item(cursor.getString(iPname),Float.valueOf(cursor.getString(iPrice)));
            arrayList.add(product_item);
        }

        return arrayList;
    }

    public ArrayList<Product_item> getSpecificProduct(String s){
        ArrayList<Product_item> arrayList =new ArrayList<>();
        String content=s;

        String[] projection = {
                Product_Table.TABLE_ID,
                Product_Table.PRODUCT_NAME,
                Product_Table.PRODUCT_PRICE
        };


        String selection = Product_Table.PRODUCT_NAME + " = ?";
        String[] selectionArgs = { content };


        Cursor cursor = ourDataBase.query(
                Product_Table.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );


       if(cursor!=null){
           int iid=cursor.getColumnIndex(Product_Table.TABLE_ID);
           int iPname=cursor.getColumnIndex(Product_Table.PRODUCT_NAME);
           int iPrice=cursor.getColumnIndex(Product_Table.PRODUCT_PRICE);

           cursor.moveToFirst();
            Product_item product_item=new Product_item(cursor.getString(iPname),Float.valueOf(cursor.getString(iPrice)));
            arrayList.add(product_item);
        }

        return arrayList;

    }

}
