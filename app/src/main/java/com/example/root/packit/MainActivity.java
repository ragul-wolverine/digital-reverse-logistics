package com.example.root.packit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText usr,pas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usr = (EditText) findViewById(R.id.username);
        pas = (EditText) findViewById(R.id.password);

    }


    public void loginBtn(View view){
        String username = usr.getText().toString();
        String password = pas.getText().toString();

        background bg;
        bg = new background(this);
        bg.execute(username,password);
    }
}