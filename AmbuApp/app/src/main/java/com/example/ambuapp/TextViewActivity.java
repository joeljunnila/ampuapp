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
import java.io.File;
import java.io.FileReader;
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
    String previousActivityName;

    public static Integer textSize = 20;

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
        textView.setText(textViewContent("tietoaSovelluksesta.txt"));

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
        textView.setText(textViewContent("valmistautuminen1.txt"));

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
        textView.setText(textViewContent("valmistautuminen2.txt"));

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
        textView.setText(textViewContent("valmistautuminen3.txt"));

        stepView.getState().stepsNumber(3).commit();
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
                activityName = "Home";
                menuActivity(v);
            }
        });
    }

    private void tarkistusPage1() {
        title.setText("Synnytyksen Jälkeen 1");
        textView.setText(textViewContent("synnytyksenJalkeen1.txt"));

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
        title.setText("Synnytyksen Jälkeen 2");
        textView.setText(textViewContent("synnytyksenJalkeen2.txt"));

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
        title.setText("Synnytyksen Jälkeen 3");
        textView.setText(textViewContent("synnytyksenJalkeen3.txt"));

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


    private void tarkistusPage5() {
        title.setText("Synnytyksen Jälkeen 5");
        textView.setText(textViewContent("synnytyksenJalkeen5.txt"));

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

    private void napanuoraPage1() {
        title.setText("Napanuoran esiinluiskahdus");
        textView.setText(textViewContent("napanuora1.txt"));

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
            public void onClick(View v) { napanuoraPage2();}
        });
    }

    private void napanuoraPage2() {
        title.setText("Napanuoran esiinluiskahdus");
        textView.setText(textViewContent("napanuora2.txt"));

        stepView.getState().stepsNumber(4).commit();
        stepView.go(1, false);

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
        textView.setText(textViewContent("napanuora4.txt"));

        stepView.getState().stepsNumber(4).commit();
        stepView.go(3, false);

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
                activityName = "Erikoistilanteet";
                menuActivity(v);
            }
        });
    }

    private String textViewContent(String fileName) {
        File txtDir = new File(Environment.getExternalStorageDirectory() + "/AmBuApp/TextFiles");

        //Get the text file
        File file = new File(txtDir, fileName);

        //Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        }
        catch (IOException e) {
            Log.d("test", "Error: Cannot access txt files");
        }

        return text.toString();
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
                        activityName = "tietoaSovelluksesta";
                        textViewActivity(v);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}