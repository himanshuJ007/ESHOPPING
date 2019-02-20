package com.example.e_shopping;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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
import com.example.e_shopping.Database.Cart_Table;
import java.util.ArrayList;

public class Cart_Fragment extends Fragment {

    RecyclerView rv;

    ArrayList<Product_item> cart=new ArrayList<>();
    TextView tv;
    ImageView im;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view= inflater.inflate(R.layout.cart_fragment,null);
        tv=view.findViewById(R.id.tvcart);
        im=view.findViewById(R.id.imcart);
        try {

            Cart_Table cart_table=new Cart_Table(this.getActivity());
            cart_table.open();
            cart=cart_table.getcart();
            cart_table.close();


        }catch (Exception e){

            Log.d("Cart TAG3",e.toString());

        }
        rv= (RecyclerView) view.findViewById(R.id.rvcart);


        rv.setLayoutManager(new GridLayoutManager(this.getActivity(),1, LinearLayoutManager.VERTICAL,false));

        Log.d("Cart TAG2",String.valueOf(cart.size()));


        Cart_Items_Adapter cart_items_adapter=new Cart_Items_Adapter(cart,this.getActivity());

        if(cart.size()==0){
            im.setVisibility(View.VISIBLE);
            tv.setVisibility(View.VISIBLE);


        }else {
            im.setVisibility(View.INVISIBLE);
            tv.setVisibility(View.INVISIBLE);

        }

        cart_items_adapter.notifyDataSetChanged();

        rv.setAdapter(cart_items_adapter);

        return view;


    }


}
