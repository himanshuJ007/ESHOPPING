package com.example.e_shopping.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.e_shopping.Account;
import com.example.e_shopping.Product_item;

import java.util.ArrayList;

public class Account_Table {

    protected static final String TABLE_NAME="account";

    protected static final String TABLE_ID="id";
    protected static final String ACCOUNT_EMAIL="email";
    protected static final String ACCOUNT_PASSWORD="password";

    private DBHelper ourHelper;

    private final Context ourContext;

    private SQLiteDatabase ourDataBase;


    public Account_Table open(){
        ourHelper=new DBHelper(ourContext);
        ourDataBase=ourHelper.getReadableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }

    public Account_Table(Context ourContext) {
        this.ourContext = ourContext;
    }


    public void accountInsert(ArrayList<Account> accounts){

        for (int i = 0; i <accounts.size() ; i++) {

            Account pi=accounts.get(i);

            String email =pi.getEmail();
            String password = pi.getPassword();

            ContentValues values = new ContentValues();
            values.put(Account_Table.ACCOUNT_EMAIL, email);
            values.put(Account_Table.ACCOUNT_PASSWORD, password);
            ourDataBase.insert(Account_Table.TABLE_NAME,null,values);

        }






    }

    public ArrayList<Account> getaccount(){

        ArrayList<Account> arrayList =new ArrayList<>();
        String[] projection = {
                Account_Table.TABLE_ID,
                Account_Table.ACCOUNT_EMAIL,
                Account_Table.ACCOUNT_PASSWORD
        };

        Cursor cursor = ourDataBase.query(
                Account_Table.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        int iid=cursor.getColumnIndex(Account_Table.TABLE_ID);
        int iPname=cursor.getColumnIndex(Account_Table.ACCOUNT_EMAIL);
        int iPrice=cursor.getColumnIndex(Account_Table.ACCOUNT_PASSWORD);
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            Account account=new Account(cursor.getString(iPname),cursor.getString(iPrice));
            arrayList.add(account);
        }

        return arrayList;



    }
//    public ArrayList<Product_item> getSpecificCart(String s){
//        ArrayList<Product_item> arrayList =new ArrayList<>();
//        String content=s;
//
//        String[] projection = {
//                Account_Table.TABLE_ID,
//                Account_Table.PRODUCT_NAME,
//                Account_Table.PRODUCT_PRICE
//        };
//
//
//        String selection = Product_Table.PRODUCT_NAME + " = ?";
//        String[] selectionArgs = { content };
//
//
//        Cursor cursor = ourDataBase.query(
//                Account_Table.TABLE_NAME,   // The table to query
//                projection,             // The array of columns to return (pass null to get all)
//                selection,              // The columns for the WHERE clause
//                selectionArgs,          // The values for the WHERE clause
//                null,                   // don't group the rows
//                null,                   // don't filter by row groups
//                null               // The sort order
//        );
//
//
//        if(cursor!=null){
//            int iid=cursor.getColumnIndex(Account_Table.TABLE_ID);
//            int iPname=cursor.getColumnIndex(Account_Table.PRODUCT_NAME);
//            int iPrice=cursor.getColumnIndex(Account_Table.PRODUCT_PRICE);
//
//            cursor.moveToFirst();
//            Product_item product_item=new Product_item(cursor.getString(iPname),Float.valueOf(cursor.getString(iPrice)));
//            arrayList.add(product_item);
//        }
//
//        return arrayList;
//
//    }

//    public  void  removeFromCart(String s){
//
//
//        String content=String.valueOf(s);
//
//        String selection = Product_Table.PRODUCT_NAME + " = ?";
//        String [] selectionArgs = { content };
//
//        int deletedRows = ourDataBase.delete(Account_Table.TABLE_NAME, selection, selectionArgs);
//
//    }
}
