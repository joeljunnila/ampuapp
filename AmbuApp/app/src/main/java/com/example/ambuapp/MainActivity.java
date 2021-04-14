package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.PopupMenu;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.shuhart.stepview.StepView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@SuppressLint("UseSwitchCompatOrMaterialCode")
public class MainActivity extends AppCompatActivity {

    //region variables
    //layouts
    ConstraintLayout layoutMenu;
    ConstraintLayout layoutImageText;
    ConstraintLayout layoutSettings;
    ConstraintLayout imageArea;

    //menubar
    TextView title;
    ImageButton naviconButton;

    //content
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    ImageView imageView;
    TextView textView;
    StepView stepView;
    TextView textSizeTextView;
    Spinner spinner;
    Switch darkModeSwitch;
    Button updateButton;
    ScrollView scrollView;

    //footer
    ImageButton leftArrow;
    ImageButton rightArrow;

    //firebase
    StorageReference storageRef;
    ArrayList<String> imageFileNames = new ArrayList<>();
    ArrayList<String> textFileNames = new ArrayList<>();
    ArrayList<StorageReference> imageRefs = new ArrayList<>();
    ArrayList<StorageReference> textRefs = new ArrayList<>();

    String activityName = "kotisivu";
    String imageFileDir = "kuvat/";
    String textFileDir = "tekstit/";
    int fileCounter = 0;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region id
        //layouts
        layoutMenu = findViewById(R.id.layoutMenu);
        layoutImageText = findViewById(R.id.layoutImageText);
        layoutSettings = findViewById(R.id.layoutSettings);
        imageArea = findViewById(R.id.imageArea);

        //menubar
        title = findViewById(R.id.title);
        naviconButton = findViewById(R.id.naviconButton);

        //content
        button1 = findViewById(R.id.Button1);
        button2 = findViewById(R.id.Button2);
        button3 = findViewById(R.id.Button3);
        button4 = findViewById(R.id.Button4);
        button5 = findViewById(R.id.Button5);
        button6 = findViewById(R.id.Button6);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        stepView = findViewById(R.id.stepView);
        textSizeTextView = findViewById(R.id.textSizeTextView);
        spinner = findViewById(R.id.spinner);
        darkModeSwitch = findViewById(R.id.darkModeSwitch);
        updateButton = findViewById(R.id.updateButton);
        //scrollable
        scrollView = findViewById(R.id.scrollView);

        //textView.setMovementMethod(new ScrollingMovementMethod());
        //footer
        leftArrow = findViewById(R.id.leftArrow);
        rightArrow = findViewById(R.id.rightArrow);
        //endregion

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        storageRef = FirebaseStorage.getInstance().getReference();
        addFileNames();

        naviconButton.setOnClickListener(this::setupPopupMenu);
        setupSpinner(sharedPreferences);
        updateButton.setOnClickListener(v -> update());

        //start program
        setupAppFromSharedprefs(sharedPreferences);
        setupDarkModeSwitch(sharedPreferences);

        // to refresh asset files
//        for (String imageFileName : imageFileNames) useAssetFile(imageFileDir, imageFileName);
//        for (String textFileName : textFileNames) useAssetFile(textFileDir, textFileName);

