package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ImageTextActivity extends AppCompatActivity {

    ImageButton homeButton;
    ImageButton rightArrow;
    ImageButton leftArrow;
    ImageButton naviconButton;

    TextView TextToChange;
    TextView title;

    int modifier = 0;

    String activityName;
    StringBuilder sb = new StringBuilder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_text);

        rightArrow = findViewById(R.id.rightArrow);
        leftArrow = findViewById(R.id.leftArrow);
        homeButton = findViewById(R.id.homeButton);
        naviconButton = findViewById(R.id.naviconButton);

        TextToChange = findViewById(R.id.textViewSA1);
        title = findViewById(R.id.title);



        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    menuActivity(v);
            }
        });
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            activityName = extras.getString("ActivityName");
        }

        switch (activityName) {
            case "Valmistautuminen1":
                valmistautuminenPage1();
                break;
            case "Valmistautuminen2":
                valmistautuminenPage2();
                break;
            case "Valmistautuminen3":
                valmistautuminenPage3();
                break;
            case "Synnytysvaiheet1":
                synnytysvaiheetPage1();
                break;
            case "Synnytysvaiheet2":
                synnytysvaiheetPage2();
                break;
            case "Synnytysvaiheet3":
                synnytysvaiheetPage3();
                break;
            case "Synnytysvaiheet4":
                synnytysvaiheetPage4();
                break;

        }

       /* if(extras != null)
        {
            activityName = extras.getString("ActivityName");
        }
        else
            {
            activityName = "Synnytyksen aikana1";
        }

        if(activityName.equals("Synnytyksen aikana1"))
        {
            modifier = 0;
            TextToChange.setText(Texts[0]);
        }
        else if(activityName.equals("Synnytyksen aikana2"))
        {
            modifier = 1;
            TextToChange.setText(Texts[1]);
        }
        else if(activityName.equals("Synnytyksen aikana3"))
        {
            modifier = 2;
            TextToChange.setText(Texts[2]);
        }
        else if(activityName.equals("Synnytyksen aikana4"))
        {
            modifier = 3;
            TextToChange.setText(Texts[3]);
            //TextToChange.setText(changeText("synnytyksenJalkeen1.txt"));
        }
        else if(activityName.equals("Synnytyksen aikana5"))
        {
            modifier = 4;
            TextToChange.setText(Texts[4]);
        }
        else if(activityName.equals("Synnytyksen aikana6"))
        {
            modifier = 5;
            TextToChange.setText(Texts[5]);
        }

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (modifier >= 9) {
                    activityName = "Home";
                    menuActivity(v);
                }
                else {
                    modifier++;
                    //TextToChange.setText();
                }
            }
        });

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (modifier <= 0) {
                    activityName = "Home";
                    menuActivity(v);

                }
                else {
                    modifier--;
                    //TextToChange.setText();
                }
            }
        });*/


    }
    //Funktiot joka sivulle
    private void aikanaPage1() {
        title.setText("Synnytyksen aikana");
        TextToChange.setText(changeText("SynnytyksenAikana1.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "aikana1";
                menuActivity(v);
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aikanaPage2();
            }
        });
    }

    private void aikanaPage2() {
        title.setText("Synnytyksen aikana");
        TextToChange.setText(changeText("SynnytyksenAikana2.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aikanaPage1();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aikanaPage3();
            }
        });
    }
    private void aikanaPage3() {
        title.setText("Synnytyksen aikana");
        TextToChange.setText(changeText("SynnytyksenAikana3.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aikanaPage2();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aikanaPage4();
            }
        });
    }
    private void aikanaPage4() {
        title.setText("Synnytyksen aikana");
        TextToChange.setText(changeText("SynnytyksenAikana4.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aikanaPage3();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aikanaPage5();
            }
        });
    }
    private void aikanaPage5() {
        title.setText("Synnytyksen aikana");
        TextToChange.setText(changeText("SynnytyksenAikana5.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aikanaPage4();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aikanaPage6();
            }
        });
    }
    private void aikanaPage6() {
        title.setText("Synnytyksen aikana");
        TextToChange.setText(changeText("SynnytyksenAikana2.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aikanaPage5();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aikanaPage7();
            }
        });
    }
    private void aikanaPage7() {
        title.setText("Synnytyksen aikana");
        TextToChange.setText(changeText("SynnytyksenAikana2.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               aikanaPage6();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                menuActivity(v);
            }
        });
    }


    // Lukee txt-tiedoston ja palauttaa sen Stringinä
    // Parametriksi tiedoston nimi joka halutaan avata
    private String changeText(String tiedosto) {
        // Alustetaan lukija
        BufferedReader lukija = null;
        // Tyhjennetään stringbuilder tarvittaessa
        if (sb.length() != 0)
        {
            sb.delete(0, sb.length());
        }

        try {
            // Avataan ja luetaan tiedosto
            lukija = new BufferedReader(
                    new InputStreamReader(getAssets().open(tiedosto)));
            String rivi;
            // Luetaan rivi kerrallaan ja lisätään string buillderiin
            while ((rivi = lukija.readLine()) != null) {
                sb.append(rivi);
                sb.append('\n');
            }
        }
        catch (IOException e) {
            // Lisää error viesti lukuvirheen tullessa
        }
        finally {
            if (lukija != null)
                try {
                    // Koitetaan sulkea lukija
                    lukija.close();
                }
                catch (IOException e) {
                    // joku error viesti sulkemis virheen tullessa
                }
        }
        // Muutetaan stringbuilderin tiedot stringiksi ja palautetaan
        String output = sb.toString();
        return output;
    }

    protected void menuActivity(View v){
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("ActivityName", activityName);
        startActivity(intent);
    }
}