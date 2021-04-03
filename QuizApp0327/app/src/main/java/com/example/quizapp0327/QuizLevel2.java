package com.example.quizapp0327;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;

public class QuizLevel2 extends AppCompatActivity {
    Button yes2, no2, next2, backHome2;

    TextView showProblem2;

    StringTokenizer st;
    String problem2, answer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_level2);

        yes2 = (Button) findViewById(R.id.yes2);
        no2 = (Button) findViewById(R.id.no2);
        next2 = (Button) findViewById(R.id.next2);
        backHome2 = (Button) findViewById(R.id.backHome2);

        next2.setEnabled(false);

        st = QuizTable.getRandomDataStringTokenizer();
        problem2 = st.nextToken(); answer2 = st.nextToken();

        showProblem2 = (TextView)findViewById(R.id.showProblem2);
        showProblem2.setText(problem2);

        yes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(QuizTable.YES, answer2);
            }
        });

        no2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(QuizTable.NO, answer2);
            }
        });

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizLevel3.class);
                startActivity(intent);
            }
        });

        backHome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(QuizLevel2.this);
                builder.setTitle("Confirm").setMessage("Are you Really Go to Home?");
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
                builder.create().show();
            }
        });
    }

    public void checkAnswer(String myAnswer, String dataAnswer) {
        if(myAnswer.equals(dataAnswer)) {
            MediaPlayer.create(this, R.raw.applause).start();
            Toast.makeText(this, "정답", Toast.LENGTH_SHORT).show();
            next2.setEnabled(true);
            next2.setBackgroundResource(R.drawable.nextgo);
        } else {
            MediaPlayer.create(this, R.raw.fail).start();
            Toast.makeText(this, "오답", Toast.LENGTH_SHORT).show();
        }
    }
}