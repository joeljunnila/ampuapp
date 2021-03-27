//package com.example.ambuapp;
//
//import android.os.Environment;
//import android.util.Log;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.storage.FileDownloadTask;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//
//import static android.content.Context.MODE_PRIVATE;
//
//public class MyFirebase {
//    StorageReference storageRef;
//    ArrayList<StorageReference> imageRefs = new ArrayList<>();
//    ArrayList<String> imageFileNames = new ArrayList<>();
//    ArrayList<StorageReference> txtRefs = new ArrayList<>();
//    ArrayList<String> txtFileNames = new ArrayList<>();
//
//    static int firebaseFileCounter = 0;
//
//    MyFirebase() {
//        storageRef = FirebaseStorage.getInstance().getReference();
//        addFirebaseFileRefs();
//    }
//
//    public void addFirebaseFileRefs() {
//        imageRefs.add(storageRef.child("kuvat/ohje.jpg"));
//        imageRefs.add(storageRef.child("kuvat/ohje3.jpg"));
//        imageRefs.add(storageRef.child("kuvat/ohje4.jpg"));
//        imageRefs.add(storageRef.child("kuvat/ohje5.jpg"));
//        imageRefs.add(storageRef.child("kuvat/ohje7.jpg"));
//
//        imageFileNames.add("image1.jpg");
//        imageFileNames.add("image2.jpg");
//        imageFileNames.add("image3.jpg");
//        imageFileNames.add("image4.jpg");
//        imageFileNames.add("image5.jpg");
//
//        txtRefs.add(storageRef.child("tekstit/hartiadystokia1.txt"));
//        txtRefs.add(storageRef.child("tekstit/hartiadystokia2.txt"));
//        txtRefs.add(storageRef.child("tekstit/hartiadystokia3.txt"));
//        txtRefs.add(storageRef.child("tekstit/hartiadystokia4.txt"));
//        txtRefs.add(storageRef.child("tekstit/hartiadystokia5.txt"));
//        txtRefs.add(storageRef.child("tekstit/napanuora1.txt"));
//        txtRefs.add(storageRef.child("tekstit/napanuora2.txt"));
//        txtRefs.add(storageRef.child("tekstit/napanuora3.txt"));
//        txtRefs.add(storageRef.child("tekstit/napanuora4.txt"));
//        txtRefs.add(storageRef.child("tekstit/peratila1.txt"));
//        txtRefs.add(storageRef.child("tekstit/peratila2.txt"));
//        txtRefs.add(storageRef.child("tekstit/peratila3.txt"));
//        txtRefs.add(storageRef.child("tekstit/peratila4.txt"));
//        txtRefs.add(storageRef.child("tekstit/peratila5.txt"));
//        txtRefs.add(storageRef.child("tekstit/synnytyksenAikana1.txt"));
//        txtRefs.add(storageRef.child("tekstit/synnytyksenAikana2.txt"));
//        txtRefs.add(storageRef.child("tekstit/synnytyksenAikana3.txt"));
//        txtRefs.add(storageRef.child("tekstit/synnytyksenAikana4.txt"));
//        txtRefs.add(storageRef.child("tekstit/synnytyksenAikana5.txt"));
//        txtRefs.add(storageRef.child("tekstit/synnytyksenAikana6.txt"));
//        txtRefs.add(storageRef.child("tekstit/synnytyksenAikana7.txt"));
//        txtRefs.add(storageRef.child("tekstit/synnytyksenJalkeen1.txt"));
//        txtRefs.add(storageRef.child("tekstit/synnytyksenJalkeen2.txt"));
//        txtRefs.add(storageRef.child("tekstit/synnytyksenJalkeen3.txt"));
//        txtRefs.add(storageRef.child("tekstit/synnytyksenJalkeen4.txt"));
//        txtRefs.add(storageRef.child("tekstit/synnytyksenJalkeen5.txt"));
//        txtRefs.add(storageRef.child("tekstit/tietoaSovelluksesta.txt"));
//        txtRefs.add(storageRef.child("tekstit/valmistautuminen1.txt"));
//        txtRefs.add(storageRef.child("tekstit/valmistautuminen2.txt"));
//        txtRefs.add(storageRef.child("tekstit/valmistautuminen3.txt"));
//
//        txtFileNames.add("hartiadystokia1.txt");
//        txtFileNames.add("hartiadystokia2.txt");
//        txtFileNames.add("hartiadystokia3.txt");
//        txtFileNames.add("hartiadystokia4.txt");
//        txtFileNames.add("hartiadystokia5.txt");
//        txtFileNames.add("napanuora1.txt");
//        txtFileNames.add("napanuora2.txt");
//        txtFileNames.add("napanuora3.txt");
//        txtFileNames.add("napanuora4.txt");
//        txtFileNames.add("peratila1.txt");
//        txtFileNames.add("peratila2.txt");
//        txtFileNames.add("peratila3.txt");
//        txtFileNames.add("peratila4.txt");
//        txtFileNames.add("peratila5.txt");
//        txtFileNames.add("synnytyksenAikana1.txt");
//        txtFileNames.add("synnytyksenAikana2.txt");
//        txtFileNames.add("synnytyksenAikana3.txt");
//        txtFileNames.add("synnytyksenAikana4.txt");
//        txtFileNames.add("synnytyksenAikana5.txt");
//        txtFileNames.add("synnytyksenAikana6.txt");
//        txtFileNames.add("synnytyksenAikana7.txt");
//        txtFileNames.add("synnytyksenJalkeen1.txt");
//        txtFileNames.add("synnytyksenJalkeen2.txt");
//        txtFileNames.add("synnytyksenJalkeen3.txt");
//        txtFileNames.add("synnytyksenJalkeen4.txt");
//        txtFileNames.add("synnytyksenJalkeen5.txt");
//        txtFileNames.add("tietoaSovelluksesta.txt");
//        txtFileNames.add("valmistautuminen1.txt");
//        txtFileNames.add("valmistautuminen2.txt");
//        txtFileNames.add("valmistautuminen3.txt");
//    }
//
//    public void updateFilesFromFirebase() {
//        for(int i=0; i<imageRefs.size(); i++) {
//            downloadFileFromFirebase(imageRefs.get(i), getFilesDir(), imageFileNames.get(i));
//        }
//
//        for(int i=0; i<txtRefs.size(); i++) {
//            downloadFileFromFirebase(txtRefs.get(i), getFilesDir(), txtFileNames.get(i));
//        }
//    }
//
//    public void downloadFileFromFirebase(StorageReference ref, File dir, String name) {
//        File imageFile = new File(dir, name);
//        ref.getFile(imageFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                firebaseFileCounter++;
//                Log.d("test", firebaseFileCounter + "/" + (imageFileNames.size() + txtFileNames.size()));
//                if(firebaseFileCounter == (imageFileNames.size() + txtFileNames.size())) {
//                    Toast.makeText(getApplicationContext(), "Updated succesfully!", Toast.LENGTH_LONG).show();
//                    Log.d("test", "Updated succesfully!");
//                    firebaseFileCounter = 0;
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                Toast.makeText(getApplicationContext(), "Update failed!", Toast.LENGTH_LONG).show();
//                Log.d("test", "Firebase error");
//            }
//        });
//    }
////
////    public void firebaseInput() {
////        String text = "hello world!";
////        FileOutputStream fos = null;
////        try {
////            fos = openFileOutput("test.txt", MODE_PRIVATE);
////            fos.write(text.getBytes());
////
////            Log.d("test", "save: " + getFilesDir() + "test.txt");
////            Toast.makeText(this, "save: " + getFilesDir() + " !! ", Toast.LENGTH_SHORT).show();
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        } finally {
////            if (fos != null) {
////                try {
////                    fos.close();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////        }
////    }
////
////    public void firebaseOutput() {
////        FileInputStream fis = null;
////        try {
////            fis = openFileInput("test.txt");
////            InputStreamReader isr = new InputStreamReader(fis);
////            BufferedReader br = new BufferedReader(isr);
////            StringBuilder sb = new StringBuilder();
////            String text;
////
////            while((text = br.readLine()) != null) {
////                sb.append(text).append("\n");
////                Log.d("test", "asd: " + text);
////            }
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        } finally {
////            if (fis != null) {
////                try {
////                    fis.close();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////        }
////    }
//}
