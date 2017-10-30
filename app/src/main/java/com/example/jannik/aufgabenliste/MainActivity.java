package com.example.jannik.aufgabenliste;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

//Eigene Activity bei App start um data mit gespeicherten Werte zu laden und an MainActivity ubergeben.
//Auch data zu AddNewExercise passen und neue Exercise da hinzufügen
public class MainActivity extends AppCompatActivity implements AdapterButtonInterface {

    ListView lv;

    private ArrayList<Exercise> data;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = (ArrayList<Exercise>) getIntent().getExtras().getSerializable("exerciseData");

        if(data == null) {
            data = new ArrayList<>();
        }

        lv = (ListView) findViewById(R.id.ExerciseList);

        lv.setAdapter(new TextButtonArrayAdapter(this,R.layout.list,data));

        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                Exercise exercise = (Exercise)adapter.getItemAtPosition(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater layoutInflater = MainActivity.this.getLayoutInflater();
                View alertView = layoutInflater.inflate(R.layout.inspect_exercise,null);
                builder.setView(alertView);

                TextView headlineView = (TextView)alertView.findViewById(R.id.taskText);
                TextView descriptionView = (TextView)alertView.findViewById(R.id.descriptionText);

                headlineView.setText(exercise.getHeadline());
                descriptionView.setText(exercise.getInfo());

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public void adapterButtonClicked(int position){
        data.remove(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_button,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.addNewItem){

            Intent intent = new Intent(getBaseContext(), AddNewExerciseActivity.class);
            intent.putExtra("exerciseData", data);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            FileOutputStream fileOutputStream = openFileOutput("saveFile", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(data);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            Log.d("Error",e.getMessage());
        }
    }
}
