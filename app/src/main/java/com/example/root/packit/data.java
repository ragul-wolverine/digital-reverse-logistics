package com.example.root.packit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class data extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(data.this, Final.class);
                startActivity(intent);
            }
        });

        ArrayAdapter<CharSequence> adapterAge;
        ArrayAdapter<CharSequence> adapterSex;

        String[] CatArr = {"Beauty & Personal care", "Food & Refreshment", "Home Care", "Water purifier"};
        String[] BradArr = {"Axe", "Breeze", "Citra"};

        Spinner CatDrp =(Spinner)findViewById(R.id.simpleSpinner);
        Spinner BradDrp    =(Spinner)findViewById(R.id.simpleSpinner2);

        adapterAge =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,CatArr);
        adapterAge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CatDrp.setAdapter(adapterAge);

        adapterSex=     new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,BradArr);
        adapterSex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BradDrp.setAdapter(adapterSex);

        String selectedAge  = CatDrp.getSelectedItem().toString();
        String selectedSex  = BradDrp.getSelectedItem().toString();
        System.out.println(selectedAge+" "+selectedSex);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


}
