package com.example.activityintent0403_3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_REGISTER = 1000;

    private TextView nameView, telView, addressView;
    private Button registerBtn, exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Intent 정보 받아오기");

        nameView = (TextView)findViewById(R.id.nameView);
        telView = (TextView)findViewById(R.id.telView);
        addressView = (TextView)findViewById(R.id.addressView);

        registerBtn = (Button)findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivityForResult(intent, REQUEST_REGISTER);
            }
        });



        exitBtn = (Button)findViewById(R.id.exitBtn);

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_REGISTER && resultCode == RESULT_OK) {
            nameView.setText(data.getStringExtra(RegisterActivity.RESULT_NAME));
            telView.setText(data.getStringExtra(RegisterActivity.RESULT_TEL));
            addressView.setText(data.getStringExtra(RegisterActivity.RESULT_ADDRESS));
        }
    }
}