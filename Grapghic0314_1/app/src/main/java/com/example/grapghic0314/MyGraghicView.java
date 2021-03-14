package com.example.grapghic0314;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

class MyGraphicView extends View {
    private int startX = -1, startY = -1;
    private int stopX = -1, stopY = -1;

    MainActivity mainActivity;

    Paint paint;

    ArrayList<MyPoint> arrayList = new ArrayList<>();

    public MyGraphicView(Context context) {
        super(context);
        mainActivity = (MainActivity)context;
        initPaint();
    }

    public void clearArrayList() {
        arrayList.clear();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (mainActivity.getDrawType()) {
            case MainActivity.LINE: case MainActivity.CIRCLE:
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
                break;
            case MainActivity.FREE:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        arrayList.add(new MyPoint(event.getX(),event.getY() ,false));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        arrayList.add(new MyPoint(event.getX(),event.getY(),true));
                        invalidate();
                        break;
                    default: break;
                }
                break;
        }

        return true;
    }

    public void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        switch (mainActivity.getDrawType()) {
            case MainActivity.LINE:
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.GREEN);

                canvas.drawLine(startX,startY , stopX,stopY , paint);
                break;
            case MainActivity.CIRCLE:
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.RED);

                int radius = (int)Math.sqrt(
                        Math.pow(stopX-startX,2) + Math.pow(stopY-startY,2)
                );
                canvas.drawCircle(startX,startY , radius , paint);
                break;
            case MainActivity.FREE:
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.BLACK);

                for(int i=1 ; i<arrayList.size() ; i++) {
                    if(arrayList.get(i).isDraw()) {
                        canvas.drawLine(
                                arrayList.get(i-1).getX(),arrayList.get(i-1).getY(),
                                arrayList.get(i).getX(),arrayList.get(i).getY(),
                                paint
                        );
                    }
                }
                break;
        }

        // line  그리기
        //canvas.drawLine(startX,startY , stopX,stopY , paint);

        // circle 그리기
        //int radius = (int)Math.sqrt(Math.pow(stopX-startX , 2) + Math.pow(stopY-startY , 2));
        //canvas.drawCircle(startX,startY , radius , paint);
    }
}
