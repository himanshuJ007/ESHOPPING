package com.example.e_shopping;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.e_shopping.Database.Product_Table;

import java.util.ArrayList;

public class Shop_Navigate extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    ActionBar toolbar;
    ArrayList<Product_item> products =Product_item.GenerateProducts();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop__navigate);

        toolbar = getSupportActionBar();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.coloTool)));



        Product_Table product_table=new Product_Table(this);
        product_table.open();
        product_table.productInsert(products);

        loadFragment(new Home_Fragment());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;



        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new Home_Fragment();
                toolbar.setTitle("HOME");

                break;

            case R.id.navigation_orders:
                fragment = new Order_Fragment();
                toolbar.setTitle("ORDERS");
                break;


            case R.id.navigation_cart:
                fragment = new Cart_Fragment();
                toolbar.setTitle("CART");
                break;

            case R.id.navigation_account:
                fragment = new Account_Fragment();
                toolbar.setTitle("ACCOUNT");
                break;
        }



        return loadFragment(fragment);


    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }



}
