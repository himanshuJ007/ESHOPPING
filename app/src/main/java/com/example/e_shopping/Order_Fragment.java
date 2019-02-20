package com.example.e_shopping;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e_shopping.Database.Orders_Table;
import com.example.e_shopping.Database.Product_Table;

import java.util.ArrayList;

public class Order_Fragment extends Fragment {


    RecyclerView rv;
    TextView tv;
    ImageView im;

    ArrayList<Product_item> order=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.orders_fragment,null);
        tv=view.findViewById(R.id.tvorder);
        im=view.findViewById(R.id.imorder);

        try {

            Orders_Table orders_table=new Orders_Table(this.getActivity());


            orders_table.open();
            order=orders_table.getorder();
            orders_table.close();


        }catch (Exception e){

            Log.d("ORDER TAG3",e.toString());

        }

        rv= (RecyclerView) view.findViewById(R.id.rvorder);


        rv.setLayoutManager(new GridLayoutManager(this.getActivity(),1, LinearLayoutManager.VERTICAL,false));

        Log.d("ORDER TAG2",String.valueOf(order.size()));


        Order_Items_Adapter order_items_adapter=new Order_Items_Adapter(order,this.getActivity());
        if(order.size()==0){
            im.setVisibility(View.VISIBLE);
            tv.setVisibility(View.VISIBLE);

        }else {
            im.setVisibility(View.INVISIBLE);
            tv.setVisibility(View.INVISIBLE);
        }


        order_items_adapter.notifyDataSetChanged();

        rv.setAdapter(order_items_adapter);
        return view;
    }
}
