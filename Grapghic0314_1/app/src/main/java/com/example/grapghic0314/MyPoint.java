package com.example.grapghic0314;

public class MyPoint {
    private float x, y;

    private boolean isDraw;

    public MyPoint(float x, float y, boolean isDraw) {
        this.x = x;
        this.y = y;
        this.isDraw = isDraw;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
    }
}
