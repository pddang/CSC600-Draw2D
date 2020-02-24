package com.data;

import java.io.Serializable;

public class PaneData extends ObjectData implements Serializable {
    private String drawingName;
    private  double width,height;

    public PaneData(double width, double height, String drawingName){
        super();
        this.width = width;
        this.height = height;
        this.drawingName = drawingName;
    }
    public PaneData() {
        super();
        width = 0;
        height = 0;
        drawingName = "Untitled";
    }
    public String getDrawingName() {
        return drawingName;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void setDrawingName(String drawingName) {
        this.drawingName = drawingName;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
