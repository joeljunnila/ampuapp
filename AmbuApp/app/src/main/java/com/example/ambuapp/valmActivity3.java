package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class valmActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valm3);
        ImageButton leftArrow = (ImageButton)findViewById(R.id.leftArrow);
        leftArrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                returnPage(view);
            }
        });
        ImageButton rightArrow = (ImageButton)findViewById(R.id.rightArrow);
        rightArrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                openActivity(view);
            }
        });
    }
    public void openActivity(View view) {
        Intent intent = new Intent(this, Home.class);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }

    public void returnPage(View view) {
        Intent intent = new Intent(this, valmActivity2.class);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }
}