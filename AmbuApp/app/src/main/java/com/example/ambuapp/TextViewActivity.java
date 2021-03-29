package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;


import com.shuhart.stepview.StepView;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;


public class TextViewActivity extends AppCompatActivity {
    ImageButton homeButton;
    TextView title;
    TextView textView;
    ImageButton rightArrow;
    ImageButton leftArrow;
    StepView stepView;

    String activityName;
<<<<<<< HEAD
    StringBuilder sb = new StringBuilder();

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
=======
    String previousActivityName;

    public static Integer textSize = 20;
>>>>>>> origin/Teemu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        homeButton = findViewById(R.id.homeButton);
        title = findViewById(R.id.title);
        textView = findViewById(R.id.content);
        //textView.setTextSize(textSize);
        rightArrow = findViewById(R.id.rightArrow);
        leftArrow = findViewById(R.id.leftArrow);
        stepView = findViewById(R.id.stepView);

        textView.setText(valmistautuminen[modifier]);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Home";
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
            case "Tarkistus1":
                tarkistusPage1();
                break;
            case "Tarkistus2":
                tarkistusPage2();
                break;
            case "Tarkistus3":
                tarkistusPage3();
                break;
            case "Tarkistus5":
                tarkistusPage5();
                break;
            case "Erikoistilanteet1":
                erikoistilanteetPage1();
                break;
            case "Erikoistilanteet2":
                erikoistilanteetPage2();
                break;
            case "Erikoistilanteet3":
                erikoistilanteetPage3();
                break;
        }

        /*leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modifier <= 0) {
                    activityName = "Valmistautuminen";
                    menuActivity(v);
                }
                else {
                    modifier--;
                    textView.setText(valmistautuminen[modifier]);
                    rightArrow.setVisibility(View.VISIBLE);
                }
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modifier >= 2) {
                    activityName = "Home";
                    menuActivity(v);
                }
                else {
                    modifier++;
                    textView.setText(valmistautuminen[modifier]);
                    leftArrow.setVisibility(View.VISIBLE);
                }
            }
        });
<<<<<<< HEAD
        switch (activityName) {
            case "Valmistautuminen1":
                modifier = 0;
                textView.setText(valmistautuminen[0]);
                break;
            case "Valmistautuminen2":
                modifier = 1;
                textView.setText(valmistautuminen[1]);
                break;
            case "Valmistautuminen3":
                modifier = 2;
                textView.setText(valmistautuminen[2]);
                break;
            case "SynnytyksenJalkeen1":
                textView.setText(changeText("synnytyksenJalkeen1.txt"));
                break;
        }*/
    }

=======
    }

//haetaan materiaalit ja activityName valitun sivun mukaan
>>>>>>> origin/Teemu
    private void valmistautuminenPage1() {
        title.setText("valmistautuminen");
        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.VISIBLE);

        stepView.getState().stepsNumber(3).commit();
        stepView.go(0, false);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Valmistautuminen";
                menuActivity(v);
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valmistautuminenPage2();
            }
        });
    }

    private void valmistautuminenPage2() {
        title.setText("valmistautuminen");
        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.VISIBLE);

        stepView.getState().stepsNumber(3).commit();
        stepView.go(1, false);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valmistautuminenPage1();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valmistautuminenPage3();
            }
        });
    }

    private void valmistautuminenPage3() {
        title.setText("valmistautuminen");
        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.VISIBLE);

<<<<<<< HEAD
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valmistautuminenPage2();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Home";
                menuActivity(v);
            }
        });
    }

    private void synnytysvaiheetPage1() {
        title.setText("valmistautuminen");
        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.VISIBLE);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Synnytysvaiheet";
                menuActivity(v);
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synnytysvaiheetPage2();
            }
        });
    }

    private void synnytysvaiheetPage2() {
        title.setText("valmistautuminen");
        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.VISIBLE);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synnytysvaiheetPage1();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synnytysvaiheetPage3();
            }
        });
    }

    private void synnytysvaiheetPage3() {
        title.setText("valmistautuminen");
        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.VISIBLE);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synnytysvaiheetPage2();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synnytysvaiheetPage4();
            }
        });
    }

    private void synnytysvaiheetPage4() {
        title.setText("valmistautuminen");
        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.VISIBLE);

=======
        stepView.getState().stepsNumber(3).commit();
        stepView.go(2, false);

>>>>>>> origin/Teemu
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valmistautuminenPage2();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Home";
                menuActivity(v);
            }
        });
    }

    private void tarkistusPage1() {
<<<<<<< HEAD
        title.setText("valmistautuminen");
        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.VISIBLE);
        textView.setText(changeText("synnytyksenJalkeen1.txt"));
