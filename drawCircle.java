package com.draw;

public class drawCircle extends Shape {

    private double x,y, radius;
    private String color;

    drawCircle(double x, double y, double radius, String color){
        super(x, y, color);
        this.radius = radius;
    }


    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

}
