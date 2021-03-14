package com.example.grapghic0314;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyGraphicView myGraphicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myGraphicView = new MyGraphicView(this);
        setContentView(myGraphicView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0 , 1 , 0 , "선 그리기");
        menu.add(0 , 2 , 0 , "원 그리기");
        menu.add(0 , 3 , 0 , "그림 그리기");
        return true;
    }

    public static final int LINE = 1, CIRCLE = 2, FREE = 3;

    private int drawType = LINE;

    public int getDrawType() {
        return drawType;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        myGraphicView.clearArrayList();
        
        switch (item.getItemId()) {
            case LINE: drawType = LINE;
                Toast.makeText(this,"선 그리기", Toast.LENGTH_SHORT).show();
                break;
            case CIRCLE: drawType = CIRCLE;
                Toast.makeText(this,"원 그리기", Toast.LENGTH_SHORT).show();
                break;
            case FREE: drawType = FREE;
                Toast.makeText(this,"그림 그리기", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }
}