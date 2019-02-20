package com.example.e_shopping;

import android.content.Context;
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

import java.util.ArrayList;

public class Order_Items_Adapter extends RecyclerView.Adapter<Order_Items_Adapter.CustomViewHolder> {

    ArrayList<Product_item> Orders;
    Context context;

    public Order_Items_Adapter(ArrayList<Product_item> orders,Context context) {
        Orders = orders;
        this.context=context;

    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater li= (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView=li.inflate(R.layout.order_item_card_layout,parent,false);


        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        final Product_item product_item=Orders.get(i);
        customViewHolder.Name.setText(product_item.getName());
        customViewHolder.Price.setText("Rs. "+String.valueOf(product_item.getPrice()));

        customViewHolder.Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    ArrayList<Product_item> arrayList = new ArrayList<>();

                    Orders_Table orders_table = new Orders_Table(context);


                    orders_table.open();

                    orders_table.removeFromOrder(product_item.getName());
                    Log.d("TAG PNAME",product_item.getName());
                    orders_table.close();

                    //Toast.makeText(context,"SUCCESSFULLY REMOVED",Toast.LENGTH_SHORT).show();

                    Toast.makeText(context,"ORDERS CANCELLED SUCCESSFULLY TAP ORDERS TAB AGAIN TO RELOAD",Toast.LENGTH_LONG).show();
                    //Cart.remove(i);



                }catch (Exception e){
                    Log.d("TAG ONCLICK ",e.toString());
                }
            }
        });

    }

    @Override
    public int getItemCount() {

        return Orders.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView Name ,Price ;
        Button Cancel;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        Name=itemView.findViewById(R.id.tvname);
        Price=itemView.findViewById(R.id.tvPrice);
        Cancel=itemView.findViewById(R.id.cancel);

    }
}
}
