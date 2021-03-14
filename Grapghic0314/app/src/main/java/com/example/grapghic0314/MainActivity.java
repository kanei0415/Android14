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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0 , 1 , 0 , "선 그리기");
        menu.add(0 , 2 , 0 , "원 그리기");
        return true;
    }

    private final int LINE = 1, CIRCLE = 2;
    private int drawType = LINE;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case LINE: drawType = LINE;
                break;
            case CIRCLE: drawType = CIRCLE;
                break;
        }

        return true;
    }

    class MyGraphicView extends View {
        private int startX = -1, startY = -1;
        private int stopX = -1, stopY = -1;

        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = (int)event.getX();
                    startY = (int)event.getY();
                    break;
                case MotionEvent.ACTION_MOVE: case MotionEvent.ACTION_UP:
                    stopX = (int)event.getX();
                    stopY = (int)event.getY();

                    invalidate(); // onDraw() call
                    break;
            }

            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();

            switch (drawType) {
                case LINE:
                    paint.setAntiAlias(true);
                    paint.setStrokeWidth(20);
                    paint.setStyle(Paint.Style.FILL);
                    paint.setColor(Color.GREEN);

                    canvas.drawLine(startX,startY , stopX,stopY , paint);
                    break;
                case CIRCLE:
                    paint.setAntiAlias(true);
                    paint.setStrokeWidth(10);
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setColor(Color.RED);

                    int radius = (int)Math.sqrt(
                            Math.pow(stopX-startX,2) + Math.pow(stopY-startY,2)
                    );
                    canvas.drawCircle(startX,startY , radius , paint);
                    break;
            }

            // line  그리기
            //canvas.drawLine(startX,startY , stopX,stopY , paint);

            // circle 그리기
            //int radius = (int)Math.sqrt(Math.pow(stopX-startX , 2) + Math.pow(stopY-startY , 2));
            //canvas.drawCircle(startX,startY , radius , paint);
        }
    }


}