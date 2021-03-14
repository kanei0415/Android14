package com.example.inbisibleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CheckBox startCheck;

    TextView descriptionShow;

    RadioGroup animalChoice;
    RadioButton dog, cat, hamster;

    Button submitButton;

    ImageView animalShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startCheck = (CheckBox)findViewById(R.id.startCheck);

        descriptionShow = (TextView)findViewById(R.id.descriptionShow);

        animalChoice = (RadioGroup)findViewById(R.id.animalChoice);
        dog = (RadioButton)findViewById(R.id.dog);
        cat = (RadioButton)findViewById(R.id.cat);
        hamster = (RadioButton)findViewById(R.id.hamster);

        submitButton = (Button)findViewById(R.id.submitButton);

        animalShow = (ImageView)findViewById(R.id.animalShow);

        startCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(startCheck.isChecked()) {
                    descriptionShow.setVisibility(View.VISIBLE);
                    animalChoice.setVisibility(View.VISIBLE);
                    submitButton.setVisibility(View.VISIBLE);
                    animalShow.setVisibility(View.VISIBLE);
                } else {
                    descriptionShow.setVisibility(View.GONE);
                    animalChoice.setVisibility(View.GONE);
                    submitButton.setVisibility(View.INVISIBLE);
                    animalShow.setVisibility(View.INVISIBLE);
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = animalChoice.getCheckedRadioButtonId();

                Log.d("my_tag","id : " + id);

                switch (id) {
                    case R.id.dog: animalShow.setImageResource(R.drawable.dog);
                        break;

                    case R.id.cat: animalShow.setImageResource(R.drawable.cat);
                        break;

                    case R.id.hamster: animalShow.setImageResource(R.drawable.hamster);
                        break;
                }
            }
        });
    }
}