=======
        title.setText("Synnytyksen Jälkeen 1");
        textView.setText(textViewContent("synnytyksenJalkeen1.txt"));
>>>>>>> origin/Teemu

        stepView.getState().stepsNumber(5).commit();
        stepView.go(0, false);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Tarkistus";
                menuActivity(v);
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarkistusPage2();
            }
        });
    }

    private void tarkistusPage2() {
<<<<<<< HEAD
        title.setText("valmistautuminen");
        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.VISIBLE);
        textView.setText(changeText("synnytyksenJalkeen2.txt"));
=======
        title.setText("Synnytyksen Jälkeen 2");
        textView.setText(textViewContent("synnytyksenJalkeen2.txt"));
>>>>>>> origin/Teemu

        stepView.getState().stepsNumber(5).commit();
        stepView.go(1, false);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarkistusPage1();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarkistusPage3();
            }
        });
    }

    private void tarkistusPage3() {
<<<<<<< HEAD
        title.setText("valmistautuminen");
        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.VISIBLE);
        textView.setText(changeText("synnytyksenJalkeen3.txt"));
=======
        title.setText("Synnytyksen Jälkeen 3");
        textView.setText(textViewContent("synnytyksenJalkeen3.txt"));
>>>>>>> origin/Teemu

        stepView.getState().stepsNumber(5).commit();
        stepView.go(2, false);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarkistusPage2();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName="Tarkistus4";
                imageTextActivity(v);
            }
        });
    }

<<<<<<< HEAD
    private void tarkistusPage4() {
        title.setText("valmistautuminen");
        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.VISIBLE);
        textView.setText(changeText("synnytyksenJalkeen4.txt"));

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarkistusPage3();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarkistusPage5();
            }
        });
    }

    private void tarkistusPage5() {
        title.setText("valmistautuminen");
        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.VISIBLE);
        textView.setText(changeText("synnytyksenJalkeen5.txt"));
=======

    private void tarkistusPage5() {
        title.setText("Synnytyksen Jälkeen 5");
        textView.setText(textViewContent("synnytyksenJalkeen5.txt"));
>>>>>>> origin/Teemu

        stepView.getState().stepsNumber(5).commit();
        stepView.go(4, false);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName="Tarkistus4";
                imageTextActivity(v);
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Home";
                menuActivity(v);
            }
        });
    }

    private void erikoistilanteetPage1() {
        title.setText("valmistautuminen");
        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.VISIBLE);

        stepView.getState().stepsNumber(4).commit();
        stepView.go(0, false);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Erikoistilanteet";
                menuActivity(v);
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                erikoistilanteetPage2();
            }
        });
    }

    private void erikoistilanteetPage2() {
        title.setText("valmistautuminen");
        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.VISIBLE);

        stepView.getState().stepsNumber(4).commit();
        stepView.go(1, false);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                erikoistilanteetPage1();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                erikoistilanteetPage3();
            }
        });
    }

    private void erikoistilanteetPage3() {
        title.setText("valmistautuminen");
        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.VISIBLE);

        stepView.getState().stepsNumber(4).commit();
        stepView.go(3, false);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                erikoistilanteetPage2();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Erikoistilanteet";
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
<<<<<<< HEAD
            // Lisää error viesti lukuvirheen tullessa
=======
            Log.d("test", "Error: Cannot access txt files");
>>>>>>> origin/Teemu
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

    public void menuActivity(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("ActivityName", activityName);
        startActivity(intent);
    }

<<<<<<< HEAD
=======
    public void imageTextActivity(View view){
        Intent intent = new Intent(this, ImageTextActivity.class);
        intent.putExtra("ActivityName", activityName);
        startActivity(intent);
    }

    public void settingsActivity(View view) {
        Intent intent = new Intent(this, Settings.class);
        intent.putExtra("previousActivityName", previousActivityName);
        startActivity(intent);
    }
    
    public void textViewActivity(View view) {
        Intent intent = new Intent(this, TextViewActivity.class);
        intent.putExtra("ActivityName", activityName);
        startActivity(intent);
    }

>>>>>>> origin/Teemu
    // Method for popup menu
    public void showPopup(View v) {
        // Create a new popup menu
        PopupMenu popup = new PopupMenu(this, v);

        // Instantiate popup_menu.xml into menu object and make it visible
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popup.getMenu());
        popup.show();

        // Set up a click listener to handle when menu items are clicked
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()  {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.settings:
<<<<<<< HEAD
                        //TODO Avaa asetukset sivut
                        return true;
                    case R.id.update:
                        //TODO avaa update sivu
                        return true;
=======
                        previousActivityName = activityName;
                        activityName = "Settings";
                        settingsActivity(v);
                        return true;
>>>>>>> origin/Teemu
                    case R.id.about:
                        //TODO avaa about sivu
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}