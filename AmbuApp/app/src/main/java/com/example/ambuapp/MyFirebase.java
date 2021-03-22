package com.example.ambuapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;

public class MyFirebase implements Runnable {
    boolean firebase = false;

    @Override
    public void run() {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();

        ArrayList<StorageReference> imageRefs = new ArrayList<>();
        imageRefs.add(storageRef.child("kuvat/ohje.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje3.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje4.jpg"));

        ArrayList<String> imageFileNames = new ArrayList<>();
        imageFileNames.add("image1.jpg");
        imageFileNames.add("image2.jpg");
        imageFileNames.add("image3.jpg");

        ArrayList<StorageReference> txtRefs = new ArrayList<>();
        txtRefs.add(storageRef.child("kuvat/ohje3.jpg"));
        //txtRefs.add(storageRef.child("tekstit/txt2.jpg"));
        //txtRefs.add(storageRef.child("tekstit/txt3.jpg"));

        ArrayList<String> txtFileNames = new ArrayList<>();
        txtFileNames.add("txt1.jpg");
        //txtFileNames.add("txt2.txt");
        //txtFileNames.add("txt3.txt");

        File ambuAppImgDir = new File(Environment.getExternalStorageDirectory(), "AmbuApp/Images");
        File ambuAppTxtDir = new File(Environment.getExternalStorageDirectory(), "AmbuApp/TextFiles");

        if(ambuAppImgDir.exists()) {
            downloadFileFromFirebase(imageRefs.get(0), ambuAppImgDir, imageFileNames.get(0));
        } else {
            if(!ambuAppImgDir.mkdir()) {
                Log.d("test", "Cannot create ambuAppImgDir, permission denied.");
            }
        }

        if(!ambuAppTxtDir.exists()) {
            if(!ambuAppImgDir.mkdir()) {
                Log.d("test", "Cannot create ambuAppTxtDir, permission denied.");
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(firebase) {
            //Log.d("test", "Succesfully connected to the Firebase!");
            // delete all contents in the directory
            String[] items = ambuAppImgDir.list();
            for(String item : items) {
                new File(ambuAppImgDir, item).delete();
            }

            items = ambuAppTxtDir.list();
            for(String item : items) {
                new File(ambuAppTxtDir, item).delete();
            }

            // download files from Firebase
            for(int i=0; i<imageRefs.size(); i++) {
                downloadFileFromFirebase(imageRefs.get(i), ambuAppImgDir, imageFileNames.get(i));
            }

            for(int i=0; i<txtRefs.size(); i++) {
                downloadFileFromFirebase(txtRefs.get(i), ambuAppTxtDir, txtFileNames.get(i));
            }
            Log.d("test", "Updated succesfully!");

        } else {
            Log.d("test", "Firebase Error!");
        }
    }

    public void downloadFileFromFirebase(StorageReference ref, File dir, String name) {
        File imageFile = new File(dir, name);
        ref.getFile(imageFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                //Toast.makeText(getApplicationContext(), "Updated succesfully!", Toast.LENGTH_LONG).show();
                //Log.d("test", "Succesfully connected to the Firebase");
                firebase = true;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //Toast.makeText(getApplicationContext(), "Update failed!", Toast.LENGTH_LONG).show();
                //Log.d("test", "Unexpected error");
                firebase = false;
            }
        });
    }
}
