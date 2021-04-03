package com.example.activity0327;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_start, btn_setting, btn_score, btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btn_start = (Button)findViewById(R.id.btn_start);
        btn_setting = (Button)findViewById(R.id.btn_setting);
        btn_score = (Button)findViewById(R.id.btn_score);
        btn_exit = (Button)findViewById(R.id.btn_exit);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Game Start!", Toast.LENGTH_SHORT).show();

                // 4대 컴포넌트 관리 객체            출발지점            이동대상
                Intent intent = new Intent(getApplicationContext(), GameStart.class);

                // 액티비티 시작
                startActivity(intent);
            }
        });

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Game Setting!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), GameSetting.class);

                startActivity(intent);
            }
        });

        btn_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Game Score!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), GameScore.class);

                startActivity(intent);
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}