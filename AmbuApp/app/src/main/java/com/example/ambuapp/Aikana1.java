package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Aikana1 extends AppCompatActivity {

    ImageButton rightArrow;
    ImageButton leftArrow;
    TextView TextToChange;
    int modifier = 0;
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
        TextToChange = findViewById(R.id.textViewSA1);

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifier++;
                TextToChange.setText(Texts[modifier]);
                if (modifier == 9)
                    rightArrow.setVisibility(View.INVISIBLE);
                    leftArrow.setVisibility(View.VISIBLE);
            }
        });

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifier--;
                TextToChange.setText(Texts[modifier]);
                if (modifier == 0)
                    leftArrow.setVisibility(View.INVISIBLE);
                    rightArrow.setVisibility(View.VISIBLE);

            }
        });
    }
}