package com.kyh.sqlite0410;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String DB_NAME = "groupDB";

    EditText inputName, inputNumber;
    Button resetBtn, inputBtn, modifyBtn, deleteBtn, searchBtn;
    TextView outputName, outputNumber;

    MyDBHelper myDBHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputName = findViewById(R.id.inputName);
        inputNumber = findViewById(R.id.inputNumber);

        outputName = findViewById(R.id.outputName);
        outputNumber = findViewById(R.id.outputNumber);

        myDBHelper = new MyDBHelper(getApplicationContext(), DB_NAME, null, 1);

        resetBtn = findViewById(R.id.resetBtn);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sqLite db 접속
                sqLiteDatabase = myDBHelper.getWritableDatabase();
                myDBHelper.onUpgrade(sqLiteDatabase, 1, 2);
                sqLiteDatabase.close();
            }
        });

        inputBtn = findViewById(R.id.inputBtn);

        inputBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    sqLiteDatabase = myDBHelper.getWritableDatabase();
                    @SuppressLint("DefaultLocale")
                    String query = String.format("insert into groupTB values ('%s' , %d)"
                            , inputName.getText().toString(), Integer.parseInt(inputNumber.getText().toString()));

                    sqLiteDatabase.execSQL(query);

                    sqLiteDatabase.close();

                    inputName.setText("");
                    inputNumber.setText("");

                    Toast.makeText(MainActivity.this, "Data Input Success!", Toast.LENGTH_SHORT).show();
                } catch(SQLiteConstraintException exception) {
                    Log.d("View.OnClickListener", "Primary Key 중복 : " + exception.getMessage());

                    Toast.makeText(MainActivity.this, "입력된 그룹이름이 이미 존재합니다!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        modifyBtn = findViewById(R.id.modifyBtn);

        modifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = myDBHelper.getWritableDatabase();

                @SuppressLint("DefaultLocale")
                String sql = String.format("update groupTB set gNumber=%d where gName='%s'"
                        ,Integer.parseInt(inputNumber.getText().toString()), inputName.getText().toString());
                sqLiteDatabase.execSQL(sql);

                sqLiteDatabase.close();

                inputName.setText("");
                inputNumber.setText("");
            }
        });

        deleteBtn = findViewById(R.id.deleteBtn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = myDBHelper.getWritableDatabase();

                String sql = String.format("delete from groupTB where gName='%s'", inputName.getText().toString());
                sqLiteDatabase.execSQL(sql);

                sqLiteDatabase.close();

                inputName.setText("");
                inputNumber.setText("");
            }
        });

        searchBtn = findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = myDBHelper.getWritableDatabase();

                String query = "select * from groupTB";

                Cursor cursor = sqLiteDatabase.rawQuery(query, null);

                String name = "그룹이름" + "\r\n" + "---------------" + "\r\n";
                String number = "그룹인원" + "\r\n" + "---------------" + "\r\n";

                while(cursor.moveToNext()) {
                    name += cursor.getString(0) + "\r\n";
                    number += cursor.getString(1) + "\r\n";
                }

                outputName.setText(name);
                outputNumber.setText(number);

                cursor.close();

                sqLiteDatabase.close();

                inputName.setText("");
                inputNumber.setText("");
            }
        });

    }
}