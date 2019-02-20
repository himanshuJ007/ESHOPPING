package com.example.e_shopping;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.e_shopping.Database.Account_Table;
import java.util.ArrayList;

public class Account_Fragment extends Fragment {
    TextView name,email;
    Button logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        final View view= inflater.inflate(R.layout.account_fragment,null);
        logout=view.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),MainActivity.class);
                startActivity(i);

            }
        });

        Account_Table account_table = new Account_Table(this.getActivity());
        account_table.open();
        ArrayList<Account> accounts = account_table.getaccount();
        account_table.close();

        String getArgument = accounts.get(0).getEmail();
        String na = "";

       try {
            //Get pass data with its key value

            String[] arrOfStr = getArgument.split("@");

            for (String a : arrOfStr) {
                na=na+a+" ";
                //Log.d("TAG ACCOUNT for ", na);
            }

        Log.d("TAG ACCOUNT ", na);
            email = view.findViewById(R.id.tvemailA);
            email.setText(getArgument);
            name = view.findViewById(R.id.name);
            if(na.isEmpty()){
                name.setText("Name");

            }else {
                name.setText(na);
            }
        }catch (Exception e){
            Log.d("TAG ACCOUNT ",e.toString());
        }
        return view;


    }




}
