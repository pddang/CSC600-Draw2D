package com.data;

import java.io.Serializable;

public class CircleData extends ObjectData implements Serializable {

    private double radius;
    public CircleData(){
        super();
        radius = 0;
    }

    public CircleData(double x, double y, double radius, String color){
        super( x, y, color);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }


}
