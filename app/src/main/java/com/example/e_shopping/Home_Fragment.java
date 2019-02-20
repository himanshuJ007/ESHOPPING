package com.example.e_shopping;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_shopping.Database.Orders_Table;
import com.example.e_shopping.Database.Product_Table;

import java.util.ArrayList;

public class Home_Fragment extends Fragment {
    @Nullable

    ArrayList<Product_item> products =Product_item.GenerateProducts();
    ArrayList<Product_item>products2=new ArrayList<>();


    RecyclerView rv;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.home_fragment,null);


        try {

            Log.d("TAG HOME", "VISIBLE");

            Product_Table product_table=new Product_Table(this.getActivity());
//            product_table.open();
//            product_table.productInsert(products);
            //product_table.close();


            product_table.open();
            products2=product_table.getProduct();
            //product_table.close();


        }catch (Exception e){

            Log.d("TAG3",e.toString());

        }

        Log.d("TAG1",String.valueOf(products.size()));

        rv= (RecyclerView) view.findViewById(R.id.rvhome);
        //rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rv.setLayoutManager(new GridLayoutManager(this.getActivity(),1, LinearLayoutManager.VERTICAL,false));
        Log.d("TAG2",String.valueOf(products2.size()));

        Shopping_Items_Adapter shopping_items_adapter=new Shopping_Items_Adapter(products2,this.getActivity());

        shopping_items_adapter.notifyDataSetChanged();

        rv.setAdapter(shopping_items_adapter);



        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(
                isVisibleToUser);

        // Refresh tab data:
        Log.d("TAG HOME", "VISIBLE");

        if (getFragmentManager() != null) {

            Log.d("TAG HOME", "VISIBLE");

            getFragmentManager()
                    .beginTransaction()
                    .detach(this)
                    .attach(this)
                    .commit();
        }
    }
}
