package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.shuhart.stepview.StepView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ImageTextActivity extends AppCompatActivity {

    ImageButton homeButton;
    ImageButton rightArrow;
    ImageButton leftArrow;
    ImageView imageView;

    TextView TextToChange;
    TextView title;
    StepView stepView;

    String activityName;
    String previousActivityName;

    public static Integer textSize = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_text);

        rightArrow = findViewById(R.id.rightArrow);
        leftArrow = findViewById(R.id.leftArrow);
        homeButton = findViewById(R.id.homeButton);

        imageView = findViewById(R.id.imageView);
        TextToChange = findViewById(R.id.textViewSA1);
        //TextToChange.setTextSize(textSize);
        title = findViewById(R.id.title);
        stepView = findViewById(R.id.stepView);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    activityName = "Home";
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
            case "Valmistautuminen1":
                valmistautuminenPage1();
                break;
            case "Valmistautuminen2":
                valmistautuminenPage2();
                break;
            case "Valmistautuminen3":
                valmistautuminenPage3();
                break;
            case "Valmistautuminen4":
                valmistautuminenPage4();
                break;
            case "Valmistautuminen5":
                valmistautuminenPage5();
                break;
            case "Valmistautuminen6":
                valmistautuminenPage6();
                break;
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
            case "Napanuora1":
                napanuoraPage1();
                break;
            case "Napanuora2" :
                napanuoraPage2();
                break;
            case "Napanuora3":
                napanuoraPage3();
                break;
            case "tietoaSovelluksesta":
                aboutPage();
                break;

        }
    }

    //Funktiot joka sivulle

    private void aboutPage() {
        title.setText("Tietoa sovelluksesta");
        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.INVISIBLE);
        hideImage();
        TextToChange.setText(textViewContent("tietoaSovelluksesta.txt"));

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

    //haetaan materiaalit ja activityName valitun sivun mukaan
    private void valmistautuminenPage1() {
        title.setText("Huomioitavaa");
        hideImage();
        TextToChange.setText(textViewContent("valmistautuminen1.txt"));

        stepView.getState().stepsNumber(6).commit();
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
        hideImage();
        title.setText("Synnytyksen tilanne");
        TextToChange.setText(textViewContent("valmistautuminen2.txt"));

        stepView.getState().stepsNumber(6).commit();
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
        hideImage();
        title.setText("Hoidetaan kohteessa");
        TextToChange.setText(textViewContent("valmistautuminen3.txt"));

        stepView.getState().stepsNumber(6).commit();
        stepView.go(2, false);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valmistautuminenPage2();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valmistautuminenPage4();
            }
        });
    }

    private void valmistautuminenPage4() {
        hideImage();
        title.setText("Milloin matkaan");
        TextToChange.setText(textViewContent("valmistautuminen4.txt"));

        stepView.getState().stepsNumber(6).commit();
        stepView.go(3, false);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valmistautuminenPage3();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valmistautuminenPage5();
            }
        });
    }

    private void valmistautuminenPage5() {
        hideImage();
        title.setText("Miten toimitaan");
        TextToChange.setText(textViewContent("valmistautuminen5.txt"));

        stepView.getState().stepsNumber(6).commit();
        stepView.go(4, false);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valmistautuminenPage4();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valmistautuminenPage6();
            }
        });
    }

    private void valmistautuminenPage6() {
        hideImage();
        title.setText("Hyvä tietää");
        TextToChange.setText(textViewContent("valmistautuminen6.txt"));

        stepView.getState().stepsNumber(6).commit();
        stepView.go(5, false);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valmistautuminenPage5();
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

    private void aikanaPage1() {
        title.setText("Synnytyksen aikana");
        imageView.setImageBitmap(updateImage("ohje.jpg"));
        TextToChange.setText(textViewContent("synnytyksenAikana1.txt"));

        stepView.getState().stepsNumber(6).commit();
        stepView.go(0, false);

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
        imageView.setImageBitmap(updateImage("ohje.jpg"));
        TextToChange.setText(textViewContent("synnytyksenAikana2.txt"));

        stepView.getState().stepsNumber(6).commit();
        stepView.go(1, false);

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
        imageView.setImageBitmap(updateImage("ohje.jpg"));
        TextToChange.setText(textViewContent("synnytyksenAikana3.txt"));

        stepView.getState().stepsNumber(6).commit();
        stepView.go(2, false);

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
        imageView.setImageBitmap(updateImage("ohje.jpg"));
        TextToChange.setText(textViewContent("synnytyksenAikana4.txt"));

        stepView.getState().stepsNumber(6).commit();
        stepView.go(3, false);

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
        imageView.setImageBitmap(updateImage("ohje.jpg"));
        TextToChange.setText(textViewContent("synnytyksenAikana5.txt"));

        stepView.getState().stepsNumber(6).commit();
        stepView.go(4, false);

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
        imageView.setImageBitmap(updateImage("ohje.jpg"));
        TextToChange.setText(textViewContent("synnytyksenAikana6.txt"));

        stepView.getState().stepsNumber(6).commit();
        stepView.go(5, false);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { aikanaPage5(); }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName ="Home";
                menuActivity(v); }
        });
    }


    private void tarkistusPage1() {
        title.setText("Lapsen synnyttyä");
        hideImage();
        TextToChange.setText(textViewContent("synnytyksenJalkeen1.txt"));

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
        title.setText("Napanuoran leikkaus");
        hideImage();
        TextToChange.setText(textViewContent("synnytyksenJalkeen2.txt"));

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
        title.setText("Jälkeisvaihe");
        hideImage();
        TextToChange.setText(textViewContent("synnytyksenJalkeen3.txt"));

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
                tarkistusPage4();
            }
        });
    }

    //Synnytyksen jalkeen kuva sivu
    private void tarkistusPage4() {
        title.setText("Toimi näin");
        TextToChange.setText(textViewContent("synnytyksenJalkeen4.txt"));
        imageView.setImageBitmap(updateImage("ohje.jpg"));

        stepView.getState().stepsNumber(5).commit();
        stepView.go(3, false);
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
        title.setText("Tarkkailu");
        hideImage();
        TextToChange.setText(textViewContent("synnytyksenJalkeen5.txt"));

        stepView.getState().stepsNumber(5).commit();
        stepView.go(4, false);

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

    private void peratilaPage1(){
        title.setText("Perätila");
        imageView.setImageBitmap(updateImage("ohje.jpg"));
        TextToChange.setText(textViewContent("peratila1.txt"));

        stepView.getState().stepsNumber(5).commit();
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
            public void onClick(View v) { peratilaPage2();}
        });
    }

    private void peratilaPage2(){
        title.setText("Perätila");
        imageView.setImageBitmap(updateImage("ohje.jpg"));
        TextToChange.setText(textViewContent("peratila2.txt"));

        stepView.getState().stepsNumber(5).commit();
        stepView.go(1, false);

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
        imageView.setImageBitmap(updateImage("ohje.jpg"));
        TextToChange.setText(textViewContent("peratila3.txt"));

        stepView.getState().stepsNumber(5).commit();
        stepView.go(2, false);

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
        imageView.setImageBitmap(updateImage("ohje.jpg"));
        TextToChange.setText(textViewContent("peratila4.txt"));

        stepView.getState().stepsNumber(5).commit();
        stepView.go(3, false);

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
        imageView.setImageBitmap(updateImage("ohje.jpg"));
        TextToChange.setText(textViewContent("peratila5.txt"));

        stepView.getState().stepsNumber(5).commit();
        stepView.go(4, false);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { peratilaPage4();}
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Erikoistilanteet";
                menuActivity(v);
            }
        });
    }

    private void hartiadystokiaPage1(){
        title.setText("Hartiadystokia");
        imageView.setImageBitmap(updateImage("ohje.jpg"));
        TextToChange.setText(textViewContent("hartiadystokia1.txt"));

        stepView.getState().stepsNumber(5).commit();
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
            public void onClick(View v) { hartiadystokiaPage2();}
        });
    }

    private void hartiadystokiaPage2(){
        title.setText("Hartiadystokia");
        imageView.setImageBitmap(updateImage("ohje.jpg"));
        TextToChange.setText(textViewContent("hartiadystokia2.txt"));

        stepView.getState().stepsNumber(5).commit();
        stepView.go(1, false);

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
        imageView.setImageBitmap(updateImage("ohje.jpg"));
        TextToChange.setText(textViewContent("hartiadystokia3.txt"));

        stepView.getState().stepsNumber(5).commit();
        stepView.go(2, false);

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
        imageView.setImageBitmap(updateImage("ohje.jpg"));
        TextToChange.setText(textViewContent("hartiadystokia4.txt"));

        stepView.getState().stepsNumber(5).commit();
        stepView.go(3, false);

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
        imageView.setImageBitmap(updateImage("ohje.jpg"));
        TextToChange.setText(textViewContent("hartiadystokia5.txt"));

        stepView.getState().stepsNumber(5).commit();
        stepView.go(4, false);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { hartiadystokiaPage4();}
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Erikoistilanteet";
                menuActivity(v);
            }
        });
    }

    private void napanuoraPage1() {
        title.setText("Napanuoran esiinluiskahdus");
        hideImage();
        TextToChange.setText(textViewContent("napanuora1.txt"));

        stepView.getState().stepsNumber(3).commit();
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
            public void onClick(View v) { napanuoraPage2();}
        });
    }

    private void napanuoraPage2() {
        title.setText("Napanuoran esiinluiskahdus");
        hideImage();
        TextToChange.setText(textViewContent("napanuora2.txt"));

        stepView.getState().stepsNumber(3).commit();
        stepView.go(1, false);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { napanuoraPage1();}
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //siirrytään 3 sivulla jossa on kuva
                napanuoraPage3();
            }
        });
    }

    //ainoa napanuorasivu johon tulee kuva?
    private void napanuoraPage3() {
        title.setText("Napanuoran esiinluiskahdus");
        imageView.setImageBitmap(updateImage("ohje.jpg"));
        TextToChange.setText(textViewContent("napanuora3.txt"));

        stepView.getState().stepsNumber(3).commit();
        stepView.go(2, false);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                napanuoraPage2();
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


    public String textViewContent(String fileName) {
        //file directory
        File txtDir = new File(String.valueOf(getFilesDir()));

        //Get the text file from directory
        File file = new File(txtDir, fileName);

        //Read text from file
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append('\n');
            }
            br.close();
        }
        catch (IOException e) {
            Log.d("test", "Error: Cannot access txt files");
        }

        return sb.toString();
    }

    public Bitmap updateImage(String fileName) {
        File file = new File(getFilesDir(), fileName);
        // Palauta imageView näkyviin
        imageView.setVisibility(View.VISIBLE);

        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(String.valueOf(file));
            return bitmap;
        } else {
            Log.d("test", "Error: Image not found");
            return null;
        }
    }

    // Funktio imageView olion piilottamiseen
    protected void hideImage() {
        imageView.setVisibility(View.GONE);
    }

    //Päävalikkoon palaamisen funktio ja funktiot muihin näkymiin
    protected void menuActivity(View v){
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("ActivityName", activityName);
        startActivity(intent);
    }

    public void settingsActivity(View view) {
        Intent intent = new Intent(this, Settings.class);
        intent.putExtra("previousActivityName", previousActivityName);
        startActivity(intent);
    }

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
                        previousActivityName = activityName;
                        activityName = "Settings";
                        settingsActivity(v);
                        return true;
                    case R.id.about:
                        aboutPage();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}