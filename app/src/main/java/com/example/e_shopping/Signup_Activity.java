package com.example.e_shopping;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_shopping.Database.Account_Table;

import java.util.ArrayList;

public class Signup_Activity extends AppCompatActivity {
    EditText email,password;
    Button btlogin,btsignup;
    ActionBar toolbar;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.coloTool)));
        toolbar=getSupportActionBar();
        toolbar.setTitle("SIGNUP PAGE");

        setContentView(R.layout.activity_signup);
            btlogin=findViewById(R.id.btlogin);
            btsignup=findViewById(R.id.btsignup);
            email=findViewById(R.id.etEmail);
            password=findViewById(R.id.etPassword);
            btsignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signup(email.getText().toString(),password.getText().toString(),v.getContext());

                }
            });
            btlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i =new Intent(Signup_Activity.this,MainActivity.class);
                    startActivity(i);
                }
            });
        }


    public void signup(String email, String password, Context context){
        if(email.isEmpty()||password.isEmpty()){

            Toast.makeText(context, "INVALID CREDENTIALS ", Toast.LENGTH_SHORT).show();
            return;

        }

        Account_Table account_table=new Account_Table(this);
        account_table.open();
        Account account=new Account(email,password);

        ArrayList<Account> accounts= new ArrayList<>();
        accounts.add(account);
        account_table.accountInsert(accounts);
        account_table.close();

        Intent i =new Intent(context,MainActivity.class);
        startActivity(i);

        Toast.makeText(context, "Successfully SignedUP ", Toast.LENGTH_SHORT).show();


        //login=true;


    }
}
