package com.example.e_shopping;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_shopping.Database.Cart_Table;
import com.example.e_shopping.Database.Orders_Table;
import com.example.e_shopping.Database.Product_Table;

import java.util.ArrayList;

public class Cart_Items_Adapter extends RecyclerView.Adapter<Cart_Items_Adapter.CustomViewHolder> {

    ArrayList<Product_item> Cart;
    Context context;

    public Cart_Items_Adapter(ArrayList<Product_item> cart,Context context) {
        Cart = cart;
        this.context=context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater li= (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView=li.inflate(R.layout.cart_item_card_layout,parent,false);


        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, final int i) {
        final Product_item product_item=Cart.get(i);
        customViewHolder.Name.setText(product_item.getName());
        customViewHolder.Price.setText("Rs. "+String.valueOf(product_item.getPrice()));

        customViewHolder.OrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    ArrayList<Product_item> arrayList = new ArrayList<>();

                    Cart_Table cart_table = new Cart_Table(context);


                    cart_table.open();
                    arrayList=(cart_table.getSpecificCart(product_item.getName()));

                    cart_table.removeFromCart(product_item.getName());
                    //Cart.remove(i);

                    Log.d("TAG PNAME",product_item.getName());
                    cart_table.close();



                    Orders_Table orders_table=new Orders_Table(context);
                    orders_table.open();
                    orders_table.orderInsert(arrayList);
                    orders_table.close();

                    Toast.makeText(context,"SUCCESSFULLY ORDERED",Toast.LENGTH_SHORT).show();



                }catch (Exception e){
                    Log.d("TAG ONCLICK ",e.toString());
                }


            }
        });

        customViewHolder.Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    ArrayList<Product_item> arrayList = new ArrayList<>();

                    Cart_Table cart_table = new Cart_Table(context);


                    cart_table.open();

                    cart_table.removeFromCart(product_item.getName());
                    Log.d("TAG PNAME",product_item.getName());
                    cart_table.close();

                    //Toast.makeText(context,"SUCCESSFULLY REMOVED",Toast.LENGTH_SHORT).show();

                    Toast.makeText(context,"SUCCESSFULLY REMOVED TAP ON THE CART AGAIN TO RELOAD",Toast.LENGTH_LONG).show();
                    //Cart.remove(i);



                }catch (Exception e){
                    Log.d("TAG ONCLICK ",e.toString());
                }
            }
        });


    }

    @Override
    public int getItemCount() {

        return Cart.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView Name ,Price ;
        Button OrderNow,Remove;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        Name=itemView.findViewById(R.id.tvname);
        Price=itemView.findViewById(R.id.tvPrice);

        OrderNow=itemView.findViewById(R.id.ordernow);
        Remove=itemView.findViewById(R.id.remove);

    }
}
}
