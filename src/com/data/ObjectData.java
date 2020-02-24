package com.data;


import java.io.Serializable;

public class ObjectData implements Serializable {
    private long id;
    private double x,y;
    private String color;

    ObjectData(double x, double y, String color){
        this.x = x;
        this.y = y;
        this.color =color;
    }


    public ObjectData() {
        x = 0;
        y = 0;
        color = "";
        id = -1;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

}
