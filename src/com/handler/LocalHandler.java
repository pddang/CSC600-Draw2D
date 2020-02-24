package com.handler;
import com.data.*;
import com.UI.Main;
import java.io.*;
import java.util.List;

public class LocalHandler {

    public static boolean saveDrawingToFile(File drawingFile)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(drawingFile)))
        {
            PaneData paneData = new PaneData(Main.DrawingPane.getWidth(), Main.DrawingPane.getHeight(),Main.DrawingPane.getName());
            oos.writeObject(paneData);

            List<ObjectData> drawingObjList = DataHandler.getObjectDataList();
            for (ObjectData obj : drawingObjList)
            {
                oos.writeObject(obj);
                System.out.print("Write object successfully\n");
            }
            return true;
        }

        catch (IOException ex)
        {
            System.out.printf("%s\n", ex.getMessage());
            return false;
        }
    }

    public static boolean loadDrawingFromFile(File fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName)))
        {
            Main.DrawingPane.getChildren().clear();
            while (true)
            {
                Object obj = ois.readObject();

                if (obj instanceof PaneData)
                {
                    PaneData paneData = (PaneData) obj;
                    Main.DrawingPane.setPrefSize(paneData.getWidth(), paneData.getHeight());
                    Main.DrawingPane.resize(paneData.getWidth(), paneData.getHeight());
                    Main.DrawingPane.setName(paneData.getDrawingName());
                    Main.DrawingPane.setModified(false);
                    continue;
                }
                if (obj instanceof CircleData)
                {
                    CircleData circleData = (CircleData) obj;
                    Main.DrawingPane.getChildren().add(NewData.NewCircle(circleData));
                    continue;
                }

                if (obj instanceof RectangleData)
                {
                    RectangleData rectangleData = (RectangleData) obj;
                    Main.DrawingPane.getChildren().add(NewData.NewRectangle(rectangleData));
                    continue;
                }
                System.out.print("Read object successfully\n");
            }
        }

        catch (EOFException ex)
        {
            System.out.printf("EOF Exception - safe");
            return true;
        }

        catch (ClassNotFoundException | IOException ex)
        {
            System.out.printf("Unable to load drawing from file: %s\n", fileName.getPath());
            System.out.printf("%s\n", ex.getMessage());
            return false;
        }

    }

}
