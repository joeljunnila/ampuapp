package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.StringBuilder;

public class TextViewActivity extends AppCompatActivity {
    ImageButton homeButton;
    TextView title;
    ImageButton naviconButton;
    TextView textView;
    ImageButton rightArrow;
    ImageButton leftArrow;

    String activityName;
    StringBuilder sb = new StringBuilder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        homeButton = findViewById(R.id.homeButton);
        title = findViewById(R.id.title);
        naviconButton = findViewById(R.id.naviconButton);
        textView = findViewById(R.id.content);
        rightArrow = findViewById(R.id.rightArrow);
        leftArrow = findViewById(R.id.leftArrow);

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
            case "Tarkistus1":
                tarkistusPage1();
                break;
            case "Tarkistus2":
                tarkistusPage2();
                break;
            case "Tarkistus3":
                tarkistusPage3();
                break;
            case "Tarkistus4":
                tarkistusPage4();
                break;
            case "Tarkistus5":
                tarkistusPage5();
                break;
            case "Napanuora1":
                napanuoraPage1();
                break;
            case "Napanuora2" :
                napanuoraPage2();
                break;
            case "Napanuora4":
                napanuoraPage4();
                break;
            case "tietoaSovelluksesta":
                aboutPage();
                break;
            /*case "asetukset":
                settingsPage();
                break; */
        }
    }

    private void aboutPage() {
        title.setText("Tietoa sovelluksesta");
        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.INVISIBLE);
        textView.setText(changeText("tietoaSovelluksesta.txt"));

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Home";
                menuActivity(v);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Home";
                menuActivity(v);
            }
        });
    }


    private void valmistautuminenPage1() {
        title.setText("Valmistautuminen");
        textView.setText(changeText("valmistautuminen1.txt"));

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
        title.setText("Valmistautuminen");
        textView.setText(changeText("valmistautuminen2.txt"));

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
        title.setText("Valmistautuminen");
        textView.setText(changeText("valmistautuminen3.txt"));

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

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synnytysvaiheetPage3();
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
        title.setText("valmistautuminen");

        textView.setText(changeText("synnytyksenJalkeen1.txt"));

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
        title.setText("valmistautuminen");
        textView.setText(changeText("synnytyksenJalkeen2.txt"));

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
        title.setText("valmistautuminen");
        textView.setText(changeText("synnytyksenJalkeen3.txt"));

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarkistusPage2();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarkistusPage4();
            }
        });
    }

    private void tarkistusPage4() {
        title.setText("valmistautuminen");
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
        textView.setText(changeText("synnytyksenJalkeen5.txt"));

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarkistusPage4();
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

    private void napanuoraPage1() {
        title.setText("Napanuoran esiinluiskahdus");
        textView.setText(changeText("napanuora1.txt"));

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Erikoistilanteet";
                menuActivity(v);
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { napanuoraPage2();}
        });
    }

    private void napanuoraPage2() {
        title.setText("Napanuoran esiinluiskahdus");
        textView.setText(changeText("napanuora2.txt"));

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { napanuoraPage1();}
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //siirrytään 3 sivulla jossa on kuva
                activityName = "Napanuora3";
                imageTextActivity(v);
            }
        });
    }

    private void napanuoraPage4() {
        title.setText("Napanuoran esiinluiskahdus");
        textView.setText(changeText("napanuora4.txt"));

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //siirrytään 3 sivulla jossa on kuva
                activityName = "Napanuora3";
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

    public void menuActivity(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("ActivityName", activityName);
        startActivity(intent);
    }
    public void imageTextActivity(View view){
        Intent intent = new Intent(this, ImageTextActivity.class);
        intent.putExtra("ActivityName", activityName);
        startActivity(intent);

    }
}