package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Valmistautuminen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valmistautuminen);
        ImageButton rightArrow = (ImageButton)findViewById(R.id.rightArrow);
        rightArrow.setVisibility(View.INVISIBLE);
    }

    public void openActivity(View view) {
        Intent intent = new Intent(this, valmActivity1.class);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }

    public void openActivity2(View view) {
        Intent intent = new Intent(this, valmActivity2.class);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }

    public void openActivity3(View view) {
        Intent intent = new Intent(this, valmActivity3.class);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }

    public void returnHome(View view) {
        Intent intent = new Intent(this, Home.class);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }
}