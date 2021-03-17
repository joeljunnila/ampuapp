package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Aikana1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aikana1);
    }
    public void openAikana1(View view) {
        Intent intent = new Intent(this, Valmistautuminen.class);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }
}