package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
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

        //Sivun vaihdon toiminnallisuus hakee ActivityName avaimella MenuActivitystä pusketun stringin
        // ja avaa sitä vastaavan sivun.

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            activityName = extras.getString("ActivityName");
        }

        switch (activityName) {
            case "Aikana1":
                aikanaPage1();
                break;
            case "Aikana2":
                aikanaPage2();
                break;
            case "Aikana3":
                aikanaPage3();
                break;
            case "Aikana4":
                aikanaPage4();
                break;
            case "Aikana5":
                aikanaPage5();
                break;
            case "Aikana6":
                aikanaPage6();
                break;
            case "Aikana7":
                aikanaPage7();
                break;
            case "Perätila1":
                peratilaPage1();
                break;
            case "Perätila2":
                peratilaPage2();
                break;
            case "Perätila3":
                peratilaPage3();
                break;
            case "Perätila4":
                peratilaPage4();
                break;
            case "Perätila5":
                peratilaPage5();
                break;
            case "Hartiadystokia1":
                hartiadystokiaPage1();
                break;
            case "Hartiadystokia2":
                hartiadystokiaPage2();
                break;
            case "Hartiadystokia3":
                hartiadystokiaPage3();
                break;
            case "Hartiadystokia4":
                hartiadystokiaPage4();
                break;
            case "Hartiadystokia5":
                hartiadystokiaPage5();
                break;
            case "Napanuora3":
                napanuoraPage3();
                break;

        }


    }

    //Funktiot joka sivulle
    private void aikanaPage1() {
        title.setText("Synnytyksen aikana");
        TextToChange.setText(changeText("SynnytyksenAikana1.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Home";
                menuActivity(v);
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { aikanaPage2(); }
        });
    }

    private void aikanaPage2() {
        title.setText("Synnytyksen aikana");
        TextToChange.setText(changeText("SynnytyksenAikana2.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { aikanaPage1(); }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { aikanaPage3(); }
        });
    }

    private void aikanaPage3() {
        title.setText("Synnytyksen aikana");
        TextToChange.setText(changeText("SynnytyksenAikana3.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { aikanaPage2(); }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { aikanaPage4(); }
        });
    }

    private void aikanaPage4() {
        title.setText("Synnytyksen aikana");
        TextToChange.setText(changeText("SynnytyksenAikana4.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { aikanaPage3(); }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { aikanaPage5(); }
        });
    }

    private void aikanaPage5() {
        title.setText("Synnytyksen aikana");
        TextToChange.setText(changeText("SynnytyksenAikana5.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { aikanaPage4(); }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { aikanaPage6(); }
        });
    }

    private void aikanaPage6() {
        title.setText("Synnytyksen aikana");
        TextToChange.setText(changeText("SynnytyksenAikana6.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { aikanaPage5(); }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { aikanaPage7(); }
        });
    }

    private void aikanaPage7() {
        title.setText("Synnytyksen aikana");
        TextToChange.setText(changeText("SynnytyksenAikana7.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { aikanaPage6(); }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName ="Home";
                menuActivity(v);
            }
        });
    }

    private void peratilaPage1(){
        title.setText("Perätila");
        TextToChange.setText(changeText("perätila1.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Erikoistilanteet";
                menuActivity(v);
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { peratilaPage2();}
        });
    }

    private void peratilaPage2(){
        title.setText("Perätila");
        TextToChange.setText(changeText("perätila2.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { peratilaPage1();}
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { peratilaPage3();}
        });
    }

    private void peratilaPage3(){
        title.setText("Perätila");
        TextToChange.setText(changeText("perätila3.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { peratilaPage2();}
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { peratilaPage4();}
        });
    }

    private void peratilaPage4(){
        title.setText("Perätila");
        TextToChange.setText(changeText("perätila4.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { peratilaPage3();}

        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { peratilaPage5();}
        });
    }

    private void peratilaPage5(){
        title.setText("Perätila");
        TextToChange.setText(changeText("perätila5.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { peratilaPage4();}
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Home";
                menuActivity(v);
            }
        });
    }

    private void hartiadystokiaPage1(){
        title.setText("Hartiadystokia");
        TextToChange.setText(changeText("hartiadystokia1.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Erikoistilanteet";
                menuActivity(v);
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { hartiadystokiaPage2();}
        });
    }

    private void hartiadystokiaPage2(){
        title.setText("Hartiadystokia");
        TextToChange.setText(changeText("hartiadystokia2.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { hartiadystokiaPage1();}
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { hartiadystokiaPage3();}
        });
    }

    private void hartiadystokiaPage3(){
        title.setText("Hartiadystokia");
        TextToChange.setText(changeText("hartiadystokia3.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { hartiadystokiaPage2();}
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { hartiadystokiaPage4();}
        });
    }

    private void hartiadystokiaPage4(){
        title.setText("Hartiadystokia");
        TextToChange.setText(changeText("hartiadystokia4.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { hartiadystokiaPage3();}
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { hartiadystokiaPage5();}
        });
    }

    private void hartiadystokiaPage5(){
        title.setText("Hartiadystokia");
        TextToChange.setText(changeText("hartiadystokia5.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { hartiadystokiaPage4();}
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Home";
                menuActivity(v);
            }
        });
    }

    //ainoa napanuorasivu johon tulee kuva?
    private void napanuoraPage3() {
        title.setText("Napanuoran esiinluiskahdus");
        TextToChange.setText(changeText("napanuora3.txt"));
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Napanuora2";
                textViewActivity(v);
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Napanuora4";
                textViewActivity(v);
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

    public void updateImage() { // tää on vaa referenssi
        String path = Environment.getExternalStorageDirectory() + "/AmbuApp/image2.jpg";
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        homeButton.setImageBitmap(bitmap);
    }

    protected void menuActivity(View v){
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("ActivityName", activityName);
        startActivity(intent);
    }
    public void textViewActivity(View view) {
        Intent intent = new Intent(this, TextViewActivity.class);
        intent.putExtra("ActivityName", activityName);
        startActivity(intent);
    }
}