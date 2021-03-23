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

        ArrayList<String> txtFileNames = new ArrayList<>();
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

        File ambuAppDir = new File(Environment.getExternalStorageDirectory(), "AmbuApp");
        File ambuAppImgDir = new File(Environment.getExternalStorageDirectory(), "AmbuApp/Images");
        File ambuAppTxtDir = new File(Environment.getExternalStorageDirectory(), "AmbuApp/TextFiles");

        // temporary for better workflow
        if(!ambuAppDir.exists()) { ambuAppDir.mkdir(); }
        if(!ambuAppImgDir.exists()) { ambuAppImgDir.mkdir(); }
        if(!ambuAppTxtDir.exists()) { ambuAppTxtDir.mkdir(); }
        if(!ambuAppDir.exists() || !ambuAppImgDir.exists() || !ambuAppTxtDir.exists()) {
            Log.d("test", "Cannot create AmbuApp directories, permission denied.");
        }

        // add this function to the final work
        /*if(!ambuAppDir.exists()) {
            if(ambuAppDir.mkdir()) {
                ambuAppImgDir.mkdir();
                ambuAppTxtDir.mkdir();
            } else {
                Log.d("test", "Cannot create AmbuApp directory, permission denied.");
            }
        }*/

        // test connection to the Firebase
        downloadFileFromFirebase(imageRefs.get(0), ambuAppImgDir, imageFileNames.get(0));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(firebase) {
            // temporary for better workflow
            deleteFilesFromDir(ambuAppImgDir);
            deleteFilesFromDir(ambuAppTxtDir);

            // download files from Firebase
            for(int i=0; i<imageRefs.size(); i++) {
                downloadFileFromFirebase(imageRefs.get(i), ambuAppImgDir, imageFileNames.get(i));
            }

            for(int i=0; i<txtRefs.size(); i++) {
                downloadFileFromFirebase(txtRefs.get(i), ambuAppTxtDir, txtFileNames.get(i));
            }
            Log.d("test", "Updated succesfully!");
            Log.d("test", "Image files: " + imageRefs.size() + "/" + imageFileNames.size());
            Log.d("test", "Text files: " + txtRefs.size() + "/" + txtFileNames.size());
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

    public void deleteFilesFromDir(File dir) {
        String[] items = dir.list();
        for(String item : items) {
            new File(dir, item).delete();
        }
    }
}
