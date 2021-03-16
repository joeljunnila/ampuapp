package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void openActivity(View view) {
        Intent intent = new Intent(this, Valmistautuminen.class);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }

    public void openActivity2(View view) {
        Intent intent = new Intent(this, SynnytyksenJalkeen.class);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }
}