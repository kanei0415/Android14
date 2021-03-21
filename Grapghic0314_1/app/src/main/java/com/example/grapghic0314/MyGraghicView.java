package com.example.grapghic0314;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

class MyGraphicView extends View {
    public static final String  PAINT_MODE = "paint",
            DRAW_MODE = "draw",
            COLOR_MODE = "color",
            LOAD_MODE = "load";

    private int startX = -1, startY = -1;
    private int stopX = -1, stopY = -1;
    private String mode = PAINT_MODE;

    MainActivity mainActivity;

    Paint paint;

    public Paint getPaint() {
        return paint;
    }

    ArrayList<MyPoint> arrayList = new ArrayList<>();

    public MyGraphicView(Context context) {
        super(context);
        mainActivity = (MainActivity)context;
        initPaint();
    }

    public void setMode(String mode) {
        this.mode = mode;
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
                        arrayList.add(new MyPoint(event.getX(),event.getY(),
                                paint.getColor(), (int)paint.getStrokeWidth(),false));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        arrayList.add(new MyPoint(event.getX(),event.getY(),
                                paint.getColor(), (int)paint.getStrokeWidth(),true));
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
        paint.setColor(Color.BLACK);
    }

    public void drawBackGround(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        int x = (getWidth() - bitmap.getWidth()) / 2;
        int y = (getHeight() - bitmap.getHeight()) / 2;
        canvas.drawBitmap(bitmap, x, y,null);
        bitmap.recycle();
    }

    public void loadBackGround(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeFile(mainActivity.getPath());
        int x = (getWidth() - bitmap.getWidth()) / 2;
        int y = (getHeight() - bitmap.getHeight()) / 2;
        canvas.drawBitmap(bitmap, x, y,null);
        bitmap.recycle();
    }

    public void drawFreeLine(Canvas canvas) {
        for(int i=1 ; i<arrayList.size() ; i++) {
            if(arrayList.get(i).isDraw()) {
                paint.setColor(arrayList.get(i-1).getColor());
                paint.setStrokeWidth(arrayList.get(i-1).getWidth());
                canvas.drawLine(
                        arrayList.get(i-1).getX(),arrayList.get(i-1).getY(),
                        arrayList.get(i).getX(),arrayList.get(i).getY(),
                        paint
                );
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(mode.equals(COLOR_MODE)) {
            drawBackGround(canvas);
        } else if(mode.equals(LOAD_MODE)) {
            loadBackGround(canvas);
        }

        switch (mainActivity.getDrawType()) {
            case MainActivity.LINE:
                canvas.drawLine(startX,startY , stopX,stopY , paint);
                break;
            case MainActivity.CIRCLE:
                int radius = (int)Math.sqrt(
                        Math.pow(stopX-startX,2) + Math.pow(stopY-startY,2)
                );
                canvas.drawCircle(startX,startY , radius , paint);
                break;
            case MainActivity.FREE:
                drawFreeLine(canvas);
                break;
        }
    }
}
