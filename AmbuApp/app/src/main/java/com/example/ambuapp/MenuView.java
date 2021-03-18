package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuView extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    ImageButton homeButton;
    ImageButton leftArrow;
    ImageButton rightArrow;
    String buttonText1;
    String buttonText2;
    String buttonText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        homeButton = (ImageButton) findViewById(R.id.imageButton);
        leftArrow = (ImageButton) findViewById(R.id.leftArrow);
        rightArrow = (ImageButton) findViewById(R.id.rightArrow);
        button1.setVisibility(View.INVISIBLE);
        button6.setVisibility(View.INVISIBLE);
        leftArrow.setVisibility(View.INVISIBLE);
        rightArrow.setVisibility(View.INVISIBLE);
        buttonText1 = "Huomioitavat asiat";
        buttonText2 = "Kohteessa vai matkaan?";
        buttonText3 = "Miten toimitaan?";
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                valmistautuminen();
            }
        });

    }

    public void valmistautuminen() {
        button2.setText(buttonText1);
        button3.setText(buttonText2);
        button4.setText(buttonText3);
        button5.setVisibility(View.INVISIBLE);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome();
            }
        });
        leftArrow.setVisibility(View.VISIBLE);
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                returnHome();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                textViewActivity(v);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                textViewActivity(v);
            }
        });
    }

    public void returnHome() {
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.INVISIBLE);

        button2.setText("Valmistautuminen");
        button3.setText("Synnytysvaiheet");
        button4.setText("Tarkistus");
        button5.setText("Erikoistilanteet");

        leftArrow.setVisibility(View.INVISIBLE);
        rightArrow.setVisibility(View.INVISIBLE);
    }

    public void textViewActivity(View v) {
        Intent intent = new Intent(this, TextViewActivity.class);
        startActivity(intent);
    }



}