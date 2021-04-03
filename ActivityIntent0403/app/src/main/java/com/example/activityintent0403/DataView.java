package com.example.activityintent0403;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DataView extends AppCompatActivity {

    private TextView inputtedName, inputtedAddress;
    private Button exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);

        inputtedName = (TextView)findViewById(R.id.inputtedName);
        inputtedAddress = (TextView)findViewById(R.id.inputtedAddress);

        Intent intent = getIntent();

        String name = intent.getStringExtra(MainActivity.NAME_KEY);
        String address = intent.getStringExtra(MainActivity.ADDRESS_KEY);

        Log.d("DataView", "name : " + name);
        Log.d("DataView", "address : " + address);

        inputtedName.setText(name);
        inputtedAddress.setText(address);

        exitBtn = (Button)findViewById(R.id.exitBtn);

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}