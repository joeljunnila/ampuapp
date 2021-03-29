package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
<<<<<<< HEAD
=======
import android.content.pm.PackageManager;
>>>>>>> origin/Teemu
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.shuhart.stepview.StepView;

public class MenuActivity extends AppCompatActivity {
    ImageButton homeButton;
    TextView title;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    ImageButton leftArrow;
    ImageButton rightArrow;
    String activityName = "Home";
    String previousActivityName;
    boolean permissionGranted = false;
    
    MyFirebase myFirebase;
    Thread myFirebaseThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        if(!permissionGranted) {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 1);
                //myFirebase = new MyFirebase();
                //myFirebaseThread = new Thread(myFirebase);
                //myFirebaseThread.start();
            } else {
                permissionGranted = true;
            }
        }

        homeButton = findViewById(R.id.homeButton);
        title = findViewById(R.id.title);
<<<<<<< HEAD
        naviconButton = findViewById(R.id.naviconButton);

        /*naviconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings();
            }
        });*/
=======
>>>>>>> origin/Teemu

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);

        leftArrow = findViewById(R.id.leftArrow);
        rightArrow = findViewById(R.id.rightArrow);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            activityName = extras.getString("ActivityName");
        }

        switch (activityName) {
            case "Home":
                homePage();
                break;
            case "Valmistautuminen":
                valmistautuminenPage();
                break;
            case "Synnytysvaiheet":
                synnytysvaiheetPage();
                break;
            case "Tarkistus":
                tarkistusPage();
                break;
            case "Erikoistilanteet":
                erikoistilanteetPage();
                break;
        }
    }

    public void homePage() {
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Disable home button
            }
        });

        title.setText("AmbuApp");
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.VISIBLE);

        button3.setText("Valmistautuminen");
        button4.setText("Synnytysvaiheet");
        button5.setText("Tarkistus");
        button6.setText("Erikoistilanteet");

        leftArrow.setVisibility(View.INVISIBLE);
        rightArrow.setVisibility(View.INVISIBLE);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Valmistautuminen";
                valmistautuminenPage();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Synnytysvaiheet";
                synnytysvaiheetPage();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Tarkistus";
                tarkistusPage();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Erikoistilanteet";
                erikoistilanteetPage();
            }
        });
    }

<<<<<<< HEAD
    public void settings() {
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.INVISIBLE);
        button5.setVisibility(View.INVISIBLE);
        button6.setVisibility(View.INVISIBLE);

        button3.setText("UPDATE");

        leftArrow.setVisibility(View.INVISIBLE);
        rightArrow.setVisibility(View.INVISIBLE);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFirebase = new MyFirebase();
                myFirebaseThread = new Thread(myFirebase);
                myFirebaseThread.start();
            }
        });
    }

=======
>>>>>>> origin/Teemu
    public void valmistautuminenPage() {
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePage();
            }
        });

        title.setText("Valmistautuminen");
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.INVISIBLE);

        button3.setText("Valmistautuminen");
        button4.setText("Kohteessa vai matkaan?");
        button5.setText("Miten toimitaan");

        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.INVISIBLE);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePage();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Valmistautuminen1";
                textViewActivity(v);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Valmistautuminen2";
                textViewActivity(v);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Valmistautuminen3";
                textViewActivity(v);
            }
        });
    }

    //Synnytyksen aikana -valikko valitsee avaimen ImageText -activityyn
    public void synnytysvaiheetPage(){
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePage();
            }
        });

        title.setText("Synnytyksen aikana");
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.INVISIBLE);

        button2.setText("Synnytyksen aikana 2");
        button3.setText("Synnytyksen aikana 3");
        button4.setText("Synnytyksen aikana 4");
        button5.setText("Synnytyksen aikana 5");

        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.INVISIBLE);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePage();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kuvaTekstiActivity(v);
            }
        });
    }

    public void tarkistusPage(){
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { homePage();; }
        });

        title.setText("Tarkistus");
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.INVISIBLE);

        button1.setText("Synnytyksen jälkeen 1");
        button2.setText("Synnytyksen jälkeen 2");
        button3.setText("Synnytyksen jälkeen 3");
        button4.setText("Synnytyksen jälkeen 4");
        button5.setText("Synnytyksen jälkeen 5");

        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.INVISIBLE);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePage();
            }
        });

        button1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Tarkistus1";
                textViewActivity(v);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Tarkistus2";
                textViewActivity(v);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Tarkistus3";
                textViewActivity(v);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Tarkistus4";
                imageTextActivity(v);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Tarkistus5";
                textViewActivity(v);
            }
        });
    }

    public void erikoistilanteetPage() {
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePage();
            }
        });

        title.setText("Erikoistilanteet");
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.INVISIBLE);

        button3.setText("Perätila");
        button4.setText("Hartiadystokia");
        button5.setText("Napanuoran esiinluiskahdus");

        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.INVISIBLE);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePage();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                activityName = "KuvaTekstiActivity";
=======
                activityName = "Perätila3";
                imageTextActivity(v);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Perätila4";
                imageTextActivity(v);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Perätila5";
                imageTextActivity(v);
            }
        });
    }

    public void hartiadystokiaPage() {
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePage();
            }
        });

        title.setText("Hartiadystokia");
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.INVISIBLE);

        button1.setText("Hartiadystokia vaihe1");
        button2.setText("Hartiadystokia vaihe2");
        button3.setText("Hartiadystokia vaihe3");
        button4.setText("Hartiadystokia vaihe4");
        button5.setText("Hartiadystokia vaihe5");

        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.INVISIBLE);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { erikoistilanteetPage();}
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Hartiadystokia1";
                imageTextActivity(v);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Hartiadystokia2";
                imageTextActivity(v);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Hartiadystokia3";
                imageTextActivity(v);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Hartiadystokia4";
                imageTextActivity(v);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Hartiadystokia5";
                imageTextActivity(v);
            }
        });
    }

    public void napanuoraPage() {
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePage();
            }
        });

        title.setText("Napanuoran esiinluiskahdus");
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.INVISIBLE);

        button2.setText("Napanuora vaihe1");
        button3.setText("Napanuora vaihe2");
        button4.setText("Napanuora vaihe3");
        button5.setText("Napanuora vaihe4");

        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.INVISIBLE);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { erikoistilanteetPage();}
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Napanuora1";
                textViewActivity(v);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Napanuora2";
>>>>>>> origin/Teemu
                textViewActivity(v);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Valmistautuminen1";
                textViewActivity(v);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Valmistautuminen1";
                textViewActivity(v);
            }
        });
    }

<<<<<<< HEAD
    public void kuvaTekstiActivity(View view){
        Intent intent = new Intent(this, KuvaTekstiActivity.class);
=======
    //Avaimen välitys ImageTextiin
    public void imageTextActivity(View view){
        Intent intent = new Intent(this, ImageTextActivity.class);
>>>>>>> origin/Teemu
        intent.putExtra("ActivityName", activityName);
        startActivity(intent);

    }

    public void textViewActivity(View view) {
        Intent intent = new Intent(this, TextViewActivity.class);
        intent.putExtra("ActivityName", activityName);
        startActivity(intent);
    }
<<<<<<< HEAD
=======
    public void settingsActivity(View view) {
        Intent intent = new Intent(this, Settings.class);
        intent.putExtra("previousActivityName", previousActivityName);
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