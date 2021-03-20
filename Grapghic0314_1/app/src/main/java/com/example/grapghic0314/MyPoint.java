package com.example.grapghic0314;

public class MyPoint {
    private float x, y;

    private boolean isDraw;

    private int color, width;

    public MyPoint(float x, float y, int color, int width, boolean isDraw) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.width = width;
        this.isDraw = isDraw;
    }

    public float getX() {
        return x;
    }

    public int getColor() {
        return color;
    }

    public int getWidth() {
        return width;
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
