package com.example.e_shopping.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.e_shopping.Product_item;

import java.util.ArrayList;

public class Cart_Table {

    protected static final String TABLE_NAME="cart";

    protected static final String TABLE_ID="id";
    protected static final String PRODUCT_NAME="pname";
    protected static final String PRODUCT_PRICE="price";

    private DBHelper ourHelper;

    private final Context ourContext;

    private SQLiteDatabase ourDataBase;


    public Cart_Table open(){
        ourHelper=new DBHelper(ourContext);
        ourDataBase=ourHelper.getReadableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }

    public Cart_Table(Context ourContext) {
        this.ourContext = ourContext;
    }


    public void cartInsert(ArrayList<Product_item> product_items){

        for (int i = 0; i <product_items.size() ; i++) {

            Product_item pi=product_items.get(i);

            String Pname =pi.getName();
            float price = pi.getPrice();

            ContentValues values = new ContentValues();
            values.put(Cart_Table.PRODUCT_NAME, Pname);
            values.put(Cart_Table.PRODUCT_PRICE, (int)price);
            ourDataBase.insert(Cart_Table.TABLE_NAME,null,values);

        }






    }

    public ArrayList<Product_item> getcart(){

        ArrayList<Product_item> arrayList =new ArrayList<>();
        String[] projection = {
                Cart_Table.TABLE_ID,
                Cart_Table.PRODUCT_NAME,
                Cart_Table.PRODUCT_PRICE
        };

        Cursor cursor = ourDataBase.query(
                Cart_Table.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        int iid=cursor.getColumnIndex(Cart_Table.TABLE_ID);
        int iPname=cursor.getColumnIndex(Cart_Table.PRODUCT_NAME);
        int iPrice=cursor.getColumnIndex(Cart_Table.PRODUCT_PRICE);
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            Product_item product_item=new Product_item(cursor.getString(iPname),Float.valueOf(cursor.getString(iPrice)));
            arrayList.add(product_item);
        }

        return arrayList;



    }
    public ArrayList<Product_item> getSpecificCart(String s){
        ArrayList<Product_item> arrayList =new ArrayList<>();
        String content=s;

        String[] projection = {
                Cart_Table.TABLE_ID,
                Cart_Table.PRODUCT_NAME,
                Cart_Table.PRODUCT_PRICE
        };


        String selection = Product_Table.PRODUCT_NAME + " = ?";
        String[] selectionArgs = { content };


        Cursor cursor = ourDataBase.query(
                Cart_Table.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );


        if(cursor!=null){
            int iid=cursor.getColumnIndex(Cart_Table.TABLE_ID);
            int iPname=cursor.getColumnIndex(Cart_Table.PRODUCT_NAME);
            int iPrice=cursor.getColumnIndex(Cart_Table.PRODUCT_PRICE);

            cursor.moveToFirst();
            Product_item product_item=new Product_item(cursor.getString(iPname),Float.valueOf(cursor.getString(iPrice)));
            arrayList.add(product_item);
        }

        return arrayList;

    }

    public  void  removeFromCart(String s){


        String content=String.valueOf(s);

        String selection = Cart_Table.PRODUCT_NAME + " = ?";
        String [] selectionArgs = { content };

        int deletedRows = ourDataBase.delete(Cart_Table.TABLE_NAME, selection, selectionArgs);

    }
}
