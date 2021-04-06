package com.example.ambuapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

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

    StorageReference storageRef;
    ArrayList<String> imageFileNames = new ArrayList<>();
    ArrayList<String> txtFileNames = new ArrayList<>();
    ArrayList<StorageReference> imageRefs = new ArrayList<>();
    ArrayList<StorageReference> txtRefs = new ArrayList<>();
    int fileCounter = 0;
    boolean firebaseStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        homeButton = findViewById(R.id.homeButton);
        title = findViewById(R.id.title);

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

        Log.d("test", "No internet connection!");
        storageRef = FirebaseStorage.getInstance().getReference();
        addFileNames();
        for(String image : imageFileNames) useAssetFile(image);
        for(String text : txtFileNames) useAssetFile(text);
        Log.d("test", "Necessary files created from assets");

        // lataa tarvittavat tiedostot firebasesta jos niitä ei ole olemassa
        /*String[] files = this.fileList();
        if(files.length < 5) {
            storageRef = FirebaseStorage.getInstance().getReference();

            if(isNetworkAvailable()) {
                addFileNames();
                update();

                // small delay for firebase to upload some files
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // if no files has been downloaded from firebase, use default files instead
                files = this.fileList();
                if(files.length < 5) {
                    for(String image : imageFileNames) useAssetFile(image);
                    for(String text : txtFileNames) useAssetFile(text);
                    Log.d("test", "Firebase failed!");
                    Log.d("test", "Necessary files created from assets");

                    // print all files from dir
//                    fileCounter = 0;
//                    files = this.fileList();
//                    for(String file : files) {
//                        Log.d("files", "fileName: " + file + " " + ++fileCounter + "/" + (fileList().length));
//                    }
                }
            } else {
                Log.d("test", "No internet connection!");
                addFileNames();
                for(String image : imageFileNames) useAssetFile(image);
                for(String text : txtFileNames) useAssetFile(text);
                Log.d("test", "Necessary files created from assets");
            }
        } */
    }

    public void useAssetFile(String fileName) {
        AssetManager assetManager = getAssets();
        InputStream in = null;
        FileOutputStream out = null;
        try {
            File outFile = new File(getFilesDir(), fileName);
            in = assetManager.open(fileName);
            out = new FileOutputStream(outFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }

            if(in != null) in.close();
            if(out != null) out.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void addFileNames() {
        imageFileNames.add("ohje.jpg");
        imageFileNames.add("ohje3.jpg");
        imageFileNames.add("ohje4.jpg");
        imageFileNames.add("ohje5.jpg");
        imageFileNames.add("ohje7.jpg");

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
        txtFileNames.add("valmistautuminen4.txt");
        txtFileNames.add("valmistautuminen5.txt");
        txtFileNames.add("valmistautuminen6.txt");

        imageRefs.add(storageRef.child("kuvat/ohje.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje3.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje4.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje5.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje7.jpg"));

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
    }

    public void update() {
        fileCounter = 0;

        for(int i=0; i<imageRefs.size(); i++) {
            downloadFileFromFirebase(imageRefs.get(i), getFilesDir(), imageFileNames.get(i));
        }

        for(int i=0; i<txtRefs.size(); i++) {
            downloadFileFromFirebase(txtRefs.get(i), getFilesDir(), txtFileNames.get(i));
        }
    }

    public void downloadFileFromFirebase(StorageReference ref, File dir, String name) {
        File file = new File(dir, name);
        ref.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                fileCounter++;
                if(fileCounter == (imageFileNames.size() + txtFileNames.size())) {
                    firebaseStatus = true;
                    Log.d("test", "Necessary files created from firebase");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.d("test", "Firebase error");
            }
        });
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

    public void valmistautuminenPage() {
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePage();
            }
        });

        title.setText("Valmistautuminen");
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.VISIBLE);

        button1.setText("Huomioitavaa");
        button2.setText("Synnytyksen tilanne");
        button3.setText("Hoidetaan kohteessa");
        button4.setText("Milloin matkaan");
        button5.setText("Miten toimitaan");
        button6.setText("Hyvä tietää");

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
                activityName = "Valmistautuminen1";
                imageTextActivity(v);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Valmistautuminen2";
                imageTextActivity(v);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Valmistautuminen3";
                imageTextActivity(v);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Valmistautuminen4";
                imageTextActivity(v);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Valmistautuminen5";
                imageTextActivity(v);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Valmistautuminen6";
                imageTextActivity(v);
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
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.VISIBLE);

        button1.setText("Synnytyksen aikana 1");
        button2.setText("Synnytyksen aikana 2");
        button3.setText("Synnytyksen aikana 3");
        button4.setText("Synnytyksen aikana 4");
        button5.setText("Synnytyksen aikana 5");
        button6.setText("Synnytyksen aikana 6");

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
                activityName = "Aikana1";
                imageTextActivity(v);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Aikana2";
                imageTextActivity(v);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Aikana3";
                imageTextActivity(v);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Aikana4";
                imageTextActivity(v);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Aikana5";
                imageTextActivity(v);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Aikana6";
                imageTextActivity(v);
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

        button1.setText("Lapsen synnyttyä");
        button2.setText("Napanuoran leikkaus");
        button3.setText("Jälkeisvaihe");
        button4.setText("Toimi näin");
        button5.setText("Tarkkailu");

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
                imageTextActivity(v);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Tarkistus2";
                imageTextActivity(v);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Tarkistus3";
                imageTextActivity(v);
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
                imageTextActivity(v);
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
            public void onClick(View v) { homePage();}
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { peratilaPage();}
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { hartiadystokiaPage();}
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { napanuoraPage();}
        });
    }

    public void peratilaPage() {
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePage();
            }
        });

        title.setText("Perätila");
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.INVISIBLE);

        button1.setText("Perätila vaihe1");
        button2.setText("Perätila vaihe2");
        button3.setText("Perätila vaihe3");
        button4.setText("Perätila vaihe4");
        button5.setText("Perätila vaihe5");

        leftArrow.setVisibility(View.VISIBLE);
        rightArrow.setVisibility(View.INVISIBLE);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { erikoistilanteetPage();}
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Perätila1";
                imageTextActivity(v);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Perätila2";
                imageTextActivity(v);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        button5.setVisibility(View.INVISIBLE);
        button6.setVisibility(View.INVISIBLE);

        button2.setText("Napanuora vaihe1");
        button3.setText("Napanuora vaihe2");
        button4.setText("Napanuora vaihe3");

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
                imageTextActivity(v);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Napanuora2";
                imageTextActivity(v);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName = "Napanuora3";
                imageTextActivity(v);
            }
        });

    }

    //Avaimen välitys ImageTextiin
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
                        imageTextActivity(v);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}