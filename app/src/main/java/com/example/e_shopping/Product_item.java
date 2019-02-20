package com.example.e_shopping;

import java.security.PublicKey;
import java.util.ArrayList;

public class Product_item {
    String Name ;
    Float Price;

    public Product_item(String name, Float price) {
        Name = name;
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public Float getPrice() {
        return Price;
    }

    public static ArrayList<Product_item> GenerateProducts(){
        ArrayList<Product_item> product=new ArrayList<>();


            Product_item p1=new Product_item("Mobile ",10000f);
            product.add(p1);
            Product_item p2=new Product_item("Tv",50000f);
            product.add(p2);
            Product_item p3=new Product_item("HeadPhone ",2000f);
            product.add(p3);
            Product_item p4=new Product_item("Laptop",60000f);
            product.add(p4);
            Product_item p5=new Product_item("Washing Machine",9000f);
            product.add(p5);
            Product_item p6=new Product_item("Shirt",500f);
            product.add(p6);
            Product_item p7=new Product_item("Shoes",900f);
            product.add(p7);
            Product_item p8=new Product_item("Trouser",600f);
            product.add(p8);
            Product_item p9=new Product_item("Sweater",1000f);
            product.add(p9);
            Product_item p10=new Product_item("Ac",30000f);
            product.add(p10);



        return product;




    }
}
