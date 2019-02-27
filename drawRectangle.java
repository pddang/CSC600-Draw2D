package com.draw;

public class drawRectangle  extends Shape {

    private double width,height, x, y;
    drawRectangle(double x, double y,double width, double height, String color ){
        super(x,y,color);
        this.width = width;
        this.height = height;
    }
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}

