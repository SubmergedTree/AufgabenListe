package com.example.jannik.aufgabenliste;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by jannik on 30.05.17.
 */

public class Loading extends AppCompatActivity {

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<Exercise> data = new ArrayList<>();

        try {
            FileInputStream fileInputStream = openFileInput("saveFile");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            data = (ArrayList<Exercise>)objectInputStream.readObject();

        } catch(IOException | ClassNotFoundException e){
            Log.d("Error",e.getMessage());
        }

        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("exerciseData", data);
        startActivity(intent);
    }
}
