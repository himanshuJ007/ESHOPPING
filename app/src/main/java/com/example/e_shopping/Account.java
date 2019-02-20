package com.example.e_shopping;

import android.widget.EditText;

import java.util.ArrayList;

public class Account {
    String Email ;
    String Password;

    public Account(String email, String password) {
        Email=email;
        Password=password;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public static ArrayList<Account> GenerateAccount(String email,String password){
        ArrayList<Account> product=new ArrayList<>();
        Account account=new Account(email,password);
        product.add(account);

        return product;

    }
}
