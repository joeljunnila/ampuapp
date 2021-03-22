package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.ImageView;

public class KuvaTekstiActivity extends AppCompatActivity {

    ImageButton homeButton;
    ImageButton rightArrow;
    ImageButton leftArrow;

    TextView TextToChange;

    int modifier = 0;

    String activityName;
    String[] Texts = {"Synnyttäjä kokee voimakasta ponnistamisen tarvetta, kun kohdunsuu on täysin avautunut ja sikiön pää on laskeutunut synnytyskanavan alaosaan\n" +
            "Supistuksia tulee 1-2 minuutin välein ja ne kestävät yleensä minuutin ajan\n" +
            "\n", "Yleensä lapsen pää eli tarjoutuva osa painaa välilihaa, ja emättimen ulkosuu pullottaa  ja alkaa avautua jolloin lapsen pää saattaa näkyä\n" +
            "Kun synnytys on edennyt tähän vaiheeseen, niin sitä ei voi enää estää millään tavoin\n" +
            "Muista toimia rauhallisesti!\n", "Välilihan repeäminen estetään tukemalla sitä toisen kämmenellä, peukalo ja etusormi kurovat välilihaan joustoa ",
            "Samalla toisella kädellä kontrolloidaan pään syntymistä ","Kiiretilanteessa tukeminen on tärkeämpää kuin aseptiset toimenpiteet.    Pään synnyttyä,"+
            "se tekee tavallisesti käännöksen niin, että kasvot näyttävät sivulle\n","Pään synnyttyä, hartiat voi auttaa ulos ilman supistusta tai vaihtoehtoisesti synnyttäjän ponnistaessa seuraavan supistuksen aikana ",
    " Molemmilla käsillä otetaan lapsen pään sivuista kiinni ja painetaan päätä alaspäin, jolloin ylempi hartia syntyy","Seuraavaksi päätä kohotetaan ylöspäin kohti häpyliitosta alemman hartian synnyttämiseksi",
    "Kun pää ja hartiat ovat syntyneet, molempien käsien etusormet viedään lapsen selän kautta kainaloihin ja lapsen vartalo syntyy hellästi nostamalla","Jos hartiat eivät synny helposti, asetetaan jokin koroke tai tyyny tai vastaava äidin pakaroiden alle"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aikana1);

        rightArrow = findViewById(R.id.rightArrow);
        leftArrow = findViewById(R.id.leftArrow);
        homeButton = findViewById(R.id.homeButton);

        TextToChange = findViewById(R.id.textViewSA1);

        //TextToChange.setText(Texts[0]);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    takeMeHome(v);
            }
        });
        Bundle extras = getIntent().getExtras();
        if(extras != null)
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
                    takeMeHome(v);
                }
                else {
                    modifier++;
                    TextToChange.setText(Texts[modifier]);
                }
            }
        });

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (modifier <= 0) {
                    activityName = "Home";
                    takeMeHome(v);

                }
                else {
                    modifier--;
                    TextToChange.setText(Texts[modifier]);
                }
            }
        });


    }
    public void takeMeHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("ActivityName", activityName);
        startActivity(intent);
    }
}