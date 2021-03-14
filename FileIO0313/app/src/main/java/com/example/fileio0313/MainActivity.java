package com.example.fileio0313;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
        String state = Environment.getExternalStorageState();
        if(!state.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this,"SD카드 쓰기 실패",Toast.LENGTH_SHORT).show();
            return;
        }

        File file = new File(getExternalFilesDir(null), FILE_NAME);

        Log.d("my_Tag", "외장 메모리 경로 : " + file.toString());

        try {
            OutputStream outputStream = new FileOutputStream(file);

            outputStream.write(input.getText().toString().getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 외장 메모리 읽기
    public void extMemRead(View v) {
        String state = Environment.getExternalStorageState();
        if(!state.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this,"SD카드 읽기 실패",Toast.LENGTH_SHORT).show();
            return;
        }

        File file = new File(getExternalFilesDir(null), FILE_NAME);

        try {
            InputStream inputStream = new FileInputStream(file);

            byte[] buf = new byte[inputStream.available()];
            inputStream.read(buf);

            output.setText(new String(buf));

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // RAW 메모리 읽기
    public void rawRead(View v) {
        InputStream inputStream = getResources().openRawResource(R.raw.raw);

        try {
            byte[] buf = new byte[inputStream.available()];
            inputStream.read(buf);

            output.setText(new String(buf));

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}