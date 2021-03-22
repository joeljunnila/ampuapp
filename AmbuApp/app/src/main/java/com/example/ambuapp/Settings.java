package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class Settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ImageButton homeButton;
    TextView title;
    ImageButton naviconButton;
    TextView textView;
    TextView textView2;
    ImageButton leftArrow;
    ImageButton rightArrow;
    String activityName;
    String[] items = new String[]{"Pieni", "Keskisuuri", "Suuri"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        homeButton = findViewById(R.id.homeButton);
        title = findViewById(R.id.title);
        naviconButton = findViewById(R.id.naviconButton);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        leftArrow = findViewById(R.id.leftArrow);
        rightArrow = findViewById(R.id.rightArrow);
        rightArrow.setVisibility(View.INVISIBLE);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Home";
                menuActivity(v);
            }
        });

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Home";
                menuActivity(v);
            }
        });
        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner);
        //create a list of items for the spinner.
        //create an adapter to describe how the items are displayed
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);


    }
    public void menuActivity(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("ActivityName", activityName);
        startActivity(intent);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                textView2.setTextSize(15);
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                textView2.setTextSize(20);
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                textView2.setTextSize(40);
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

}
