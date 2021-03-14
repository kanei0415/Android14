package com.example.fileio0313;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText input;
    TextView output;
    final String FILE_NAME = "test.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        output = findViewById(R.id.output);
    }

    // 내장 메모리 쓰기
    public void intMemWrite(View v) {
        try {
            FileOutputStream fileOutputStream = openFileOutput(FILE_NAME, Context.MODE_APPEND);

            fileOutputStream.write(input.getText().toString().getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 내장 메모리 읽기
    public void intMemRead(View v) {
        try {
            FileInputStream fileInputStream = openFileInput(FILE_NAME);

            byte[] buf = new byte[fileInputStream.available()];
            fileInputStream.read(buf);

            output.setText(new String(buf));

            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 외장 메모리 쓰기
    public void extMemWrite(View v) {

    }

    // 외장 메모리 읽기
    public void extMemRead(View v) {

    }

    // RAW 메모리 읽기
    public void rawRead(View v) {

    }
}