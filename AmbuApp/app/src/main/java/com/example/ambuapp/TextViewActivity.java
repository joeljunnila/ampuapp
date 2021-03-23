package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.StringBuilder;

import com.example.*;

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


    private void valmistautuminenPage1() {
        textView.setText(textViewContent("valmistautuminen1.txt"));

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
        textView.setText(textViewContent("synnytyksenJalkeen1.txt"));

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
        textView.setText(textViewContent("synnytyksenJalkeen2.txt"));

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
        textView.setText(textViewContent("synnytyksenJalkeen3.txt"));

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
        textView.setText(textViewContent("synnytyksenJalkeen4.txt"));

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
        textView.setText(textViewContent("synnytyksenJalkeen5.txt"));

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
        textView.setText(textViewContent("napanuora1.txt"));

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
            Log.d("test", "Error: textViewContent");
        }

        return text.toString();
    }

    public void menuActivity(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
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
                        //TODO Avaa asetukset sivut
                        return true;
                    case R.id.update:
                        //TODO avaa update sivu
                        return true;
                    case R.id.about:
                        //TODO avaa about sivu
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    public void imageTextActivity(View view){
        Intent intent = new Intent(this, ImageTextActivity.class);
        intent.putExtra("ActivityName", activityName);
        startActivity(intent);

    }
}