package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Home extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void openActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }

    public void openActivity2(View view) {
        Intent intent = new Intent(this, TextView.class);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }
    public void openActivity3(View view) {
        Intent intent = new Intent(this, SynnytyksenAikana.class);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }
}