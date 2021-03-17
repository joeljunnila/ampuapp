package com.example.ambuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.common.util.Strings;

public class MenuView extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;

    ImageButton leftArrow;
    ImageButton rightArrow;

    //private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);

        button1.setVisibility(View.INVISIBLE);
        //button2.setVisibility(View.INVISIBLE);
        //button3.setVisibility(View.INVISIBLE);
        //button4.setVisibility(View.INVISIBLE);
        //button5.setVisibility(View.INVISIBLE);
        button6.setVisibility(View.INVISIBLE);

        button1.setText("1");
        button2.setText("Valmistautuminen");
        button3.setText("Synnytysvaiheet");
        button4.setText("Tarkistus");
        button5.setText("Erikoistilanteet");
        button6.setText("6");

        leftArrow = (ImageButton) findViewById(R.id.leftArrow);
        rightArrow = (ImageButton) findViewById(R.id.rightArrow);

        leftArrow.setVisibility(View.INVISIBLE);
        rightArrow.setVisibility(View.INVISIBLE);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valmistautuminen();
            }
        });

        /*storageRef = FirebaseStorage.getInstance().getReference();

        try {
            updateImages();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void valmistautuminen() {
        button2.setVisibility(View.INVISIBLE);
        button3.setText("Valmistautuminen");
        button4.setText("Kohteessa vai matkaan?");
        button5.setText("Miten toimitaan");
        leftArrow.setVisibility(View.VISIBLE);
    }

    /*public void updateImages() throws IOException {
        StorageReference ohjeRef = storageRef.child("kuvat/ohje.jpg");
        File localFile = File.createTempFile("images", "jpg");

        ohjeRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                // Local temp file has been created
                Toast.makeText(getApplicationContext(), "toimii", Toast.LENGTH_LONG).show();
                System.out.println("toimii: " + localFile);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Toast.makeText(getApplicationContext(), "kusi", Toast.LENGTH_LONG).show();
                System.out.println("kusi");
            }
        });
    }*/

    public void returnHome(View view) {}

    public void textViewActivity(View view) {
        Intent intent = new Intent(this, TextView.class);
        startActivity(intent);
    }
}