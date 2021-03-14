package com.example.optionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button okbtn, cancelbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        okbtn = (Button)findViewById(R.id.okbtn);
        cancelbtn = (Button)findViewById(R.id.cancelbtn);

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "확인 버튼", Toast.LENGTH_SHORT).show();
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "취소 버튼", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_ok: Toast.makeText(this,
                    "메뉴 확인", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_cancel:
                Toast.makeText(this,
                        "메뉴 취소", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_exit: finish(); // 앱 강제 종료
                break;
        }

        return true;
    }
}