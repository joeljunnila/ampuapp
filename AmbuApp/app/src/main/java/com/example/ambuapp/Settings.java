package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

public class Settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ImageButton homeButton;
    Button button, darkbutton;
    TextView title;
    TextView textView;
    TextView textView2;
    TextView textView3;
    ImageButton leftArrow;
    ImageButton rightArrow;
    String activityName;
    String previousActivityName;
    String[] items = new String[]{"Pieni", "Keskisuuri", "Suuri"};

    TextViewActivity textActivity = new TextViewActivity();
    MyFirebase myFirebase;
    Thread myFirebaseThread;

    boolean darkmode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        button = findViewById(R.id.button);
        homeButton = findViewById(R.id.homeButton);
        title = findViewById(R.id.title);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        leftArrow = findViewById(R.id.leftArrow);
        rightArrow = findViewById(R.id.rightArrow);
        rightArrow.setVisibility(View.INVISIBLE);
        darkbutton = findViewById(R.id.darkbutton);



        //otetaan entinen activityName talteen, jotta voidaan palata sinne paluu-nuolesta
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            previousActivityName = extras.getString("previousActivityName");
        }

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Home";
                menuActivity(v);
            }
        });

        //vaihtoehdot palautumissivulle
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (previousActivityName.equals("Valmistautuminen1") ||
                        previousActivityName.equals("Valmistautuminen2") ||
                        previousActivityName.equals("Valmistautuminen3") ||
                        previousActivityName.equals("Tarkistus1") ||
                        previousActivityName.equals("Tarkistus2") ||
                        previousActivityName.equals("Tarkistus3") ||
                        previousActivityName.equals("Tarkistus5") ||
                        previousActivityName.equals("Napanuora1") ||
                        previousActivityName.equals("Napanuora2") ||
                        previousActivityName.equals("Napanuora4") ||
                        previousActivityName.equals("tietoaSovelluksesta")) {
                    activityName = previousActivityName;
                    textViewActivity(v);
                }
                else if (previousActivityName.equals("Aikana1") ||
                        previousActivityName.equals("Aikana2") ||
                        previousActivityName.equals("Aikana3") ||
                        previousActivityName.equals("Aikana4") ||
                        previousActivityName.equals("Aikana5") ||
                        previousActivityName.equals("Aikana6") ||
                        previousActivityName.equals("Tarkistus4") ||
                        previousActivityName.equals("Perätila1") ||
                        previousActivityName.equals("Perätila2") ||
                        previousActivityName.equals("Perätila3") ||
                        previousActivityName.equals("Perätila4") ||
                        previousActivityName.equals("Perätila5") ||
                        previousActivityName.equals("Hartiadystokia1") ||
                        previousActivityName.equals("Hartiadystokia2") ||
                        previousActivityName.equals("Hartiadystokia3") ||
                        previousActivityName.equals("Hartiadystokia4") ||
                        previousActivityName.equals("Hartiadystokia5") ||
                        previousActivityName.equals("Napanuora3")) {
                    activityName = previousActivityName;
                    imageTextActivity(v);
                }
                else {
                    activityName = previousActivityName;
                    menuActivity(v);
                }
            }
        });

        darkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (darkmode == false) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    darkmode = true;
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    darkmode = false;
                }
            }
        });

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner);
        //create a list of items for the spinner.
        //create an adapter to describe how the items are displayed
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
    }

    //päivitetään materiaalit sovellukseen
    public void update(View v) {
        myFirebase = new MyFirebase();
        myFirebaseThread = new Thread(myFirebase);
        myFirebaseThread.start();
        textView2.setText("Updated");
    }

    public void menuActivity(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("ActivityName", activityName);
        startActivity(intent);
    }
    public void settingsActivity(View view) {
        Intent intent = new Intent(this, Settings.class);
        intent.putExtra("ActivityName", activityName);
        startActivity(intent);
    }
    public void textViewActivity(View view) {
        Intent intent = new Intent(this, TextViewActivity.class);
        intent.putExtra("ActivityName", activityName);
        startActivity(intent);
    }
    public void imageTextActivity(View view) {
        Intent intent = new Intent(this, ImageTextActivity.class);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        switch (position) {
            case 0:
                textActivity.textSize = 10;
                ImageTextActivity.textSize = 10;
                textView2.setTextSize(10);

                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                textActivity.textSize = 18;
                ImageTextActivity.textSize = 18;
                textView2.setTextSize(18);
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                textActivity.textSize = 30;
                ImageTextActivity.textSize = 30;
                textView2.setTextSize(30);
                // Whatever you want to happen when the third item gets selected
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
}
