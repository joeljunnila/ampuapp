package com.example.ambuapp;

import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;

public class MyFirebase implements Runnable {
    StorageReference storageRef;
    boolean firebase = false;

    @Override
    public void run() {
<<<<<<< HEAD
        File ambuAppDir = new File(Environment.getExternalStorageDirectory(), "AmbuApp");
        //File rootPath = new File(Environment.getDataDirectory(), "AmbuApp"); // internal storage
=======
>>>>>>> origin/Teemu

        StorageReference storageRef = FirebaseStorage.getInstance().getReference();

        ArrayList<StorageReference> imageRefs = new ArrayList<>();
        imageRefs.add(storageRef.child("kuvat/ohje.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje3.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje4.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje5.jpg"));
        imageRefs.add(storageRef.child("kuvat/ohje7.jpg"));

        ArrayList<String> imageFileNames = new ArrayList<>();
        imageFileNames.add("image1.jpg");
        imageFileNames.add("image2.jpg");
        imageFileNames.add("image3.jpg");
        imageFileNames.add("image4.jpg");
        imageFileNames.add("image5.jpg");

        downloadFileFromFirebase(imageRefs.get(0), ambuAppDir, imageFileNames.get(0));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(firebase) {
            //Log.d("test", "Succesfully connected to the Firebase!");
            if(ambuAppDir.exists()) {
                // delete all contents in the directory
                String[] items = ambuAppDir.list();
                for(String item : items) {
                    new File(ambuAppDir, item).delete();
                }

                for(int i=0; i<imageRefs.size(); i++) {
                    downloadFileFromFirebase(imageRefs.get(i), ambuAppDir, imageFileNames.get(i));
                }
                Log.d("test", "Updated succesfully!");
            } else {
                if(!ambuAppDir.mkdir()) {
                    Log.d("test", "Cannot create directory, permission denied.");
                }
            }
        } else {
            Log.d("test", "Connection to the Firebase failed!");
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
