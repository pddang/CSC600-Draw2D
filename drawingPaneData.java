package com.draw;

public class drawingPaneData {
    private String drawingName;
    private  double width,height;

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
