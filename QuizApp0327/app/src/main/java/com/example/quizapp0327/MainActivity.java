package com.example.quizapp0327;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button quizStart, quizExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity_DD", "onCreate");
        setContentView(R.layout.activity_main);

        quizStart = (Button)findViewById(R.id.quizStart);
        quizExit = (Button)findViewById(R.id.quizExit);

        quizStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizLevel1.class);

                startActivity(intent);
            }
        });

        quizExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.finishAffinity(MainActivity.this);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity_DD", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity_DD", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity_DD", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity_DD", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity_DD", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity_DD", "onRestart");
    }
}