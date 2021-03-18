package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SynnytyksenAikana extends AppCompatActivity {
    Button sAButton1;
    Button sAButton2;
    Button sAButton3;
    Button sAButton4;
    Button sAButton5;
    Button sAButton6;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synnytyksen_aikana);
        sAButton1 = findViewById(R.id.onButton1);
        sAButton2 = findViewById(R.id.onButton2);
        sAButton3 = findViewById(R.id.onButton3);
        sAButton4 = findViewById(R.id.onButton4);
        sAButton5 = findViewById(R.id.onButton5);
        sAButton6 = findViewById(R.id.onButton6);

    }
    public void openAikana1(View view) {
        Intent intent = new Intent(this, KuvaTekstiActivity.class);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }
}