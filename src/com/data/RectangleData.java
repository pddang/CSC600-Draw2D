package com.data;

import java.io.Serializable;

public class RectangleData extends ObjectData implements Serializable {

    private double width,height;
    public RectangleData(){
        super();
        width = 0;
        height = 0;
    }
    public RectangleData(double x, double y, double width, double height, String color){
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

