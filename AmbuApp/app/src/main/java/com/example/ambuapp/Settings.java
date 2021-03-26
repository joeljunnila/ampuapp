package com.example.ambuapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ImageButton homeButton;
    Button button;
    TextView title;
    TextView textView;
    TextView textView2;
    TextView textView3;
    ImageButton leftArrow;
    ImageButton rightArrow;
    String activityName;
    String previousActivityName;
    String[] items = new String[]{"Pieni", "Keskisuuri", "Suuri"};

    StorageReference storageRef;
    TextViewActivity textActivity = new TextViewActivity();
    ArrayList<String> imageFileNames = new ArrayList<>();
    ArrayList<StorageReference> txtRefs = new ArrayList<>();
    ArrayList<String> txtFileNames = new ArrayList<>();

    int firebaseFileCounter = 0;
    ArrayList<StorageReference> imageRefs = new ArrayList<>();

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

        FirebaseStorage.getInstance().getReference();

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
        firebase();
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

    public void firebase() {
        imageRefs.add(storageRef.child("kuvat/ohje.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje3.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje4.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje5.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje7.jpg"));

        imageFileNames.add("image1.jpg");
        imageFileNames.add("image2.jpg");
        imageFileNames.add("image3.jpg");
        imageFileNames.add("image4.jpg");
        imageFileNames.add("image5.jpg");

        txtRefs.add(storageRef.child("tekstit/hartiadystokia1.txt"));
        txtRefs.add(storageRef.child("tekstit/hartiadystokia2.txt"));
        txtRefs.add(storageRef.child("tekstit/hartiadystokia3.txt"));
        txtRefs.add(storageRef.child("tekstit/hartiadystokia4.txt"));
        txtRefs.add(storageRef.child("tekstit/hartiadystokia5.txt"));
        txtRefs.add(storageRef.child("tekstit/napanuora1.txt"));
        txtRefs.add(storageRef.child("tekstit/napanuora2.txt"));
        txtRefs.add(storageRef.child("tekstit/napanuora3.txt"));
        txtRefs.add(storageRef.child("tekstit/napanuora4.txt"));
        txtRefs.add(storageRef.child("tekstit/peratila1.txt"));
        txtRefs.add(storageRef.child("tekstit/peratila2.txt"));
        txtRefs.add(storageRef.child("tekstit/peratila3.txt"));
        txtRefs.add(storageRef.child("tekstit/peratila4.txt"));
        txtRefs.add(storageRef.child("tekstit/peratila5.txt"));
        txtRefs.add(storageRef.child("tekstit/synnytyksenAikana1.txt"));
        txtRefs.add(storageRef.child("tekstit/synnytyksenAikana2.txt"));
        txtRefs.add(storageRef.child("tekstit/synnytyksenAikana3.txt"));
        txtRefs.add(storageRef.child("tekstit/synnytyksenAikana4.txt"));
        txtRefs.add(storageRef.child("tekstit/synnytyksenAikana5.txt"));
        txtRefs.add(storageRef.child("tekstit/synnytyksenAikana6.txt"));
        txtRefs.add(storageRef.child("tekstit/synnytyksenAikana7.txt"));
        txtRefs.add(storageRef.child("tekstit/synnytyksenJalkeen1.txt"));
        txtRefs.add(storageRef.child("tekstit/synnytyksenJalkeen2.txt"));
        txtRefs.add(storageRef.child("tekstit/synnytyksenJalkeen3.txt"));
        txtRefs.add(storageRef.child("tekstit/synnytyksenJalkeen4.txt"));
        txtRefs.add(storageRef.child("tekstit/synnytyksenJalkeen5.txt"));
        txtRefs.add(storageRef.child("tekstit/tietoaSovelluksesta.txt"));
        txtRefs.add(storageRef.child("tekstit/valmistautuminen1.txt"));
        txtRefs.add(storageRef.child("tekstit/valmistautuminen2.txt"));
        txtRefs.add(storageRef.child("tekstit/valmistautuminen3.txt"));

        txtFileNames.add("hartiadystokia1.txt");
        txtFileNames.add("hartiadystokia2.txt");
        txtFileNames.add("hartiadystokia3.txt");
        txtFileNames.add("hartiadystokia4.txt");
        txtFileNames.add("hartiadystokia5.txt");
        txtFileNames.add("napanuora1.txt");
        txtFileNames.add("napanuora2.txt");
        txtFileNames.add("napanuora3.txt");
        txtFileNames.add("napanuora4.txt");
        txtFileNames.add("peratila1.txt");
        txtFileNames.add("peratila2.txt");
        txtFileNames.add("peratila3.txt");
        txtFileNames.add("peratila4.txt");
        txtFileNames.add("peratila5.txt");
        txtFileNames.add("synnytyksenAikana1.txt");
        txtFileNames.add("synnytyksenAikana2.txt");
        txtFileNames.add("synnytyksenAikana3.txt");
        txtFileNames.add("synnytyksenAikana4.txt");
        txtFileNames.add("synnytyksenAikana5.txt");
        txtFileNames.add("synnytyksenAikana6.txt");
        txtFileNames.add("synnytyksenAikana7.txt");
        txtFileNames.add("synnytyksenJalkeen1.txt");
        txtFileNames.add("synnytyksenJalkeen2.txt");
        txtFileNames.add("synnytyksenJalkeen3.txt");
        txtFileNames.add("synnytyksenJalkeen4.txt");
        txtFileNames.add("synnytyksenJalkeen5.txt");
        txtFileNames.add("tietoaSovelluksesta.txt");
        txtFileNames.add("valmistautuminen1.txt");
        txtFileNames.add("valmistautuminen2.txt");
        txtFileNames.add("valmistautuminen3.txt");

        for(int i=0; i<txtRefs.size(); i++) {
            downloadFileFromFirebase(txtRefs.get(i), getFilesDir(), txtFileNames.get(i));
        }
    }

    public void downloadFileFromFirebase(StorageReference ref, File dir, String name) {
        File imageFile = new File(dir, name);
        ref.getFile(imageFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                firebaseFileCounter++;
                if(firebaseFileCounter == (imageFileNames.size() + txtFileNames.size())) {
                    Toast.makeText(getApplicationContext(), "Updated succesfully!", Toast.LENGTH_LONG).show();
                    Log.d("test", "Succesfully connected to the Firebase");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getApplicationContext(), "Update failed!", Toast.LENGTH_LONG).show();
                Log.d("test", "Firebase error");
            }
        });
    }

    public void firebase3() {
        String text = "hello world!";
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("test.txt", MODE_PRIVATE);
            fos.write(text.getBytes());

            Log.d("test", "save: " + getFilesDir() + "test.txt");
            Toast.makeText(this, "save: " + getFilesDir() + " !! ", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void firebase2() {
        FileInputStream fis = null;
        try {
            fis = openFileInput("test.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while((text = br.readLine()) != null) {
                sb.append(text).append("\n");
                Log.d("test", "asd: " + text);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
