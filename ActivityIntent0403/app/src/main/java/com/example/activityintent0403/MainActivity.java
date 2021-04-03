package com.example.activityintent0403;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String NAME_KEY = "NAME", ADDRESS_KEY = "ADDRESS";

    private EditText inputName, inputAddress;
    private Button intentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputName = (EditText)findViewById(R.id.inputName);
        inputAddress = (EditText)findViewById(R.id.inputAddress);

        intentBtn = (Button)findViewById(R.id.intentBtn);

        intentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DataView.class);

                String name = inputName.getText().toString();
                String address = inputAddress.getText().toString();

                // 값을 넘긴다. key : value -> Hash Table, Dictionary
                intent.putExtra(NAME_KEY, name);
                intent.putExtra(ADDRESS_KEY, address);

                startActivity(intent);
            }
        });
    }
}