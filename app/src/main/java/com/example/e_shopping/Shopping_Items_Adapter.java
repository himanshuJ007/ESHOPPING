package com.example.e_shopping;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import javax.xml.transform.Templates;

public class Shopping_Items_Adapter extends RecyclerView.Adapter<Shopping_Items_Adapter.CustomViewHolder> {

    ArrayList<Product_item> Products;
    Context context;
   // Product_Table product_table;


    public Shopping_Items_Adapter(ArrayList<Product_item> products,Context context) {

        Products = products;
        this.context=context;
       // this.product_table=product_table;

    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater li= (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView=li.inflate(R.layout.shopping_item_card_layout,parent,false);


        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {

        final Product_item product_item=Products.get(i);

        customViewHolder.Name.setText(product_item.getName());
        customViewHolder.Price.setText("Rs. "+String.valueOf(product_item.getPrice()));

        customViewHolder.OrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    ArrayList<Product_item> arrayList = new ArrayList<>();

                    Product_Table product_table = new Product_Table(context);


                    product_table.open();
                    arrayList=(product_table.getSpecificProduct(product_item.getName()));
                    Log.d("TAG PNAME",product_item.getName());
                    product_table.close();



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

        customViewHolder.AddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ArrayList<Product_item> arrayList = new ArrayList<>();

                    Product_Table product_table = new Product_Table(context);


                    product_table.open();
                    arrayList=(product_table.getSpecificProduct(product_item.getName()));
                    Log.d("TAG PNAME",product_item.getName());
                    product_table.close();



                    Cart_Table cart_table =new Cart_Table(context);
                    cart_table.open();
                    cart_table.cartInsert(arrayList);
                    cart_table.close();
                    Toast.makeText(context,"ADDED TO CART",Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Log.d("TAG ONCLICK ",e.toString());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return Products.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView Name ,Price ;
        Button OrderNow,AddToCart;


    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        Name=itemView.findViewById(R.id.tvname);
        Price=itemView.findViewById(R.id.tvPrice);
        OrderNow=itemView.findViewById(R.id.ordernow);
        AddToCart=itemView.findViewById(R.id.addtocart);


    }
}
}
