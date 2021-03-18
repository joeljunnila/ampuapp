package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
<<<<<<< HEAD
import android.widget.ImageButton;
import android.widget.TextView;

public class TextViewActivity extends AppCompatActivity {


    ImageButton rightArrow;
    ImageButton leftArrow;
    ImageButton homeButton;
    TextView TextToChange;
    int modifier = 0;
    String[] Texts = {"Synnytystehtävän (791 A-D) tulessa huomioi seuraavat asiat:  \n" +
            "\n" +
            "Kirjaa ylös synnyttäjän perustiedot (sairaudet, allergiat, monesko raskaus ja synnytys jne.) \n" +
            "\n" +
            "Raskausviikot, selvitä miten raskaus on edennyt ja onko sikiö perä- vai raivotarjonnassa \n" +
            "\n" +
            "Kohteeseen päästyäsi tarkista mikä on synnytyksen tilanne: \n" +
            "Onko lapsivesi mennyt? Koska se on mennyt?\n" +
            "\n" +
            "Lapsiveden väri? Kirkas vai vihreä/kellertävää? Hajuton/pahanhajuista? \n" +
            "\n" +
            "Onko supistuksia? Milloin ne ovat alkaneet? Kuinka tiheään supistuksia tulee? Onko synnytys oikeasti käynnissä? ", "Hoidetaan kohteessa: \n" +
            "Supistuksia on 1-2 minuutin välein säännöllisesti \n" +
            "Synnyttäjällä on ponnistamisen pakko \n" +
            "Sikiön pää näkyy \n" +
            "  \n" +
            "Milloin matkaan: \n" +
            "Lapsivesi on mennyt, mutta ei ole supistuksia \n" +
            "Supistukset tulevat epäsäännöllisesti \n" +
            "Supistukset tulevat säännöllisesti 2-15 minuutin välein \n" +
            "Synnyttäjä otetaan kyytiin ja lähdetään liikkeelle, tutkimukset yms. voi tehdä matkalla \n" +
            "MUISTA ENNAKKOILMOITUS", "Miten toimitaan: \n" +
            "Valmistaudu ottamalla synnytyssetit esille \n" +
            "Paarien yläosa käännettään toisin päin. \n" +
            "Synnyttäjä asetetaan vasemmalle kyljelleen \n" +
            "Avaa i.v. -yhteys \n" +
            "Seuraa supistusten väliä \n" +
            "Jos synnyttäjällä tulee paineen tunne tai ponnistamisen pakko matkalla, pysäytä auto \n" +
            "Lääkkeettömiä kivunlievitys keinoja voi kokeilla kuten hierontaa tai lämpöpussia kipukohtiin"};
=======
import android.widget.TextView;

public class TextViewActivity extends AppCompatActivity {
    TextView textView;
    String activityName;

    String[] valmistautuminen = {"Synnyttäjä kokee voimakasta ponnistamisen tarvetta, kun kohdunsuu on täysin avautunut ja sikiön pää on laskeutunut synnytyskanavan alaosaan\n" +
            "Supistuksia tulee 1-2 minuutin välein ja ne kestävät yleensä minuutin ajan\n" +
            "\n", "Yleensä lapsen pää eli tarjoutuva osa painaa välilihaa, ja emättimen ulkosuu pullottaa  ja alkaa avautua jolloin lapsen pää saattaa näkyä\n" +
            "Kun synnytys on edennyt tähän vaiheeseen, niin sitä ei voi enää estää millään tavoin\n" +
            "Muista toimia rauhallisesti!\n", "Välilihan repeäminen estetään tukemalla sitä toisen kämmenellä, peukalo ja etusormi kurovat välilihaan joustoa ",
            "Samalla toisella kädellä kontrolloidaan pään syntymistä ","Kiiretilanteessa tukeminen on tärkeämpää kuin aseptiset toimenpiteet.    Pään synnyttyä,"+
            "se tekee tavallisesti käännöksen niin, että kasvot näyttävät sivulle\n","Pään synnyttyä, hartiat voi auttaa ulos ilman supistusta tai vaihtoehtoisesti synnyttäjän ponnistaessa seuraavan supistuksen aikana ",
            " Molemmilla käsillä otetaan lapsen pään sivuista kiinni ja painetaan päätä alaspäin, jolloin ylempi hartia syntyy","Seuraavaksi päätä kohotetaan ylöspäin kohti häpyliitosta alemman hartian synnyttämiseksi",
            "Kun pää ja hartiat ovat syntyneet, molempien käsien etusormet viedään lapsen selän kautta kainaloihin ja lapsen vartalo syntyy hellästi nostamalla","Jos hartiat eivät synny helposti, asetetaan jokin koroke tai tyyny tai vastaava äidin pakaroiden alle"};

>>>>>>> 38315e8871f01d4509e2f9b6fb2e2e68b865d1ff

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

<<<<<<< HEAD
        rightArrow = findViewById(R.id.rightArrow);
        leftArrow = findViewById(R.id.leftArrow);
        TextToChange = findViewById(R.id.textView);
        homeButton = findViewById(R.id.imageButton);
        TextToChange.setText(Texts[modifier]);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome(v);
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modifier == 2) {
                    returnHome(v);
                }
                modifier++;
                TextToChange.setText(Texts[modifier]);
                leftArrow.setVisibility(View.VISIBLE);
            }
        });

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modifier == 0) {
                    returnHome(v);
                }
                modifier--;
                TextToChange.setText(Texts[modifier]);
                rightArrow.setVisibility(View.VISIBLE);
            }
        });
    }
    public void returnHome(View v) {
        Intent intent = new Intent(this, MenuView.class);
=======
        textView = (TextView) findViewById(R.id.content);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            activityName = extras.getString("ActivityName");
        }

        if(activityName.equals("Valmistautuminen1")) {
            textView.setText(valmistautuminen[1]);
        }
    }

    public void returnHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("ActivityName", activityName);
>>>>>>> 38315e8871f01d4509e2f9b6fb2e2e68b865d1ff
        startActivity(intent);
    }
}