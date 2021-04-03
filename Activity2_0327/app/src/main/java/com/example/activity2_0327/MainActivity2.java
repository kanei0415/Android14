package com.example.activity2_0327;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    Button backHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        backHome = (Button)findViewById(R.id.backHome);

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                // FLAG_ACTIVITY_SINGLE_TOP : 액티비티가 존재하면 재사용
                // FLAG_ACTIVITY_CLEAR_TOP : 액티비티의 하위 액티비티를 모두 제거
                // FLAG_ACTIVITY_NEW_TASK : 액티비티를 새롭게 생성
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}