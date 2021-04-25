package com.example.ambuapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.PopupMenu;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    FirebaseAuth mAuth;
    StorageReference storageRef;
    ArrayList<String> imageFileNames = new ArrayList<>();
    ArrayList<String> textFileNames = new ArrayList<>();
    ArrayList<StorageReference> imageRefs = new ArrayList<>();
    ArrayList<StorageReference> textRefs = new ArrayList<>();

    SharedPreferences sharedPreferences;
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
        layoutMenu = findViewById(R.id.layoutMenu);
        layoutImageText = findViewById(R.id.layoutImageText);
        layoutSettings = findViewById(R.id.layoutSettings);
        imageArea = findViewById(R.id.imageArea);

        title = findViewById(R.id.title);
        naviconButton = findViewById(R.id.naviconButton);
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
        scrollView = findViewById(R.id.scrollView);
        leftArrow = findViewById(R.id.leftArrow);
        rightArrow = findViewById(R.id.rightArrow);
        //endregion

        sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        storageRef = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        addFileNames();

        naviconButton.setOnClickListener(this::setupPopupMenu);
        setupSpinner();
        updateButton.setOnClickListener(v -> update());

        //start program
        setupAppFromSharedprefs();
        setupDarkModeSwitch();
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

    public void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.textSizesSpinnerValues, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if(position == 0) {
                    editor.putString("textSize", "small");
                    textView.setTextSize(14);
                    textSizeTextView.setTextSize(14);
                    darkModeSwitch.setTextSize(14);
                } else if (position == 1) {
                    editor.putString("textSize", "normal");
                    textView.setTextSize(20);
                    textSizeTextView.setTextSize(20);
                    darkModeSwitch.setTextSize(20);
                } else if (position == 2) {
                    editor.putString("textSize", "big");
                    textView.setTextSize(30);
                    textSizeTextView.setTextSize(30);
                    darkModeSwitch.setTextSize(30);
                }
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public void setupAppFromSharedprefs() {
        boolean isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true);
        boolean themeChanged = sharedPreferences.getBoolean("themeChanged", false);
        boolean darkMode = sharedPreferences.getBoolean("isDarkModeOn", false);
        String textSize = sharedPreferences.getString("textSize", "normal");

        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(isFirstLaunch) {
            int phoneTheme = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            if (phoneTheme == Configuration.UI_MODE_NIGHT_YES) darkMode = true;

            for (String imageFileName : imageFileNames) useAssetFile(imageFileDir, imageFileName);
            for (String textFileName : textFileNames) useAssetFile(textFileDir, textFileName);
            if (isNetworkAvailable()) {
                authenticate();
                update();
            }
            Log.d("test", "firstlaunch");
            AlertDialog.Builder disclaimer = new AlertDialog.Builder(this);
            disclaimer.setTitle("Vastuuvapauslauseke")
                    .setMessage("Sovelluksen tekijät eivät ole vastuussa mistään vahingoista " +
                            "joita ilmenee sovelluksen käytöstä tai kyvyttymyydestä käyttää " +
                            "sovellusta ja materiaaleja joita se sisältää, eivätkä mistään " +
                            "toimenpiteestä tai päätöksestä jotka on tehty sovelluksen käytön seurauksena. " +
                            "\n\nSovelluksen tekijät eivät ole vastuussa sovelluksen materiaalin sisällöstä " +
                            "eivätkä sen tarkuudesta. Jatkamalla hyväksyt käyttämään tietoa omalla vastuulla.")
                    .setPositiveButton("ok", (dialog, which) -> {});
            disclaimer.show();

            //for (String imageFileName : imageFileNames) useAssetFile(imageFileDir, imageFileName);
           // for (String textFileName : textFileNames) useAssetFile(textFileDir, textFileName);
            if (isNetworkAvailable()) {
                authenticate();
                update();
            }

            editor.putBoolean("isFirstLaunch", false);
            editor.apply();
        }

        if(themeChanged){
            activityName = sharedPreferences.getString("activityToReturn", "kotisivu");
            asetukset();
            editor.putBoolean("themeChanged", false);
            editor.apply();
        }

        if (darkMode) {
            editor.putBoolean("isDarkModeOn", true);
            editor.apply();
            darkModeSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        switch (textSize) {
            case "small":
                spinner.setSelection(0);
                textView.setTextSize(14);
                textSizeTextView.setTextSize(14);
                darkModeSwitch.setTextSize(14);
                break;
            case "normal":
                spinner.setSelection(1);
                textView.setTextSize(20);
                textSizeTextView.setTextSize(20);
                darkModeSwitch.setTextSize(20);
                break;
            case "big":
                spinner.setSelection(2);
                textView.setTextSize(30);
                textSizeTextView.setTextSize(30);
                darkModeSwitch.setTextSize(30);
                break;
        }
    }

    public void setupDarkModeSwitch() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                editor.putBoolean("isDarkModeOn", true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                editor.putBoolean("isDarkModeOn", false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            editor.putBoolean("themeChanged", true);
            editor.putString("activityToReturn", activityName);
            editor.apply();
        });
    }

    public void addFileNames() {
        imageFileNames.add("ohje.jpg");
        imageFileNames.add("ohje3.jpg");
        imageFileNames.add("ohje4.jpg");
        imageFileNames.add("ohje5.jpg");
        imageFileNames.add("ohje7.jpg");

        textFileNames.add("hartiadystokiaSivu1.txt");
        textFileNames.add("hartiadystokiaSivu2.txt");
        textFileNames.add("hartiadystokiaSivu3.txt");
        textFileNames.add("laakeohjeetSivu.txt");
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
        textFileNames.add("tietoaSovelluksesta.txt");
        textFileNames.add("valmistautuminenSivu1.txt");
        textFileNames.add("valmistautuminenSivu2.txt");
        textFileNames.add("valmistautuminenSivu3.txt");
        textFileNames.add("valmistautuminenSivu4.txt");
        textFileNames.add("valmistautuminenSivu5.txt");

        imageRefs.add(storageRef.child("kuvat/ohje.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje3.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje4.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje5.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje7.jpg"));

        textRefs.add(storageRef.child("tekstit/hartiadystokiaSivu1.txt"));
        textRefs.add(storageRef.child("tekstit/hartiadystokiaSivu2.txt"));
        textRefs.add(storageRef.child("tekstit/hartiadystokiaSivu3.txt"));
        textRefs.add(storageRef.child("tekstit/laakeohjeetSivu.txt"));
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
        textRefs.add(storageRef.child("tekstit/tietoaSovelluksesta.txt"));
        textRefs.add(storageRef.child("tekstit/valmistautuminenSivu1.txt"));
        textRefs.add(storageRef.child("tekstit/valmistautuminenSivu2.txt"));
        textRefs.add(storageRef.child("tekstit/valmistautuminenSivu3.txt"));
        textRefs.add(storageRef.child("tekstit/valmistautuminenSivu4.txt"));
        textRefs.add(storageRef.child("tekstit/valmistautuminenSivu5.txt"));
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

    public void authenticate(){
        mAuth.signInAnonymously().addOnCompleteListener(this, task -> {
            if (task.isSuccessful()){
                FirebaseUser user = mAuth.getCurrentUser();
                Log.d("test", String.valueOf(user));
            }
        });
    }
    public void update() {
        authenticate();
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
                layoutImageText.setVisibility(View.INVISIBLE);
                layoutSettings.setVisibility(View.INVISIBLE);
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
                layoutMenu.setVisibility(View.INVISIBLE);
                layoutImageText.setVisibility(View.VISIBLE);
                layoutSettings.setVisibility(View.INVISIBLE);
                stepView.setVisibility(View.VISIBLE);

                leftArrow.setVisibility(View.VISIBLE);
                rightArrow.setVisibility(View.VISIBLE);
                break;
            default:
                layoutMenu.setVisibility(View.INVISIBLE);
                layoutImageText.setVisibility(View.INVISIBLE);
                layoutSettings.setVisibility(View.INVISIBLE);
                stepView.setVisibility(View.GONE);

                leftArrow.setVisibility(View.VISIBLE);
                rightArrow.setVisibility(View.INVISIBLE);

            switch (activityName) {
                case "kotisivu":
                    leftArrow.setOnClickListener(this::kotisivu);
                    break;
                case "erikoistilanteetSivu":
                    leftArrow.setOnClickListener(this::erikoistilanteetSivu);
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
                    leftArrow.setOnClickListener(this::synnytyksenJalkeenSivu3);
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
                    leftArrow.setOnClickListener(this::peratilaSivu4);
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
                case "napanuoraSivu1":
                    leftArrow.setOnClickListener(this::napanuoraSivu1);
                    break;
                case "napanuoraSivu2":
                    leftArrow.setOnClickListener(this::napanuoraSivu2);
                    break;
                case "napanuoraSivu3":
                    leftArrow.setOnClickListener(this::napanuoraSivu3);
                    break;
                case "laakeohjeetSivu":
                    leftArrow.setOnClickListener(this::laakeohjeetSivu);
                default:
                    leftArrow.setOnClickListener(this::kotisivu);
            }
        }
    }
    //endregion

    //region navicon sivut
    public void asetukset() {
        title.setText(R.string.asetukset);
        setLayout("x");
        layoutSettings.setVisibility(View.VISIBLE);
    }

    public void asetukset(View v) {
        title.setText(R.string.asetukset);
        setLayout("x");
        layoutSettings.setVisibility(View.VISIBLE);
    }

    public void tietoaSovelluksesta(View v) {
        title.setText(R.string.about);
        setLayout("x");
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

        button6.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);

        button2.setText(R.string.kotisivu1);
        button3.setText(R.string.kotisivu2);
        button4.setText(R.string.kotisivu3);
        button5.setText(R.string.kotisivu4);
        button6.setText(R.string.kotisivu5);

        leftArrow.setVisibility(View.INVISIBLE);

        button2.setOnClickListener(this::valmistautuminenSivu1);
        button3.setOnClickListener(this::synnytyksenAikanaSivu1);
        button4.setOnClickListener(this::synnytyksenJalkeenSivu1);
        button5.setOnClickListener(this::erikoistilanteetSivu);
        button6.setOnClickListener(this::laakeohjeetSivu);
    }

    public void erikoistilanteetSivu(View v) {
        activityName = "erikoistilanteetSivu";
        title.setText(R.string.kotisivu4);
        setLayout("layoutMenu");

        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button6.setVisibility(View.INVISIBLE);

        button2.setText(R.string.erikoistilanteetSivu1);
        button3.setText(R.string.erikoistilanteetSivu2);
        button4.setText(R.string.erikoistilanteetSivu3);

        button2.setOnClickListener(this::peratilaSivu1);
        button3.setOnClickListener(this::hartiadystokiaSivu1);
        button4.setOnClickListener(this::napanuoraSivu1);
    }
    //endregion

    //region siirtymä sivut
    public void valmistautuminenSivu1(View v) {
        title.setText(R.string.valmistautuminenSivuTitle);
        activityName = "valmistautuminenSivu1";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::kotisivu);
        rightArrow.setOnClickListener(this::valmistautuminenSivu2);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("valmistautuminenSivu1.txt"));
        stepView.getState().stepsNumber(5).commit();
        stepView.go(0, false);
    }

    public void valmistautuminenSivu2(View v) {
        title.setText(R.string.valmistautuminenSivuTitle);
        activityName = "valmistautuminenSivu2";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::valmistautuminenSivu1);
        rightArrow.setOnClickListener(this::valmistautuminenSivu3);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("valmistautuminenSivu2.txt"));
        stepView.getState().stepsNumber(5).commit();
        stepView.go(1, false);
    }

    public void valmistautuminenSivu3(View v) {
        title.setText(R.string.valmistautuminenSivuTitle);
        activityName = "valmistautuminenSivu3";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::valmistautuminenSivu2);
        rightArrow.setOnClickListener(this::valmistautuminenSivu4);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("valmistautuminenSivu3.txt"));
        stepView.getState().stepsNumber(5).commit();
        stepView.go(2, false);
    }

    public void valmistautuminenSivu4(View v) {
        title.setText(R.string.valmistautuminenSivuTitle);
        activityName = "valmistautuminenSivu4";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::valmistautuminenSivu3);
        rightArrow.setOnClickListener(this::valmistautuminenSivu5);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("valmistautuminenSivu4.txt"));
        stepView.getState().stepsNumber(5).commit();
        stepView.go(3, false);
    }

    public void valmistautuminenSivu5(View v) {
        title.setText(R.string.valmistautuminenSivuTitle);
        activityName = "valmistautuminenSivu5";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::valmistautuminenSivu4);
        rightArrow.setOnClickListener(this::kotisivu);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("valmistautuminenSivu5.txt"));
        stepView.getState().stepsNumber(5).commit();
        stepView.go(4, false);
    }


    public void synnytyksenAikanaSivu1(View v) {
        title.setText(R.string.synnytyksenAikanaSivuTitle);
        activityName = "synnytyksenAikanaSivu1";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::kotisivu);
        rightArrow.setOnClickListener(this::synnytyksenAikanaSivu2);

        imageArea.setVisibility(View.GONE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("synnytyksenAikanaSivu1.txt"));
        stepView.getState().stepsNumber(6).commit();
        stepView.go(0, false);
    }

    public void synnytyksenAikanaSivu2(View v) {
        title.setText(R.string.synnytyksenAikanaSivuTitle);
        activityName = "synnytyksenAikanaSivu2";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::synnytyksenAikanaSivu1);
        rightArrow.setOnClickListener(this::synnytyksenAikanaSivu3);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje5.jpg"));
        textView.setText(getText("synnytyksenAikanaSivu2.txt"));
        stepView.getState().stepsNumber(6).commit();
        stepView.go(1, false);
    }

    public void synnytyksenAikanaSivu3(View v) {
        title.setText(R.string.synnytyksenAikanaSivuTitle);
        activityName = "synnytyksenAikanaSivu3";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::synnytyksenAikanaSivu2);
        rightArrow.setOnClickListener(this::synnytyksenAikanaSivu4);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje3.jpg"));
        textView.setText(getText("synnytyksenAikanaSivu3.txt"));
        stepView.getState().stepsNumber(6).commit();
        stepView.go(2, false);
    }

    public void synnytyksenAikanaSivu4(View v) {
        title.setText(R.string.synnytyksenAikanaSivuTitle);
        activityName = "synnytyksenAikanaSivu4";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::synnytyksenAikanaSivu3);
        rightArrow.setOnClickListener(this::synnytyksenAikanaSivu5);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje3.jpg"));
        textView.setText(getText("synnytyksenAikanaSivu4.txt"));
        stepView.getState().stepsNumber(6).commit();
        stepView.go(3, false);
    }

    public void synnytyksenAikanaSivu5(View v) {
        title.setText(R.string.synnytyksenAikanaSivuTitle);
        activityName = "synnytyksenAikanaSivu5";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::synnytyksenAikanaSivu4);
        rightArrow.setOnClickListener(this::synnytyksenAikanaSivu6);

        imageArea.setVisibility(View.GONE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("synnytyksenAikanaSivu5.txt"));
        stepView.getState().stepsNumber(6).commit();
        stepView.go(4, false);
    }

    public void synnytyksenAikanaSivu6(View v) {
        title.setText(R.string.synnytyksenAikanaSivuTitle);
        activityName = "synnytyksenAikanaSivu6";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::synnytyksenAikanaSivu5);
        rightArrow.setOnClickListener(this::kotisivu);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("synnytyksenAikanaSivu6.txt"));
        stepView.getState().stepsNumber(6).commit();
        stepView.go(5, false);
    }


    public void synnytyksenJalkeenSivu1(View v) {
        title.setText(R.string.synnytyksenJalkeenSivuTitle);
        activityName = "synnytyksenJalkeenSivu1";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::kotisivu);
        rightArrow.setOnClickListener(this::synnytyksenJalkeenSivu2);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("synnytyksenJalkeenSivu1.txt"));
        stepView.getState().stepsNumber(4).commit();
        stepView.go(0, false);
    }

    public void synnytyksenJalkeenSivu2(View v) {
        title.setText(R.string.synnytyksenJalkeenSivuTitle);
        activityName = "synnytyksenJalkeenSivu2";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::synnytyksenJalkeenSivu1);
        rightArrow.setOnClickListener(this::synnytyksenJalkeenSivu3);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("synnytyksenJalkeenSivu2.txt"));
        stepView.getState().stepsNumber(4).commit();
        stepView.go(1, false);
    }

    public void synnytyksenJalkeenSivu3(View v) {
        title.setText(R.string.synnytyksenJalkeenSivuTitle);
        activityName = "synnytyksenJalkeenSivu3";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::synnytyksenJalkeenSivu2);
        rightArrow.setOnClickListener(this::synnytyksenJalkeenSivu4);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("synnytyksenJalkeenSivu3.txt"));
        stepView.getState().stepsNumber(4).commit();
        stepView.go(2, false);
    }

    public void synnytyksenJalkeenSivu4(View v) {
        title.setText(R.string.synnytyksenJalkeenSivuTitle);
        activityName = "synnytyksenJalkeenSivu4";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::synnytyksenJalkeenSivu3);
        rightArrow.setOnClickListener(this::kotisivu);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("synnytyksenJalkeenSivu4.txt"));
        stepView.getState().stepsNumber(4).commit();
        stepView.go(3, false);
    }


    public void peratilaSivu1(View v) {
        title.setText(R.string.peratilaSivuTitle);
        activityName = "peratilaSivu1";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::erikoistilanteetSivu);
        rightArrow.setOnClickListener(this::peratilaSivu2);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("peratilaSivu1.txt"));
        stepView.getState().stepsNumber(5).commit();
        stepView.go(0, false);
    }

    public void peratilaSivu2(View v) {
        title.setText(R.string.peratilaSivuTitle);
        activityName = "peratilaSivu2";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::peratilaSivu1);
        rightArrow.setOnClickListener(this::peratilaSivu3);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("peratilaSivu2.txt"));
        stepView.getState().stepsNumber(5).commit();
        stepView.go(1, false);
    }

    public void peratilaSivu3(View v) {
        title.setText(R.string.peratilaSivuTitle);
        activityName = "peratilaSivu3";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::peratilaSivu2);
        rightArrow.setOnClickListener(this::peratilaSivu4);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("peratilaSivu3.txt"));
        stepView.getState().stepsNumber(5).commit();
        stepView.go(2, false);
    }

    public void peratilaSivu4(View v) {
        title.setText(R.string.peratilaSivuTitle);
        activityName = "peratilaSivu4";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::peratilaSivu3);
        rightArrow.setOnClickListener(this::peratilaSivu5);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("peratilaSivu4.txt"));
        stepView.getState().stepsNumber(5).commit();
        stepView.go(3, false);
    }

    public void peratilaSivu5(View v) {
        title.setText(R.string.peratilaSivuTitle);
        activityName = "peratilaSivu5";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::peratilaSivu4);
        rightArrow.setOnClickListener(this::erikoistilanteetSivu);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("peratilaSivu5.txt"));
        stepView.getState().stepsNumber(5).commit();
        stepView.go(4, false);
    }


    public void hartiadystokiaSivu1(View v) {
        title.setText(R.string.hartiadystokiaSivuTitle);
        activityName = "hartiadystokiaSivu1";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::erikoistilanteetSivu);
        rightArrow.setOnClickListener(this::hartiadystokiaSivu2);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("hartiadystokiaSivu1.txt"));
        stepView.getState().stepsNumber(3).commit();
        stepView.go(0, false);
    }

    public void hartiadystokiaSivu2(View v) {
        title.setText(R.string.hartiadystokiaSivuTitle);
        activityName = "hartiadystokiaSivu2";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::hartiadystokiaSivu1);
        rightArrow.setOnClickListener(this::hartiadystokiaSivu3);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("hartiadystokiaSivu2.txt"));
        stepView.getState().stepsNumber(3).commit();
        stepView.go(1, false);
    }

    public void hartiadystokiaSivu3(View v) {
        title.setText(R.string.hartiadystokiaSivuTitle);
        activityName = "hartiadystokiaSivu3";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::hartiadystokiaSivu2);
        rightArrow.setOnClickListener(this::erikoistilanteetSivu);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje.jpg"));
        textView.setText(getText("hartiadystokiaSivu3.txt"));
        stepView.getState().stepsNumber(3).commit();
        stepView.go(2, false);
    }


    public void napanuoraSivu1(View v) {
        title.setText(R.string.napanuoraSivuTitle);
        activityName = "napanuoraSivu1";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::erikoistilanteetSivu);
        rightArrow.setOnClickListener(this::napanuoraSivu2);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje7.jpg"));
        textView.setText(getText("napanuoraSivu1.txt"));
        stepView.getState().stepsNumber(3).commit();
        stepView.go(0, false);
    }

    public void napanuoraSivu2(View v) {
        title.setText(R.string.napanuoraSivuTitle);
        activityName = "napanuoraSivu2";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::napanuoraSivu1);
        rightArrow.setOnClickListener(this::napanuoraSivu3);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje7.jpg"));
        textView.setText(getText("napanuoraSivu2.txt"));
        stepView.getState().stepsNumber(3).commit();
        stepView.go(1, false);
    }

    public void napanuoraSivu3(View v) {
        title.setText(R.string.napanuoraSivuTitle);
        activityName = "napanuoraSivu3";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::napanuoraSivu2);
        rightArrow.setOnClickListener(this::erikoistilanteetSivu);

        imageArea.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(getImage("ohje4.jpg"));
        textView.setText(getText("napanuoraSivu3.txt"));
        stepView.getState().stepsNumber(3).commit();
        stepView.go(2, false);
    }


    public void laakeohjeetSivu(View v) {
        title.setText(R.string.laakeohjeetSivuTitle);
        activityName = "laakeohjeetSivu";
        setLayout("layoutImageText");

        leftArrow.setOnClickListener(this::kotisivu);
        rightArrow.setVisibility(View.INVISIBLE);

        imageArea.setVisibility(View.GONE);
        textView.setText(getText("laakeohjeetSivu.txt"));
        stepView.setVisibility(View.GONE);
    }
    //endregion
}