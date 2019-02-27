package com.draw;



public class Shape {
    private double x,y;
    private String color;

    Shape(double x, double y, String color){
        this.x = x;
        this.y = y;
        this.color = color;
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
