package com.kyh.amyApp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kyh.amyApp.R;

public class UserDataView extends AppCompatActivity {

    TextView showDetailName, showDetailTel, showDetailAddr;
    Button backDetailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_view);

        showDetailName = findViewById(R.id.showDetailName);
        showDetailTel = findViewById(R.id.showDetailTel);
        showDetailAddr = findViewById(R.id.showDetailAddr);

        Intent intent = getIntent();

        showDetailName.setText(intent.getStringExtra(MainActivity.NAME_KEY));
        showDetailTel.setText(intent.getStringExtra(MainActivity.TEL_KEY));
        showDetailAddr.setText(intent.getStringExtra(MainActivity.ADDRESS_KEY));

        backDetailBtn = findViewById(R.id.backDetailBtn);

        backDetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}