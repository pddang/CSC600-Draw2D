package com.handler;

import com.data.*;
import com.UI.Main;
import javafx.scene.Node;
import javafx.scene.shape.Shape;

import java.util.LinkedList;


public class DataHandler {

    public static LinkedList getObjectDataList(){
        LinkedList<ObjectData> objectDataList = new LinkedList<>();
        for(Node shape: Main.DrawingPane.getChildren()){
            if(shape instanceof CircleEx){
                CircleEx circle = (CircleEx) shape;
                CircleData circleData = new CircleData();
                circleData.setX(circle.getCenterX());
                circleData.setY(circle.getCenterY());
                circleData.setRadius(circle.getRadius());
                circleData.setColor(circle.getFill().toString());
                objectDataList.add(circleData);
            }
            else if ( shape instanceof RectangleEx) {
                RectangleEx rectangle = (RectangleEx) shape;
                RectangleData rectangleData = new RectangleData();
                rectangleData.setX(rectangle.getX());
                rectangleData.setY(rectangle.getY());
                rectangleData.setWidth(rectangle.getWidth());
                rectangleData.setHeight(rectangle.getHeight());
                rectangleData.setColor(rectangle.getFill().toString());
                objectDataList.add(rectangleData);
            }
        }
        return objectDataList;
    }

    public static Shape findSelectedShape(){
        for(Node shape: Main.DrawingPane.getChildren() ){
            if(shape instanceof CircleEx){
                if (((CircleEx) shape).isSelected())
                    return ((CircleEx) shape);
                System.out.print("Found circle\n");

            }
            if(shape instanceof RectangleEx){
                if (((RectangleEx) shape).isSelected())
                    return ((RectangleEx) shape);
                System.out.print("Found rectangle\n");
            }
        }
        return null;
    }

    public static void clearSelectedItems()
    {
        for (Node shape : Main.DrawingPane.getChildren())
        {
            Shape shapeObject = (Shape) shape;
            shapeObject.setStroke(shapeObject.getFill());
            shapeObject.setStrokeWidth(1.0);

            if (shape instanceof CircleEx)
            {
                ((CircleEx) shape).setSelected(false);
                continue;
            }
            if (shape instanceof RectangleEx)
            {
                ((RectangleEx) shape).setSelected(false);
                continue;
            }
        }
    }




}

