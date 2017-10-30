package com.example.jannik.aufgabenliste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import java.util.ArrayList;


public class AddNewExerciseActivity extends AppCompatActivity{

    private Exercise exercise;

    private ArrayList<Exercise> data;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_exercise);

        data = (ArrayList<Exercise>) getIntent().getExtras().getSerializable("exerciseData");

        exercise = new Exercise();

        Button button = (Button) findViewById(R.id.InsertFieldApply);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText headlineET = (EditText) findViewById(R.id.InsertFieldHeadline);
                EditText descriptionET = (EditText) findViewById(R.id.InsertFieldInfo);

                exercise.setHeadline(headlineET.getText().toString());
                exercise.setInfo(descriptionET.getText().toString());

                if(headlineET.getText().toString().trim().length() != 0) {

                    data.add(exercise);

                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    intent.putExtra("exerciseData", data);
                    startActivity(intent);
                }
            }
        });
    }
}
