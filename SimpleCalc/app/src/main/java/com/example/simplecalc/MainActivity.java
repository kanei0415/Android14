package com.example.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText calcInput;

    Calc c = new Calc();

    private String expression = "";

    // 숫자 버튼 클릭 이벤트
    public void onClickNumber(View v) {
        expression += ((Button)v).getText().toString();
        calcInput.setText(expression);
    }

    // 연산자 버튼 클릭 이벤트
    public void onClickOperator(View v) {
        expression += ((Button)v).getText().toString();
        calcInput.setText(expression);
    }

    // = 버튼 클릭 이벤트
    public void onClickEqual(View v) {
        // Calc로 expression을 넘김
        c.setExpression(expression);

        // Calc에서 계산 결과를 받음
        String result = c.getResult();

        expression = result;
        calcInput.setText(expression);
    }

    // X 버튼 클릭 이벤트
    public void onClickCancel(View v) {
        if(expression.length()>0) {
            expression = expression.substring(0,expression.length()-1);
            calcInput.setText(expression);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}