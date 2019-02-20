package com.example.e_shopping;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_shopping.Database.Account_Table;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button btlogin,btsignup;
    ActionBar toolbar;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.coloTool)));
        toolbar=getSupportActionBar();
        toolbar.setTitle("LOGIN PAGE");

        setContentView(R.layout.activity_main);
            btlogin=findViewById(R.id.btlogin);
            btsignup=findViewById(R.id.btsignup);
            email=findViewById(R.id.etEmail);
            password=findViewById(R.id.etPassword);

            btlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login(email.getText().toString(),password.getText().toString(),v.getContext());
                }
            });

            btsignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i =new Intent(v.getContext(),Signup_Activity.class);
                    startActivity(i);

                }
            });




    }

    public void login(String email, String password, Context context) {
        if(email.isEmpty()||password.isEmpty()){

            Toast.makeText(context, "WRONG CREDENTIALS ", Toast.LENGTH_SHORT).show();
            return;

        }

        Account_Table account_table = new Account_Table(this);
        account_table.open();
        ArrayList<Account> accounts = account_table.getaccount();
        account_table.close();

        int i=0;
        while (i<accounts.size()){
            Log.d("TAG USER INPUT :",email+password);
            Log.d("TAG DB CONTAINS :",accounts.get(i).getEmail()+accounts.get(i).getPassword());

            if (email.equals(accounts.get(i).getEmail()) && password.equals(accounts.get(i).getPassword()) ) {


                Toast.makeText(context, "Successfully LoggedIn ", Toast.LENGTH_SHORT).show();


                Bundle data = new Bundle();//create bundle instance
                data.putString("key_email", email);//put string to pass with a key value
                Account_Fragment account_fragment=new Account_Fragment();
                account_fragment.setArguments(data);

//                FragmentManager fragmentManager=getSupportFragmentManager();
//                FragmentTransaction transaction=fragmentManager.beginTransaction();
//                transaction.add(R.id.fragment_container,account_fragment,"fragment");





                Intent in = new Intent(context, Shop_Navigate.class);
                startActivity(in);
                i=accounts.size()+1;

            }else {
                i++;
            }

        }
        if(i==accounts.size()){
        Toast.makeText(context, "WRONG CREDENTIALS ", Toast.LENGTH_SHORT).show();
        }

    }
}
