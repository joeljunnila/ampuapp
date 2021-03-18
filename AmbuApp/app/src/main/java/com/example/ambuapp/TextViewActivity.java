package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class TextViewActivity extends AppCompatActivity {
    TextView textView;
    String activityName;
    ImageButton rightArrow;
    ImageButton leftArrow;
    ImageButton homeButton;

    int modifier = 0;
    String[] valmistautuminen = {"Synnytystehtävän (791 A-D) tulessa huomioi seuraavat asiat:  \n" +
            "Kirjaa ylös synnyttäjän perustiedot (sairaudet, allergiat, monesko raskaus ja synnytys jne.) \n" +
            "\n" +
            "Raskausviikot, selvitä miten raskaus on edennyt ja onko sikiö perä- vai raivotarjonnassa \n" +
            "\n" +
            "Kohteeseen päästyäsi tarkista mikä on synnytyksen tilanne: \n" +
            "Onko lapsivesi mennyt? Koska se on mennyt?\n" +
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        rightArrow = findViewById(R.id.rightArrow);
        leftArrow = findViewById(R.id.leftArrow);
        homeButton = findViewById(R.id.homeButton);
        textView = (TextView) findViewById(R.id.content);
        textView.setText(valmistautuminen[modifier]);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome(v);
            }
        });

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modifier <= 0) {
                    activityName = "Valmistautuminen";
                    returnHome(v);
                }
                else {
                    modifier--;
                    textView.setText(valmistautuminen[modifier]);
                    rightArrow.setVisibility(View.VISIBLE);
                }


            }
        });
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modifier >= 2) {
                    activityName = "Home";
                    returnHome(v);
                }
                else {
                    modifier++;
                    textView.setText(valmistautuminen[modifier]);
                    leftArrow.setVisibility(View.VISIBLE);
                }
            }
        });
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            activityName = extras.getString("ActivityName");
        }

        if(activityName.equals("Valmistautuminen1")) {
            modifier = 0;
            textView.setText(valmistautuminen[0]);
        }
        else if(activityName.equals("Valmistautuminen2")) {
            modifier = 1;
            textView.setText(valmistautuminen[1]);
        }
        else if(activityName.equals("Valmistautuminen3"))
        {
            modifier = 2;
            textView.setText(valmistautuminen[2]);
        }
    }

    public void returnHome(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("ActivityName", activityName);
        startActivity(intent);
    }
}