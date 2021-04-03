package com.example.quizapp0327;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;

public class QuizLevel3 extends AppCompatActivity {

    Button yes3, no3, next3, backHome3;

    TextView showProblem3;

    StringTokenizer st;
    String problem3, answer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_level3);

        yes3 = (Button) findViewById(R.id.yes3);
        no3 = (Button) findViewById(R.id.no3);
        next3 = (Button) findViewById(R.id.next3);
        backHome3 = (Button) findViewById(R.id.backHome3);

        next3.setEnabled(false);

        st = QuizTable.getRandomDataStringTokenizer();
        problem3 = st.nextToken(); answer3 = st.nextToken();

        showProblem3 = (TextView)findViewById(R.id.showProblem3);
        showProblem3.setText(problem3);

        yes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(QuizTable.YES, answer3);
            }
        });

        no3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(QuizTable.NO, answer3);
            }
        });

        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizLevel1.class);
                startActivity(intent);
            }
        });

        backHome3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(QuizLevel3.this);
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
            next3.setEnabled(true);
            next3.setBackgroundResource(R.drawable.nextgo);
        } else {
            MediaPlayer.create(this, R.raw.fail).start();
            Toast.makeText(this, "오답", Toast.LENGTH_SHORT).show();
        }
    }
}