package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TextViewActivity extends AppCompatActivity {
    String activityName;
    String[] content = {"Synnyttäjä kokee voimakasta ponnistamisen tarvetta, kun kohdunsuu on täysin avautunut ja sikiön pää on laskeutunut synnytyskanavan alaosaan\n" +
            "Supistuksia tulee 1-2 minuutin välein ja ne kestävät yleensä minuutin ajan\n" +
            "\n", "Yleensä lapsen pää eli tarjoutuva osa painaa välilihaa, ja emättimen ulkosuu pullottaa  ja alkaa avautua jolloin lapsen pää saattaa näkyä\n" +
            "Kun synnytys on edennyt tähän vaiheeseen, niin sitä ei voi enää estää millään tavoin\n" +
            "Muista toimia rauhallisesti!\n", "Välilihan repeäminen estetään tukemalla sitä toisen kämmenellä, peukalo ja etusormi kurovat välilihaan joustoa ",
            "Samalla toisella kädellä kontrolloidaan pään syntymistä ","Kiiretilanteessa tukeminen on tärkeämpää kuin aseptiset toimenpiteet.    Pään synnyttyä,"+
            "se tekee tavallisesti käännöksen niin, että kasvot näyttävät sivulle\n","Pään synnyttyä, hartiat voi auttaa ulos ilman supistusta tai vaihtoehtoisesti synnyttäjän ponnistaessa seuraavan supistuksen aikana ",
            " Molemmilla käsillä otetaan lapsen pään sivuista kiinni ja painetaan päätä alaspäin, jolloin ylempi hartia syntyy","Seuraavaksi päätä kohotetaan ylöspäin kohti häpyliitosta alemman hartian synnyttämiseksi",
            "Kun pää ja hartiat ovat syntyneet, molempien käsien etusormet viedään lapsen selän kautta kainaloihin ja lapsen vartalo syntyy hellästi nostamalla","Jos hartiat eivät synny helposti, asetetaan jokin koroke tai tyyny tai vastaava äidin pakaroiden alle"};

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        textView = (TextView) findViewById(R.id.content);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            activityName = extras.getString("ActivityName");
        }

        if(activityName.equals("Valmistautuminen1")) {
            content = new String[]{"Synnyttäjä kokee voimakasta ponnistamisen tarvetta, kun kohdunsuu on täysin avautunut ja sikiön pää on laskeutunut synnytyskanavan alaosaan\n" +
                    "Supistuksia tulee 1-2 minuutin välein ja ne kestävät yleensä minuutin ajan\n" +
                    "\n", "Yleensä lapsen pää eli tarjoutuva osa painaa välilihaa, ja emättimen ulkosuu pullottaa  ja alkaa avautua jolloin lapsen pää saattaa näkyä\n" +
                    "Kun synnytys on edennyt tähän vaiheeseen, niin sitä ei voi enää estää millään tavoin\n" +
                    "Muista toimia rauhallisesti!\n", "Välilihan repeäminen estetään tukemalla sitä toisen kämmenellä, peukalo ja etusormi kurovat välilihaan joustoa ",
                    "Samalla toisella kädellä kontrolloidaan pään syntymistä ", "Kiiretilanteessa tukeminen on tärkeämpää kuin aseptiset toimenpiteet.    Pään synnyttyä," +
                    "se tekee tavallisesti käännöksen niin, että kasvot näyttävät sivulle\n", "Pään synnyttyä, hartiat voi auttaa ulos ilman supistusta tai vaihtoehtoisesti synnyttäjän ponnistaessa seuraavan supistuksen aikana ",
                    " Molemmilla käsillä otetaan lapsen pään sivuista kiinni ja painetaan päätä alaspäin, jolloin ylempi hartia syntyy", "Seuraavaksi päätä kohotetaan ylöspäin kohti häpyliitosta alemman hartian synnyttämiseksi",
                    "Kun pää ja hartiat ovat syntyneet, molempien käsien etusormet viedään lapsen selän kautta kainaloihin ja lapsen vartalo syntyy hellästi nostamalla", "Jos hartiat eivät synny helposti, asetetaan jokin koroke tai tyyny tai vastaava äidin pakaroiden alle"
            };

            textView.setText(content[0]);
        }
    }

    public void returnHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}