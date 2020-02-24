package com.handler;
import com.data.*;
import com.database.DatabaseUtility;
import com.UI.Main;
import java.util.List;



public class DatabaseHandler {

    public static boolean saveDrawingToDatabase()
    {
        PaneData paneData = new PaneData(Main.DrawingPane.getWidth(), Main.DrawingPane.getHeight(), Main.DrawingPane.getName());
        boolean success = DatabaseUtility.addDrawingPane(paneData);
        if(success){
        List<ObjectData> drawingObjList = DataHandler.getObjectDataList();
        for (ObjectData drawingObj : drawingObjList)
        {
            drawingObj.setId(paneData.getId());
            if( drawingObj instanceof  CircleData){
                DatabaseUtility.addCircle((CircleData) drawingObj);
                System.out.print("Add circle\n");
            }
            else if ( drawingObj instanceof RectangleData) {
                DatabaseUtility.addRectangle((RectangleData) drawingObj);
                System.out.print("Add rectangle\n");
            } else return false;
         }
        }
        return true;
    }

    public static boolean loadDrawingFromDatabase(String drawingName)
    {
        List<ObjectData> drawingObjectList = DatabaseUtility.loadDrawing(drawingName);
        if (drawingObjectList.isEmpty())
            return false;

        for (ObjectData obj : drawingObjectList)
        {
            if (obj instanceof PaneData)
            {
                PaneData paneData = (PaneData) obj;
                Main.DrawingPane.getChildren().clear();
                Main.DrawingPane.setPrefSize(paneData.getWidth(), paneData.getHeight());
                Main.DrawingPane.resize(paneData.getWidth(), paneData.getHeight());
                Main.DrawingPane.setName(paneData.getDrawingName());
                Main.DrawingPane.setModified(false);
                System.out.print("Load Pane\n");
                continue;


            }

            if (obj instanceof CircleData)
            {
                CircleData circleData = (CircleData) obj;
                Main.DrawingPane.getChildren().add(NewData.NewCircle(circleData));
                System.out.print("Load Circle\n");
                continue;
            }

            if (obj instanceof RectangleData)
            {
                RectangleData rectangleData = (RectangleData) obj;
                Main.DrawingPane.getChildren().add(NewData.NewRectangle(rectangleData));
                System.out.print("Load Rect\n");
                continue;
            }
        }

        return true;
    }

}
