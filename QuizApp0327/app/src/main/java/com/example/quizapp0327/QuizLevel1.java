package com.example.quizapp0327;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;

public class QuizLevel1 extends AppCompatActivity {
    Button yes1, no1, next1, backHome1;

    TextView showProblem1;

    String problem1, answer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_level1);

        getSupportActionBar().hide();

        yes1 = (Button) findViewById(R.id.yes1);
        no1 = (Button) findViewById(R.id.no1);
        next1 = (Button) findViewById(R.id.next1);
        backHome1 = (Button) findViewById(R.id.backHome1);

        next1.setEnabled(false);

        String data = QuizTable.DATAS[(int)(Math.random()*QuizTable.DATAS.length)];

        StringTokenizer st = new StringTokenizer(data, QuizTable.DELIMITER);

        problem1 = st.nextToken(); answer1 = st.nextToken();

        showProblem1 = (TextView)findViewById(R.id.showProblem1);

        showProblem1.setText(problem1);

        yes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer1.equals(QuizTable.YES)) {
                    MediaPlayer.create(QuizLevel1.this, R.raw.applause).start();
                    Toast.makeText(QuizLevel1.this, "정답", Toast.LENGTH_SHORT).show();
                    next1.setEnabled(true);
                    next1.setBackgroundResource(R.drawable.nextgo);
                } else {
                    MediaPlayer.create(QuizLevel1.this, R.raw.fail).start();
                    Toast.makeText(QuizLevel1.this, "오답", Toast.LENGTH_SHORT).show();
                }
            }
        });

        no1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer1.equals(QuizTable.NO)) {
                    MediaPlayer.create(QuizLevel1.this, R.raw.applause).start();
                    Toast.makeText(QuizLevel1.this, "정답", Toast.LENGTH_SHORT).show();
                    next1.setEnabled(true);
                    next1.setBackgroundResource(R.drawable.nextgo);
                } else {
                    MediaPlayer.create(QuizLevel1.this, R.raw.fail).start();
                    Toast.makeText(QuizLevel1.this, "오답", Toast.LENGTH_SHORT).show();
                }
            }
        });

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizLevel2.class);
                startActivity(intent);
            }
        });

        backHome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}