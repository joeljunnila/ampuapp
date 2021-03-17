package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageButton;

public class Aikana1 extends AppCompatActivity {

    ImageButton rightArrow;
    TextView TextToChange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aikana1);

        rightArrow = findViewById(R.id.rightArrow);
        TextToChange = findViewById(R.id.textViewSA1);
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextToChange.setText("jees");
            }
        });




    }
    public void textChange()
    {


    }


}