package com.data;

import com.handler.DataHandler;
import com.handler.buttonsHandler;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class NewData {

    public static PaneEx newPane() {

        PaneEx pane = new PaneEx();
        buttonsHandler.handleMouseMovement(pane);
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        return pane;
    }

    public static CircleEx NewCircle(CircleData data){
        CircleEx circle = new CircleEx();
        circle.setCenterX(data.getX());
        circle.setCenterY(data.getY());
        circle.setRadius(data.getRadius());
        circle.setFill(Color.valueOf(data.getColor()));
        circle.setOnMouseClicked(event -> {
            DataHandler.clearSelectedItems();
            CircleEx circleEx  = (CircleEx) event.getSource();
            circleEx.setSelected(true);
            circleEx.setStroke(Color.BLACK);
            circleEx.setStrokeWidth(3);

        });
        return circle;
    }

    public static RectangleEx NewRectangle(RectangleData data){
        RectangleEx rectangle = new RectangleEx();
        rectangle.setX(data.getX());
        rectangle.setY(data.getY());
        rectangle.setWidth(data.getWidth());
        rectangle.setHeight(data.getHeight());
        rectangle.setFill(Color.valueOf(data.getColor()));

        rectangle.setOnMouseClicked(event -> {
            DataHandler.clearSelectedItems();
            RectangleEx rectangleEx  = (RectangleEx) event.getSource();
            rectangleEx.setSelected(true);
            rectangleEx.setStroke(Color.BLACK);
            rectangleEx.setStrokeWidth(3);
        });
        return rectangle;
    }

}
