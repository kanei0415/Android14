package com.example.activityintent0403_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    public static final String RESULT_NAME = "NAME", RESULT_TEL = "TEL", RESULT_ADDRESS = "ADDRESS";

    private TextView editName, editTel, editAddress;
    private Button ok, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editName = (TextView)findViewById(R.id.editName);
        editTel = (TextView)findViewById(R.id.editTel);
        editAddress = (TextView)findViewById(R.id.editAddress);

        ok = (Button)findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.putExtra(RESULT_NAME, editName.getText().toString());
                intent.putExtra(RESULT_TEL, editTel.getText().toString());
                intent.putExtra(RESULT_ADDRESS, editAddress.getText().toString());

                setResult(RESULT_OK, intent);
                finish();
            }
        });

        cancel = (Button)findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}