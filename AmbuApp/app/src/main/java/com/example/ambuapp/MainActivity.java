package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String activityName = "Home";

    MyFirebase myFirebase;
    Thread myFirebaseThread;

    ImageButton homeButton;
    TextView title;
    ImageButton naviconButton;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;

    ImageButton leftArrow;
    ImageButton rightArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        homeButton = findViewById(R.id.homeButton);
        title = findViewById(R.id.title);
        naviconButton = findViewById(R.id.naviconButton);

        naviconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings();
            }
        });

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);

        leftArrow = findViewById(R.id.leftArrow);
        rightArrow = findViewById(R.id.rightArrow);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            activityName = extras.getString("ActivityName");
        }

        if (activityName.equals("Home")) {
            homePage();
        } else if (activityName.equals("Valmistautuminen")) {
            valmistautuminenPage();
        }

    }

    public void homePage() {
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // disable home button in home page
            }
        });

        title.setText("AmbuApp");
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

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valmistautuminenPage();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aikanaActivity(v);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               tarkistusActivity(v);
           }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                erikoistilanteetPage();
            }
        });
    }

    public void settings() {
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.INVISIBLE);
        button5.setVisibility(View.INVISIBLE);
        button6.setVisibility(View.INVISIBLE);

        button3.setText("UPDATE");

        leftArrow.setVisibility(View.INVISIBLE);
        rightArrow.setVisibility(View.INVISIBLE);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFirebase = new MyFirebase();
                myFirebaseThread = new Thread(myFirebase);
                myFirebaseThread.start();
            }
        });
    }

    public void valmistautuminenPage() {

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePage();
            }
        });
        title.setText("Valmistautuminen");
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setText("Valmistautuminen");
        button4.setText("Kohteessa vai matkaan?");
        button5.setText("Miten toimitaan");
        button6.setVisibility(View.INVISIBLE);
        leftArrow.setVisibility(View.VISIBLE);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePage();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Valmistautuminen1";
                textViewActivity(v);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Valmistautuminen2";
                textViewActivity(v);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Valmistautuminen3";
                textViewActivity(v);
            }
        });

    }

    public void erikoistilanteetPage() {
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePage();
            }
        });
        title.setText("Erikoistilanteet");

        button2.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setText("Perätila");
        button4.setText("Hartiadystokia");
        button5.setText("Napanuoran esiinluiskahdus");
        leftArrow.setVisibility(View.VISIBLE);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePage();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "KuvaTekstiActivity";
                textViewActivity(v);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Valmistautuminen1";
                textViewActivity(v);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Valmistautuminen1";
                textViewActivity(v);
            }
        });

    }

    public void KuvaTekstiActivity(View view){
        Intent intent = new Intent(this, KuvaTekstiActivity.class);
        intent.putExtra("ActivityName", activityName);
        startActivity(intent);

    }

    public void textViewActivity(View view) {
        Intent intent = new Intent(this, TextViewActivity.class);
        intent.putExtra("ActivityName", activityName);
        startActivity(intent);
    }

    public void aikanaActivity(View view){
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePage();
            }
        });

        title.setText("Synnytyksen aikana");
        button1.setVisibility(View.VISIBLE);
        button6.setVisibility(View.VISIBLE);
        button1.setText("Synnytyksen aikana 1");
        button2.setText("Synnytyksen aikana 2");
        button3.setText("Synnytyksen aikana 3");
        button4.setText("Synnytyksen aikana 4");
        button5.setText("Synnytyksen aikana 5");
        button6.setText("Synnytyksen aikana 6");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KuvaTekstiActivity(v);
            }
        });
        /*leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePage();
            }
        });
        */
    }

    public void tarkistusActivity(View view){
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { homePage();; }
        });

        title.setText("Tarkistus");
        button1.setVisibility(View.VISIBLE);
        button6.setVisibility(View.VISIBLE);
        button1.setText("Synnytyksen jälkeen 1");
        button2.setText("Synnyttyksen jälkeen 2");
        button3.setText("Synnytyksen jälkeen 3");
        button4.setText("Synnytyksen jälkeen 4");
        button5.setText("Synnytyksen jälkeen 5");
        button6.setText("Synnytyksen jälkeen 6");

        button1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "SynnytyksenJalkeen1";
                textViewActivity(v);
            }
        });
    }
}