package com.example.testapp0306;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button add, minus, times, divides;

    EditText input1,input2;

    TextView result;

    public void showResult(TextView result, int input1, char operator, int input2){
        switch (operator) {
            case '+': result.setText(Integer.toString(input1+input2));
                break;
            case '-': result.setText(Integer.toString(input1-input2));
                break;
            case '*': result.setText(Integer.toString(input1*input2));
                break;
            case '/': result.setText(Float.toString((float)input1/input2));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.add);
        minus = findViewById(R.id.minus);
        times = findViewById(R.id.times);
        divides = findViewById(R.id.divides);

        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);

        result = findViewById(R.id.result);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(input1.getText().toString());
                int num2 = Integer.parseInt(input2.getText().toString());

                showResult(result,num1,'+',num2);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(input1.getText().toString());
                int num2 = Integer.parseInt(input2.getText().toString());

                showResult(result,num1,'-',num2);
            }
        });

        times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(input1.getText().toString());
                int num2 = Integer.parseInt(input2.getText().toString());

                showResult(result,num1,'*',num2);
            }
        });

        divides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(input1.getText().toString());
                int num2 = Integer.parseInt(input2.getText().toString());

                showResult(result,num1,'/',num2);
            }
        });
    }
}