        // nämä StepView arvot pitäisi muuttua puhelimen näytön koon mukaan
        // stepview näytti skaalautuvan ihan hyvin, joten tää ei oo välttämättä tarpeellinen
//        stepView.doneCircleRadius = 40;
//        stepView.selectedCircleRadius = 40;
//        stepView.stepNumberTextSize = 40;
//        stepView.stepLineWidth = 8;
    }

    //region functions
    @SuppressLint("NonConstantResourceId")
    public void setupPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.popupMenuItem1:
                    asetukset(v);
                    return true;
                case R.id.popupMenuItem2:
                    tietoaSovelluksesta(v);
                    return true;
                default:
                    return false;
            }
        });
    }

    public void setupSpinner(SharedPreferences sharedPrefs) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.textSizesSpinnerValues, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        SharedPreferences.Editor editor = sharedPrefs.edit();

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    editor.putString("textSize", "small");
                    textView.setTextSize(14);
                    textSizeTextView.setTextSize(14);
                    darkModeSwitch.setTextSize(14);
                } else if (position == 1) {
                    editor.putString("textSize", "normal");
                    textView.setTextSize(22);
                    textSizeTextView.setTextSize(22);
                    darkModeSwitch.setTextSize(22);
                } else if (position == 2) {
                    editor.putString("textSize", "big");
                    textSizeTextView.setTextSize(30);
                    darkModeSwitch.setTextSize(30);
                }
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void setupAppFromSharedprefs(SharedPreferences sharedPrefs) {
        boolean isFirstLaunch = sharedPrefs.getBoolean("isFirstLaunch", true);
        boolean themeChanged = sharedPrefs.getBoolean("themeChanged", false);
        boolean darkMode = sharedPrefs.getBoolean("isDarkModeOn", false);
        String textSize = sharedPrefs.getString("textSize", "normal");

        SharedPreferences.Editor editor = sharedPrefs.edit();

        if(isFirstLaunch) {
            for (String imageFileName : imageFileNames) useAssetFile(imageFileDir, imageFileName);
            for (String textFileName : textFileNames) useAssetFile(textFileDir, textFileName);
            if (isNetworkAvailable()) update();
            editor.putBoolean("isFirstLaunch", false);
            editor.apply();
        }

        if(themeChanged){
            asetukset();
            editor.putBoolean("themeChanged", false);
            editor.apply();
        }

        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            darkModeSwitch.setChecked(true);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        switch (textSize) {
            case "small":
                spinner.setSelection(0);
                break;
            case "normal":
                spinner.setSelection(1);
                break;
            case "big":
                spinner.setSelection(2);
                break;
        }
    }

    public void setupDarkModeSwitch(SharedPreferences sharedPrefs) {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                editor.putBoolean("isDarkModeOn", true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                editor.putBoolean("isDarkModeOn", false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            editor.putBoolean("themeChanged", true);
            editor.apply();
        });
    }

    //region reading and updating config file
    /*public boolean checkFromConfigFile(String section, String value) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(configFile));
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.contains(section) && line.endsWith(value)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }*/

    /*public void updateConfigFile(String section, String value) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(configFile));
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.contains(section)) {
                    line = section + " = " + value;
                }
                stringBuilder.append(line).append('\n');
            }

            FileWriter writer = new FileWriter(configFile);
            writer.write(String.valueOf(stringBuilder));

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    //endregion

    public void addFileNames() {
        imageFileNames.add("ohje.jpg");
        imageFileNames.add("ohje3.jpg");
        imageFileNames.add("ohje4.jpg");
        imageFileNames.add("ohje5.jpg");
        imageFileNames.add("ohje7.jpg");

        textFileNames.add("hartiadystokiaSivu1.txt");
        textFileNames.add("hartiadystokiaSivu2.txt");
        textFileNames.add("hartiadystokiaSivu3.txt");
        textFileNames.add("hartiadystokiaSivu4.txt");
        textFileNames.add("hartiadystokiaSivu5.txt");
        textFileNames.add("napanuoraSivu1.txt");
        textFileNames.add("napanuoraSivu2.txt");
        textFileNames.add("napanuoraSivu3.txt");
        textFileNames.add("peratilaSivu1.txt");
        textFileNames.add("peratilaSivu2.txt");
        textFileNames.add("peratilaSivu3.txt");
        textFileNames.add("peratilaSivu4.txt");
        textFileNames.add("peratilaSivu5.txt");
        textFileNames.add("synnytyksenAikanaSivu1.txt");
        textFileNames.add("synnytyksenAikanaSivu2.txt");
        textFileNames.add("synnytyksenAikanaSivu3.txt");
        textFileNames.add("synnytyksenAikanaSivu4.txt");
        textFileNames.add("synnytyksenAikanaSivu5.txt");
        textFileNames.add("synnytyksenAikanaSivu6.txt");
        textFileNames.add("synnytyksenJalkeenSivu1.txt");
        textFileNames.add("synnytyksenJalkeenSivu2.txt");
        textFileNames.add("synnytyksenJalkeenSivu3.txt");
        textFileNames.add("synnytyksenJalkeenSivu4.txt");
        textFileNames.add("synnytyksenJalkeenSivu5.txt");
        textFileNames.add("tietoaSovelluksesta.txt");
        textFileNames.add("valmistautuminenSivu1.txt");
        textFileNames.add("valmistautuminenSivu2.txt");
        textFileNames.add("valmistautuminenSivu3.txt");
        textFileNames.add("valmistautuminenSivu4.txt");
        textFileNames.add("valmistautuminenSivu5.txt");
        textFileNames.add("valmistautuminenSivu6.txt");

        imageRefs.add(storageRef.child("kuvat/ohje.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje3.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje4.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje5.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje7.jpg"));

        textRefs.add(storageRef.child("tekstit/hartiadystokiaSivu1.txt"));
        textRefs.add(storageRef.child("tekstit/hartiadystokiaSivu2.txt"));
        textRefs.add(storageRef.child("tekstit/hartiadystokiaSivu3.txt"));
        textRefs.add(storageRef.child("tekstit/hartiadystokiaSivu4.txt"));
        textRefs.add(storageRef.child("tekstit/hartiadystokiaSivu5.txt"));
        textRefs.add(storageRef.child("tekstit/napanuoraSivu1.txt"));
        textRefs.add(storageRef.child("tekstit/napanuoraSivu2.txt"));
        textRefs.add(storageRef.child("tekstit/napanuoraSivu3.txt"));
        textRefs.add(storageRef.child("tekstit/peratilaSivu1.txt"));
        textRefs.add(storageRef.child("tekstit/peratilaSivu2.txt"));
        textRefs.add(storageRef.child("tekstit/peratilaSivu3.txt"));
        textRefs.add(storageRef.child("tekstit/peratilaSivu4.txt"));
        textRefs.add(storageRef.child("tekstit/peratilaSivu5.txt"));
        textRefs.add(storageRef.child("tekstit/synnytyksenAikanaSivu1.txt"));
        textRefs.add(storageRef.child("tekstit/synnytyksenAikanaSivu2.txt"));
        textRefs.add(storageRef.child("tekstit/synnytyksenAikanaSivu3.txt"));
        textRefs.add(storageRef.child("tekstit/synnytyksenAikanaSivu4.txt"));
        textRefs.add(storageRef.child("tekstit/synnytyksenAikanaSivu5.txt"));
        textRefs.add(storageRef.child("tekstit/synnytyksenAikanaSivu6.txt"));
        textRefs.add(storageRef.child("tekstit/synnytyksenJalkeenSivu1.txt"));
        textRefs.add(storageRef.child("tekstit/synnytyksenJalkeenSivu2.txt"));
        textRefs.add(storageRef.child("tekstit/synnytyksenJalkeenSivu3.txt"));
        textRefs.add(storageRef.child("tekstit/synnytyksenJalkeenSivu4.txt"));
        textRefs.add(storageRef.child("tekstit/synnytyksenJalkeenSivu5.txt"));
        textRefs.add(storageRef.child("tekstit/tietoaSovelluksesta.txt"));
        textRefs.add(storageRef.child("tekstit/valmistautuminenSivu1.txt"));
        textRefs.add(storageRef.child("tekstit/valmistautuminenSivu2.txt"));
        textRefs.add(storageRef.child("tekstit/valmistautuminenSivu3.txt"));
        textRefs.add(storageRef.child("tekstit/valmistautuminenSivu4.txt"));
        textRefs.add(storageRef.child("tekstit/valmistautuminenSivu5.txt"));
        textRefs.add(storageRef.child("tekstit/valmistautuminenSivu6.txt"));
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void useAssetFile(String dir, String fileName) {
        AssetManager assetManager = getAssets();
        try {
            InputStream in = assetManager.open(dir + fileName);

            File outFile = new File(getFilesDir(), fileName);
            FileOutputStream out = new FileOutputStream(outFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }

            in.close();
            out.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void update() {
        fileCounter = 0;

        for(int i=0; i<imageRefs.size(); i++) {
            downloadFileFromFirebase(imageRefs.get(i), getFilesDir(), imageFileNames.get(i));
        }

        for(int i = 0; i< textRefs.size(); i++) {
            downloadFileFromFirebase(textRefs.get(i), getFilesDir(), textFileNames.get(i));
        }
    }

    public void downloadFileFromFirebase(StorageReference ref, File dir, String name) {
        File file = new File(dir, name);

        SharedPreferences sharedPrefs = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        boolean isFirstLaunch = sharedPrefs.getBoolean("isFirstLaunch", true);

        ref.getFile(file).addOnSuccessListener(taskSnapshot -> {
            fileCounter++;
            if(fileCounter == (imageFileNames.size() + textFileNames.size())) {
                Log.d("test", "Updated succesfully!");
                if(!isFirstLaunch) {
                    Toast.makeText(this, "Päivitetty onnistuneesti!", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(exception -> {
            Log.d("test", "Update failed!");
            Toast.makeText(getApplicationContext(), "Päivitys epäonnistui!", Toast.LENGTH_SHORT).show();
        });
    }

    public Bitmap getImage(String fileName) {
        File imageFile = new File(getFilesDir(), fileName);

        if(imageFile.exists()) {
            return BitmapFactory.decodeFile(String.valueOf(imageFile));
        } else {
            Log.d("test", "Error: Image file not found");
            return null;
        }
    }

    public String getText(String fileName) {
        File textFile = new File(getFilesDir(), fileName);

        if(textFile.exists()) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(textFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append('\n');
                }
                reader.close();
            }
            catch (IOException e) {
                Log.d("test", "Error: getText function");
            }

            return stringBuilder.toString();
        } else {
            Log.d("test", "Error: Text file not found");
            return null;
        }
    }

    public void setLayout(String content) {
        switch (content) {
            case "layoutMenu":
                layoutMenu.setVisibility(View.VISIBLE);
                layoutImageText.setVisibility(View.GONE);
                layoutSettings.setVisibility(View.GONE);
                stepView.setVisibility(View.GONE);

                button1.setVisibility(View.INVISIBLE);
                //button2.setVisibility(View.INVISIBLE);
                //button3.setVisibility(View.INVISIBLE);
                //button4.setVisibility(View.INVISIBLE);
                button5.setVisibility(View.INVISIBLE);
                button6.setVisibility(View.INVISIBLE);

                leftArrow.setVisibility(View.VISIBLE);
                rightArrow.setVisibility(View.INVISIBLE);

                leftArrow.setOnClickListener(this::kotisivu);
                break;
            case "layoutImageText":
                scrollView.scrollTo(0, 0);
                layoutMenu.setVisibility(View.GONE);
                layoutImageText.setVisibility(View.VISIBLE);
                layoutSettings.setVisibility(View.GONE);
                stepView.setVisibility(View.VISIBLE);

                leftArrow.setVisibility(View.VISIBLE);
                rightArrow.setVisibility(View.VISIBLE);
                break;
            default:
                layoutMenu.setVisibility(View.GONE);
                layoutImageText.setVisibility(View.GONE);
                layoutSettings.setVisibility(View.GONE);
                stepView.setVisibility(View.GONE);

                leftArrow.setVisibility(View.VISIBLE);
                rightArrow.setVisibility(View.INVISIBLE);

                switch (activityName) {
                    case "kotisivu":
                        leftArrow.setOnClickListener(this::kotisivu);
                        break;
                    case "valmistautuminenSivu":
                        leftArrow.setOnClickListener(this::valmistautuminenSivu);
                        break;
                    case "synnytyksenAikanaSivu":
                        leftArrow.setOnClickListener(this::synnytyksenAikanaSivu);
                        break;
                    case "synnytyksenJalkeenSivu":
                        leftArrow.setOnClickListener(this::synnytyksenJalkeenSivu);
                        break;
                    case "erikoistilanteetSivu":
                        leftArrow.setOnClickListener(this::erikoistilanteetSivu);
                        break;
                    case "peratilaSivu":
                        leftArrow.setOnClickListener(this::peratilaSivu);
                        break;
                    case "hartiadystokiaSivu":
                        leftArrow.setOnClickListener(this::hartiadystokiaSivu);
                        break;
                    case "napanuoraSivu":
                        leftArrow.setOnClickListener(this::napanuoraSivu);
                        break;
                    case "valmistautuminenSivu1":
                        leftArrow.setOnClickListener(this::valmistautuminenSivu1);
                        break;
                    case "valmistautuminenSivu2":
                        leftArrow.setOnClickListener(this::valmistautuminenSivu2);
                        break;
                    case "valmistautuminenSivu3":
                        leftArrow.setOnClickListener(this::valmistautuminenSivu3);
                        break;
                    case "valmistautuminenSivu4":
                        leftArrow.setOnClickListener(this::valmistautuminenSivu4);
                        break;
                    case "valmistautuminenSivu5":
                        leftArrow.setOnClickListener(this::valmistautuminenSivu5);
                        break;
                    case "valmistautuminenSivu6":
                        leftArrow.setOnClickListener(this::valmistautuminenSivu6);
                        break;
                    case "synnytyksenAikanaSivu1":
                        leftArrow.setOnClickListener(this::synnytyksenAikanaSivu1);
                        break;
                    case "synnytyksenAikanaSivu2":
                        leftArrow.setOnClickListener(this::synnytyksenAikanaSivu2);
                        break;
                    case "synnytyksenAikanaSivu3":
                        leftArrow.setOnClickListener(this::synnytyksenAikanaSivu3);
                        break;
                    case "synnytyksenAikanaSivu4":
                        leftArrow.setOnClickListener(this::synnytyksenAikanaSivu4);
                        break;
                    case "synnytyksenAikanaSivu5":
                        leftArrow.setOnClickListener(this::synnytyksenAikanaSivu5);
                        break;
                    case "synnytyksenAikanaSivu6":
                        leftArrow.setOnClickListener(this::synnytyksenAikanaSivu6);
                        break;
                    case "synnytyksenJalkeenSivu1":
                        leftArrow.setOnClickListener(this::synnytyksenJalkeenSivu1);
                        break;
                    case "synnytyksenJalkeenSivu2":
                        leftArrow.setOnClickListener(this::synnytyksenJalkeenSivu2);
                        break;
                    case "synnytyksenJalkeenSivu3":
                        leftArrow.setOnClickListener(this::synnytyksenJalkeenSivu3);
                        break;
                    case "synnytyksenJalkeenSivu4":
                        leftArrow.setOnClickListener(this::synnytyksenJalkeenSivu4);
                        break;
                    case "synnytyksenJalkeenSivu5":
                        leftArrow.setOnClickListener(this::synnytyksenJalkeenSivu5);
                        break;
                    case "peratilaSivu1":
                        leftArrow.setOnClickListener(this::peratilaSivu1);
                        break;
                    case "peratilaSivu2":
                        leftArrow.setOnClickListener(this::peratilaSivu2);
                        break;
                    case "peratilaSivu3":
                        leftArrow.setOnClickListener(this::peratilaSivu3);
                        break;
                    case "peratilaSivu4":
                        leftArrow.setOnClickListener(this::peratilaSivu4);
                        break;
                    case "peratilaSivu5":
                        leftArrow.setOnClickListener(this::peratilaSivu5);
                        break;
                    case "hartiadystokiaSivu1":
                        leftArrow.setOnClickListener(this::hartiadystokiaSivu1);
                        break;
                    case "hartiadystokiaSivu2":
                        leftArrow.setOnClickListener(this::hartiadystokiaSivu2);
                        break;
                    case "hartiadystokiaSivu3":
                        leftArrow.setOnClickListener(this::hartiadystokiaSivu3);
                        break;
                    case "hartiadystokiaSivu4":
                        leftArrow.setOnClickListener(this::hartiadystokiaSivu4);
                        break;
                    case "hartiadystokiaSivu5":
                        leftArrow.setOnClickListener(this::hartiadystokiaSivu5);
                        break;
                    case "napanuoraSivu1":
                        leftArrow.setOnClickListener(this::napanuoraSivu1);
                        break;
                    case "napanuoraSivu2":
                        leftArrow.setOnClickListener(this::napanuoraSivu2);
                        break;
                    case "napanuoraSivu3":
                        leftArrow.setOnClickListener(this::napanuoraSivu3);
                        break;
                }
        }
    }
    //endregion

    //region navicon sivut
    public void asetukset() {
        title.setText(R.string.asetukset);
        setLayout("x");
        activityName = "asetukset";

        layoutSettings.setVisibility(View.VISIBLE);
    }

    public void asetukset(View v) {
        title.setText(R.string.asetukset);
        setLayout("x");
        activityName = "asetukset";

        layoutSettings.setVisibility(View.VISIBLE);
    }

    public void tietoaSovelluksesta(View v) {
        title.setText(R.string.about);
        setLayout("x");
        activityName = "tietoaSovelluksesta";

        imageArea.setVisibility(View.GONE);
        layoutImageText.setVisibility(View.VISIBLE);
        textView.setText(getText("tietoaSovelluksesta.txt"));
    }
    //endregion

    //region menu sivut
    public void kotisivu(View v) {
        activityName = "kotisivu";
        title.setText(R.string.app_name);
        setLayout("layoutMenu");

        button5.setVisibility(View.VISIBLE);

        button2.setText(R.string.kotisivu1);
        button3.setText(R.string.kotisivu2);
        button4.setText(R.string.kotisivu3);
        button5.setText(R.string.kotisivu4);

        leftArrow.setVisibility(View.INVISIBLE);

        button2.setOnClickListener(this::valmistautuminenSivu);
        button3.setOnClickListener(this::synnytyksenAikanaSivu);
        button4.setOnClickListener(this::synnytyksenJalkeenSivu);
        button5.setOnClickListener(this::erikoistilanteetSivu);
    }

    public void valmistautuminenSivu(View v) {
        activityName = "valmistautuminenSivu";
        title.setText(R.string.kotisivu1);
        setLayout("layoutMenu");

        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.VISIBLE);

        button1.setText(R.string.valmistautuminenSivu1);
        button2.setText(R.string.valmistautuminenSivu2);
        button3.setText(R.string.valmistautuminenSivu3);
        button4.setText(R.string.valmistautuminenSivu4);
        button5.setText(R.string.valmistautuminenSivu5);
        button6.setText(R.string.valmistautuminenSivu6);

        button1.setOnClickListener(this::valmistautuminenSivu1);
        button2.setOnClickListener(this::valmistautuminenSivu2);
        button3.setOnClickListener(this::valmistautuminenSivu3);
        button4.setOnClickListener(this::valmistautuminenSivu4);
        button5.setOnClickListener(this::valmistautuminenSivu5);
        button6.setOnClickListener(this::valmistautuminenSivu6);

        stepView.getState().stepsNumber(6).commit();
    }

    public void synnytyksenAikanaSivu(View v) {
        activityName = "synnytyksenAikanaSivu";
        title.setText(R.string.kotisivu2);
        setLayout("layoutMenu");

        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.VISIBLE);

        button1.setText(R.string.synnytyksenAikanaSivu1);
        button2.setText(R.string.synnytyksenAikanaSivu2);
        button3.setText(R.string.synnytyksenAikanaSivu3);
        button4.setText(R.string.synnytyksenAikanaSivu4);
        button5.setText(R.string.synnytyksenAikanaSivu5);
        button6.setText(R.string.synnytyksenAikanaSivu6);

        button1.setOnClickListener(this::synnytyksenAikanaSivu1);
        button2.setOnClickListener(this::synnytyksenAikanaSivu2);
        button3.setOnClickListener(this::synnytyksenAikanaSivu3);
        button4.setOnClickListener(this::synnytyksenAikanaSivu4);
        button5.setOnClickListener(this::synnytyksenAikanaSivu5);
        button6.setOnClickListener(this::synnytyksenAikanaSivu6);

        stepView.getState().stepsNumber(6).commit();
    }

    public void synnytyksenJalkeenSivu(View v) {
        activityName = "synnytyksenJalkeenSivu";
        title.setText(R.string.kotisivu3);
        setLayout("layoutMenu");

        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);

        button1.setText(R.string.synnytyksenJalkeenSivu1);
        button2.setText(R.string.synnytyksenJalkeenSivu2);
        button3.setText(R.string.synnytyksenJalkeenSivu3);
        button4.setText(R.string.synnytyksenJalkeenSivu4);
        button5.setText(R.string.synnytyksenJalkeenSivu5);

        button1.setOnClickListener(this::synnytyksenJalkeenSivu1);
        button2.setOnClickListener(this::synnytyksenJalkeenSivu2);
        button3.setOnClickListener(this::synnytyksenJalkeenSivu3);
        button4.setOnClickListener(this::synnytyksenJalkeenSivu4);
        button5.setOnClickListener(this::synnytyksenJalkeenSivu5);

        stepView.getState().stepsNumber(5).commit();
    }

    public void erikoistilanteetSivu(View v) {
        activityName = "erikoistilanteetSivu";
        title.setText(R.string.kotisivu4);
        setLayout("layoutMenu");

        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);

        button2.setText(R.string.erikoistilanteetSivu1);
        button3.setText(R.string.erikoistilanteetSivu2);
        button4.setText(R.string.erikoistilanteetSivu3);

        button2.setOnClickListener(this::peratilaSivu);
        button3.setOnClickListener(this::hartiadystokiaSivu);
        button4.setOnClickListener(this::napanuoraSivu);
    }

    public void peratilaSivu(View v) {
        activityName = "peratilaSivu";
        title.setText(R.string.erikoistilanteetSivu1);
        setLayout("layoutMenu");

        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);

        button1.setText(R.string.peratilaSivu1);
        button2.setText(R.string.peratilaSivu2);
        button3.setText(R.string.peratilaSivu3);
        button4.setText(R.string.peratilaSivu4);
        button5.setText(R.string.peratilaSivu5);

        leftArrow.setOnClickListener(this::erikoistilanteetSivu);
        button1.setOnClickListener(this::peratilaSivu1);
        button2.setOnClickListener(this::peratilaSivu2);
        button3.setOnClickListener(this::peratilaSivu3);
        button4.setOnClickListener(this::peratilaSivu4);
        button5.setOnClickListener(this::peratilaSivu5);

        stepView.getState().stepsNumber(5).commit();
    }

    public void hartiadystokiaSivu(View v) {
        activityName = "hartiadystokiaSivu";
        title.setText(R.string.erikoistilanteetSivu2);
        setLayout("layoutMenu");

        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);

        button1.setText(R.string.hartiadystokiaSivu1);
        button2.setText(R.string.hartiadystokiaSivu2);
        button3.setText(R.string.hartiadystokiaSivu3);
        button4.setText(R.string.hartiadystokiaSivu4);
        button5.setText(R.string.hartiadystokiaSivu5);

        leftArrow.setOnClickListener(this::erikoistilanteetSivu);
        button1.setOnClickListener(this::hartiadystokiaSivu1);
        button2.setOnClickListener(this::hartiadystokiaSivu2);
        button3.setOnClickListener(this::hartiadystokiaSivu3);
        button4.setOnClickListener(this::hartiadystokiaSivu4);
        button5.setOnClickListener(this::hartiadystokiaSivu5);

        stepView.getState().stepsNumber(5).commit();
    }

    public void napanuoraSivu(View v) {
        activityName = "napanuoraSivu";
        title.setText(R.string.erikoistilanteetSivu3);
        setLayout("layoutMenu");

        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);


        button2.setText(R.string.napanuoraSivu1);
        button3.setText(R.string.napanuoraSivu2);
        button4.setText(R.string.napanuoraSivu3);


        leftArrow.setOnClickListener(this::erikoistilanteetSivu);
        button2.setOnClickListener(this::napanuoraSivu1);
        button3.setOnClickListener(this::napanuoraSivu2);
        button4.setOnClickListener(this::napanuoraSivu3);


        stepView.getState().stepsNumber(3).commit();
    }
    //endregion

    //region siirtymä sivut
    public void valmistautuminenSivu1(View v) {
        title.setText(R.string.valmistautuminenSivu1Title);
        activityName = "valmistautuminenSivu1";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::valmistautuminenSivu);
        rightArrow.setOnClickListener(this::valmistautuminenSivu2);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("valmistautuminenSivu1.txt"));
        stepView.go(0, false);
    }

    public void valmistautuminenSivu2(View v) {
        title.setText(R.string.valmistautuminenSivu2Title);
        activityName = "valmistautuminenSivu2";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::valmistautuminenSivu1);
        rightArrow.setOnClickListener(this::valmistautuminenSivu3);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("valmistautuminenSivu2.txt"));
        stepView.go(1, false);
    }

    public void valmistautuminenSivu3(View v) {
        title.setText(R.string.valmistautuminenSivu3Title);
        activityName = "valmistautuminenSivu3";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::valmistautuminenSivu2);
        rightArrow.setOnClickListener(this::valmistautuminenSivu4);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("valmistautuminenSivu3.txt"));
        stepView.go(2, false);
    }

    public void valmistautuminenSivu4(View v) {
        title.setText(R.string.valmistautuminenSivu4Title);
        activityName = "valmistautuminenSivu4";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::valmistautuminenSivu3);
        rightArrow.setOnClickListener(this::valmistautuminenSivu5);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("valmistautuminenSivu4.txt"));
        stepView.go(3, false);
    }

    public void valmistautuminenSivu5(View v) {
        title.setText(R.string.valmistautuminenSivu5Title);
        activityName = "valmistautuminenSivu5";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::valmistautuminenSivu4);
        rightArrow.setOnClickListener(this::valmistautuminenSivu6);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("valmistautuminenSivu5.txt"));
        stepView.go(4, false);
    }

    public void valmistautuminenSivu6(View v) {
        title.setText(R.string.valmistautuminenSivu6Title);
        activityName = "valmistautuminenSivu6";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::valmistautuminenSivu5);
        rightArrow.setOnClickListener(this::kotisivu);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("valmistautuminenSivu6.txt"));
        stepView.go(5, false);
    }

    public void synnytyksenAikanaSivu1(View v) {
        title.setText(R.string.synnytyksenAikanaSivu1Title);
        activityName = "synnytyksenAikanaSivu1";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::synnytyksenAikanaSivu);
        rightArrow.setOnClickListener(this::synnytyksenAikanaSivu2);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("synnytyksenAikanaSivu1.txt"));
        stepView.go(0, false);
    }

    public void synnytyksenAikanaSivu2(View v) {
        title.setText(R.string.synnytyksenAikanaSivu2Title);
        activityName = "synnytyksenAikanaSivu2";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::synnytyksenAikanaSivu1);
        rightArrow.setOnClickListener(this::synnytyksenAikanaSivu3);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje3.jpg"));
        textView.setText(getText("synnytyksenAikanaSivu2.txt"));
        stepView.go(1, false);
    }

    public void synnytyksenAikanaSivu3(View v) {
        title.setText(R.string.synnytyksenAikanaSivu3Title);
        activityName = "synnytyksenAikanaSivu3";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::synnytyksenAikanaSivu2);
        rightArrow.setOnClickListener(this::synnytyksenAikanaSivu4);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("synnytyksenAikanaSivu3.txt"));
        stepView.go(2, false);
    }

    public void synnytyksenAikanaSivu4(View v) {
        title.setText(R.string.synnytyksenAikanaSivu4Title);
        activityName = "synnytyksenAikanaSivu4";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::synnytyksenAikanaSivu3);
        rightArrow.setOnClickListener(this::synnytyksenAikanaSivu5);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("synnytyksenAikanaSivu4.txt"));
        stepView.go(3, false);
    }

    public void synnytyksenAikanaSivu5(View v) {
        title.setText(R.string.synnytyksenAikanaSivu5Title);
        activityName = "synnytyksenAikanaSivu5";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::synnytyksenAikanaSivu4);
        rightArrow.setOnClickListener(this::synnytyksenAikanaSivu6);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("synnytyksenAikanaSivu5.txt"));
        stepView.go(4, false);
    }

    public void synnytyksenAikanaSivu6(View v) {
        title.setText(R.string.synnytyksenAikanaSivu6Title);
        activityName = "synnytyksenAikanaSivu6";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::synnytyksenAikanaSivu5);
        rightArrow.setOnClickListener(this::kotisivu);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("synnytyksenAikanaSivu6.txt"));
        stepView.go(5, false);
    }

    public void synnytyksenJalkeenSivu1(View v) {
        title.setText(R.string.synnytyksenJalkeenSivu1Title);
        activityName = "synnytyksenJalkeenSivu1";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::synnytyksenJalkeenSivu);
        rightArrow.setOnClickListener(this::synnytyksenJalkeenSivu2);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("synnytyksenJalkeenSivu1.txt"));
        stepView.go(0, false);
    }

    public void synnytyksenJalkeenSivu2(View v) {
        title.setText(R.string.synnytyksenJalkeenSivu2Title);
        activityName = "synnytyksenJalkeenSivu2";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::synnytyksenJalkeenSivu1);
        rightArrow.setOnClickListener(this::synnytyksenJalkeenSivu3);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("synnytyksenJalkeenSivu2.txt"));
        stepView.go(1, false);
    }

    public void synnytyksenJalkeenSivu3(View v) {
        title.setText(R.string.synnytyksenJalkeenSivu3Title);
        activityName = "synnytyksenJalkeenSivu3";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::synnytyksenJalkeenSivu2);
        rightArrow.setOnClickListener(this::synnytyksenJalkeenSivu4);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("synnytyksenJalkeenSivu3.txt"));
        stepView.go(2, false);
    }

    public void synnytyksenJalkeenSivu4(View v) {
        title.setText(R.string.synnytyksenJalkeenSivu4Title);
        activityName = "synnytyksenJalkeenSivu4";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::synnytyksenJalkeenSivu3);
        rightArrow.setOnClickListener(this::synnytyksenJalkeenSivu5);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("synnytyksenJalkeenSivu4.txt"));
        stepView.go(3, false);
    }

    public void synnytyksenJalkeenSivu5(View v) {
        title.setText(R.string.synnytyksenJalkeenSivu5Title);
        activityName = "synnytyksenJalkeenSivu5";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::synnytyksenJalkeenSivu4);
        rightArrow.setOnClickListener(this::kotisivu);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("synnytyksenJalkeenSivu5.txt"));
        stepView.go(4, false);
    }

    public void peratilaSivu1(View v) {
        title.setText(R.string.peratilaSivu1Title);
        activityName = "peratilaSivu1";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::peratilaSivu);
        rightArrow.setOnClickListener(this::peratilaSivu2);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("peratilaSivu1.txt"));
        stepView.go(0, false);
    }

    public void peratilaSivu2(View v) {
        title.setText(R.string.peratilaSivu2Title);
        activityName = "peratilaSivu2";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::peratilaSivu1);
        rightArrow.setOnClickListener(this::peratilaSivu3);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("peratilaSivu2.txt"));
        stepView.go(1, false);
    }

    public void peratilaSivu3(View v) {
        title.setText(R.string.peratilaSivu3Title);
        activityName = "peratilaSivu3";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::peratilaSivu2);
        rightArrow.setOnClickListener(this::peratilaSivu4);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("peratilaSivu3.txt"));
        stepView.go(2, false);
    }

    public void peratilaSivu4(View v) {
        title.setText(R.string.peratilaSivu4Title);
        activityName = "peratilaSivu4";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::peratilaSivu3);
        rightArrow.setOnClickListener(this::peratilaSivu5);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("peratilaSivu4.txt"));
        stepView.go(3, false);
    }

    public void peratilaSivu5(View v) {
        title.setText(R.string.peratilaSivu5Title);
        activityName = "peratilaSivu5";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::peratilaSivu4);
        rightArrow.setOnClickListener(this::erikoistilanteetSivu);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("peratilaSivu5.txt"));
        stepView.go(4, false);
    }

    public void hartiadystokiaSivu1(View v) {
        title.setText(R.string.hartiadystokiaSivu1Title);
        activityName = "hartiadystokiaSivu1";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::hartiadystokiaSivu);
        rightArrow.setOnClickListener(this::hartiadystokiaSivu2);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("hartiadystokiaSivu1.txt"));
        stepView.go(0, false);
    }

    public void hartiadystokiaSivu2(View v) {
        title.setText(R.string.hartiadystokiaSivu2Title);
        activityName = "hartiadystokiaSivu2";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::hartiadystokiaSivu1);
        rightArrow.setOnClickListener(this::hartiadystokiaSivu3);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("hartiadystokiaSivu2.txt"));
        stepView.go(1, false);
    }

    public void hartiadystokiaSivu3(View v) {
        title.setText(R.string.hartiadystokiaSivu3Title);
        activityName = "hartiadystokiaSivu3";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::hartiadystokiaSivu2);
        rightArrow.setOnClickListener(this::hartiadystokiaSivu4);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("hartiadystokiaSivu3.txt"));
        stepView.go(2, false);
    }

    public void hartiadystokiaSivu4(View v) {
        title.setText(R.string.hartiadystokiaSivu4Title);
        activityName = "hartiadystokiaSivu4";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::hartiadystokiaSivu3);
        rightArrow.setOnClickListener(this::hartiadystokiaSivu5);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("hartiadystokiaSivu4.txt"));
        stepView.go(3, false);
    }

    public void hartiadystokiaSivu5(View v) {
        title.setText(R.string.hartiadystokiaSivu5Title);
        activityName = "hartiadystokiaSivu5";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::hartiadystokiaSivu4);
        rightArrow.setOnClickListener(this::erikoistilanteetSivu);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("hartiadystokiaSivu5.txt"));
        stepView.go(4, false);
    }

    public void napanuoraSivu1(View v) {
        title.setText(R.string.napanuoraSivu1Title);
        activityName = "napanuoraSivu1";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::napanuoraSivu);
        rightArrow.setOnClickListener(this::napanuoraSivu2);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("napanuoraSivu1.txt"));
        stepView.go(0, false);
    }

    public void napanuoraSivu2(View v) {
        title.setText(R.string.napanuoraSivu2Title);
        activityName = "napanuoraSivu2";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::napanuoraSivu1);
        rightArrow.setOnClickListener(this::napanuoraSivu3);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("napanuoraSivu2.txt"));
        stepView.go(1, false);
    }

    public void napanuoraSivu3(View v) {
        title.setText(R.string.napanuoraSivu3Title);
        activityName = "napanuoraSivu3";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::napanuoraSivu2);
        rightArrow.setOnClickListener(this::erikoistilanteetSivu);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("napanuoraSivu3.txt"));
        stepView.go(2, false);
    }
    //endregion
}