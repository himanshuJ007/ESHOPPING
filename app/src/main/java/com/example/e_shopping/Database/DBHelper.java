package com.example.e_shopping.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.e_shopping.Account;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shopping.db";
    private static final int DATABASE_vERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Product_Table.TABLE_NAME + " (" +
                    Product_Table.TABLE_ID + " INTEGER PRIMARY KEY," +
                    Product_Table.PRODUCT_NAME + " TEXT," +
                    Product_Table.PRODUCT_PRICE + " INTEGER)";

    private static final String SQL_CREATE_ENTRIES_ORDER =
            "CREATE TABLE " + Orders_Table.TABLE_NAME + " (" +
                    Orders_Table.TABLE_ID + " INTEGER PRIMARY KEY," +
                    Orders_Table.PRODUCT_NAME + " TEXT," +
                    Orders_Table.PRODUCT_PRICE + " INTEGER)";

    private static final String SQL_CREATE_ENTRIES_Cart =
            "CREATE TABLE " + Cart_Table.TABLE_NAME + " (" +
                    Cart_Table.TABLE_ID + " INTEGER PRIMARY KEY," +
                    Cart_Table.PRODUCT_NAME + " TEXT," +
                    Cart_Table.PRODUCT_PRICE + " INTEGER)";

    private static final String SQL_CREATE_ENTRIES_Account =
            "CREATE TABLE " + Account_Table.TABLE_NAME + " (" +
                    Account_Table.TABLE_ID + " INTEGER PRIMARY KEY," +
                    Account_Table.ACCOUNT_EMAIL + " TEXT," +
                    Account_Table.ACCOUNT_PASSWORD + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Product_Table.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_ORDER =
            "DROP TABLE IF EXISTS " + Orders_Table.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_Cart =
            "DROP TABLE IF EXISTS " + Cart_Table.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_Account =
            "DROP TABLE IF EXISTS " + Account_Table.TABLE_NAME;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_vERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES_ORDER);
        db.execSQL(SQL_CREATE_ENTRIES_Cart);
        db.execSQL(SQL_CREATE_ENTRIES_Account);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES_ORDER);
        db.execSQL(SQL_DELETE_ENTRIES_Cart);
        db.execSQL(SQL_DELETE_ENTRIES_Account);
        onCreate(db);
    }
}