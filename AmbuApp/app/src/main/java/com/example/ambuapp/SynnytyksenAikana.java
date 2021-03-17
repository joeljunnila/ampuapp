package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SynnytyksenAikana extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synnytyksen_aikana);
    }
    public void openAikana1(View view) {
        Intent intent = new Intent(this, Aikana1.class);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }
}