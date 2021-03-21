package com.example.grapghic0314;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
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
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public static final int LINE = 1, CIRCLE = 2, FREE = 3;

    private int drawType = LINE;

    private String selectColor;

    private String selectWidth;

    ImgSave imgSave = new ImgSave(this);
    private String path;

    public String getPath() {
        return path;
    }

    public int getDrawType() {
        return drawType;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.line: drawType = LINE;
                Toast.makeText(this,"선 그리기", Toast.LENGTH_SHORT).show();
                myGraphicView.clearArrayList();
                myGraphicView.setMode(MyGraphicView.PAINT_MODE);
                break;
            case R.id.circle: drawType = CIRCLE;
                Toast.makeText(this,"원 그리기", Toast.LENGTH_SHORT).show();
                myGraphicView.clearArrayList();
                myGraphicView.setMode(MyGraphicView.PAINT_MODE);
                break;
            case R.id.freeLine: drawType = FREE;
                Toast.makeText(this,"그림 그리기", Toast.LENGTH_SHORT).show();
                myGraphicView.clearArrayList();
                myGraphicView.setMode(MyGraphicView.DRAW_MODE);
                break;
            case R.id.colorPick:
                getColorPick();
                Toast.makeText(this,"색상 변경", Toast.LENGTH_SHORT).show();
                break;
            case R.id.widthPick:
                getWidthPick();
                Toast.makeText(this,"굵기 변경", Toast.LENGTH_SHORT).show();
                break;
            case R.id.save:
                imgSave.imageFileSave(myGraphicView);
                path = imgSave.getStrFilePath();
                Toast.makeText(this,"이미지 저장", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                finish();
                break;
            case R.id.paintColor: drawType = FREE;
                Toast.makeText(this,"색칠 하기", Toast.LENGTH_SHORT).show();
                myGraphicView.clearArrayList();
                myGraphicView.setMode(MyGraphicView.COLOR_MODE);
                break;
            case R.id.load: drawType = FREE;
                Toast.makeText(this,"이미지 불러오기", Toast.LENGTH_SHORT).show();
                myGraphicView.setMode(MyGraphicView.LOAD_MODE);
                break;
        }

        myGraphicView.invalidate();
        return true;
    }

    public void getColorPick() {

        String[] words = new String[] {
          "검정", "파랑", "빨강", "녹색", "보라"
        };

        new AlertDialog.Builder(this).setTitle("선택").
        setSingleChoiceItems(words, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectColor = words[which];
                Log.d("setSingleChoiceItems", "selectColor : " + selectColor);
            }
        })
        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, selectColor, Toast.LENGTH_SHORT).show();
                myGraphicView.getPaint().setColor(getColorInt(selectColor));
            }
        })
        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("setNegativeButton", "색상 선택 취소");
            }
        }).show();
    }

    public int getColorInt(String color) {
        switch (color) {
            case "검정": return Color.BLACK;
            case "파랑": return Color.BLUE;
            case "빨강": return Color.RED;
            case "녹색": return Color.GREEN;
            case "보라": return Color.MAGENTA;
            default: return 0;
        }
    }

    public void getWidthPick () {
        String[] widths = new String[] {
                "10", "20", "30", "40", "50"
        };

        new AlertDialog.Builder(this).setTitle("선택").
                setSingleChoiceItems(widths, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectWidth = widths[which];
                        Log.d("setSingleChoiceItems", "selectWidth : " + selectWidth);
                    }
                })
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, selectWidth, Toast.LENGTH_SHORT).show();
                        myGraphicView.getPaint().setStrokeWidth(Integer.parseInt(selectWidth));
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("setNegativeButton", "굵기 선택 취소");
                    }
                }).show();
    }
}