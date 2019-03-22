package com.example.root.packit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class data extends AppCompatActivity  {



    EditText serial,quantity,product,date,username,cat,brand;
    Button btn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        cat = (EditText) findViewById(R.id.editText6);
        brand = (EditText)  findViewById(R.id.editText7);
        serial = (EditText) findViewById(R.id.editText2);
        quantity = (EditText) findViewById(R.id.editText4);
        product = (EditText) findViewById(R.id.editText);
        date = (EditText) findViewById(R.id.editText5);
        username = (EditText) findViewById(R.id.editText3);


    }




    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    public void registerBtn(View view) {
        String category = cat.getText().toString();
        String  brands= brand.getText().toString();
        String serialnum = serial.getText().toString();
        String quan = quantity.getText().toString();
        String pro = product.getText().toString();
        String deliver = date.getText().toString();
        String user = username.getText().toString();

        bg bgm;
        bgm = new bg(this);
        bgm.execute(category,brands,serialnum,quan,pro,deliver,user);
    }

}

