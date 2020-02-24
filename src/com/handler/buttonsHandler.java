package com.handler;

import com.data.*;
import com.database.DatabaseUtility;
import com.UI.DialogPane;
import com.UI.Main;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import java.io.File;
import java.util.List;
import java.util.Optional;

public class buttonsHandler {

    public static void handleNewDrawingPane(){
           if(Main.DrawingPane.isModified()){
               if(DialogPane.ConfirmationDialog("Creating new drawing will override the existing drawing. \n Are you sure? ") != ButtonType.OK) return;
           }
           Dialog<PaneData> dialog = DialogPane.paneDialog();
           Optional<PaneData> result = dialog.showAndWait();
           if(result.isPresent()){
               PaneData data = result.get();
               if(!DatabaseUtility.checkDrawingExit(data.getDrawingName())){
                   Main.DrawingPane.getChildren().clear();
                   Main.DrawingPane.setPrefSize(data.getWidth(),data.getHeight());
                   Main.DrawingPane.resize(data.getWidth(),data.getHeight());
                   Main.DrawingPane.setName(data.getDrawingName());
                   Main.DrawingPane.setModified(false);
               } else {
                  DialogPane.ErrorDialog("Drawing is already existed.");
               }
           }
    }

    public static void handleSavingDrawingToDisk(){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Drawing");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Drawing Files", "*.drw"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showSaveDialog(Main.getStage());
        if(selectedFile != null) {
           if(!LocalHandler.saveDrawingToFile(selectedFile)){
               DialogPane.ErrorDialog("Error saving drawing to file.");
           }
       }
    }

    public static void handleLoadingDrawingFromDisk(){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Drawing");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Drawing Files", "*.drw"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(Main.getStage());
        if(selectedFile != null) {
            if(!LocalHandler.loadDrawingFromFile(selectedFile)){
                DialogPane.ErrorDialog("Error loading drawing to file.");
            }
        }
    }

    public static void handleSavingDrawingToDatabase() {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Save drawing to database");
        inputDialog.setHeaderText("Enter a name to save drawing");
        Optional<String> result = inputDialog.showAndWait();
        if(result.isPresent()){
            String newDrawingName = result.get();
            boolean exist = DatabaseUtility.checkDrawingExit(newDrawingName);
            if (exist) {
                if (DialogPane.ConfirmationDialog("Saving same drawing name will override the existing drawing. \n Are you sure? ") != ButtonType.OK)
                    return;
            }
            Main.DrawingPane.setName(newDrawingName);
            if (!DatabaseHandler.saveDrawingToDatabase()) {
                DialogPane.ErrorDialog(String.format("Unable to save drawing (%s) to database!", newDrawingName));
            }
            System.out.print("Save successfully \n");
        }


    }

    public static void handleLoadingDrawingFromDatabase(){


        List<String> list = DatabaseUtility.getDrawingName();
        if(list.isEmpty()){
            DialogPane.ErrorDialog("No drawings found in the database!");
            return;
        }
        ChoiceDialog dialog = new ChoiceDialog(list.get(0),list.toArray());
        dialog.setTitle("Simple Drawing 2D");
        dialog.setHeaderText("Loading Drawing from Database: ");
        dialog.setContentText("Choose a drawing name to load: ");
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent() && !DatabaseHandler.loadDrawingFromDatabase(result.get())){
            DialogPane.ErrorDialog("Error loading drawing from database");
        }

    }

    public static void handleDeletingDrawingFromDatabase(){

        List<String> list = DatabaseUtility.getDrawingName();
        if(list.isEmpty()) {
            DialogPane.ErrorDialog("No Drawing found in the database");
            return;
        }
        ChoiceDialog dialog = new ChoiceDialog(list.get(0),list.toArray());
        dialog.setTitle("Simple Drawing 2D");
        dialog.setHeaderText("Deleting Drawing from Database: ");
        dialog.setContentText("Choose a drawing name to delete: ");
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent() && !DatabaseUtility.deleteDrawing(result.get())){
            DialogPane.ErrorDialog("Error deleting drawing from database");
        }

    }


    public static void handleExiting(){
        Platform.exit();
    }

    public static void handleDrawingCircle(){
            Dialog<CircleData> circleDialog = DialogPane.circleDialog(null );
            Optional<CircleData> result = circleDialog.showAndWait();
            if (result.isPresent()) {
                CircleData data = result.get();
                Main.DrawingPane.getChildren().add(NewData.NewCircle(data));
            }
    }
    public static void handleDrawingRectangle(){

        Dialog<RectangleData> rectangleDialog = DialogPane.rectangleDialog(null );
        Optional<RectangleData> result = rectangleDialog.showAndWait();
        if (result.isPresent()) {
            RectangleData data = result.get();
            Main.DrawingPane.getChildren().add(NewData.NewRectangle(data));
        }

    }


    public static void handleModifyingObject(){

        Shape selectedShape = DataHandler.findSelectedShape();
        if (null == selectedShape)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modify Selected Object");
            alert.setHeaderText("No selected objects found!");
            alert.showAndWait();
            return;
        }

        if (selectedShape instanceof CircleEx)
        {
            CircleEx circle = (CircleEx) selectedShape;
            CircleData circleData = new CircleData();
            circleData.setX(circle.getCenterX());
            circleData.setY(circle.getCenterY());
            circleData.setRadius(circle.getRadius());
            circleData.setColor(circle.getFill().toString());

            Dialog<CircleData> dlg = DialogPane.circleDialog(circleData);
            Optional<CircleData> result = dlg.showAndWait();
            if (result.isPresent())
            {
                circleData = result.get();
                circle.setCenterX(circleData.getX());
                circle.setCenterY(circleData.getY());
                circle.setRadius(circleData.getRadius());
                circle.setFill(Color.valueOf(circleData.getColor()));
                Main.DrawingPane.setModified(true);
            }
            return;
        }

        if (selectedShape instanceof RectangleEx)
        {
            RectangleEx rectangle = (RectangleEx) selectedShape;
            RectangleData rectangleData = new RectangleData();
            rectangleData.setX(rectangle.getX());
            rectangleData.setY(rectangle.getY());
            rectangleData.setWidth(rectangle.getWidth());
            rectangleData.setHeight(rectangle.getHeight());
            rectangleData.setColor(rectangle.getFill().toString());

            Dialog<RectangleData> dlg = DialogPane.rectangleDialog(rectangleData);
            Optional<RectangleData> result = dlg.showAndWait();
            if (result.isPresent())
            {
                rectangleData = result.get();
                rectangle.setX(rectangleData.getX());
                rectangle.setY(rectangleData.getY());
                rectangle.setWidth(rectangleData.getWidth());
                rectangle.setHeight(rectangleData.getHeight());
                rectangle.setFill(Color.valueOf(rectangleData.getColor()));
                Main.DrawingPane.setModified(true);
            }
            return;
        }
    }

    public static void handleDeletingObject(){
        Shape selectedShape= DataHandler.findSelectedShape();
        if (null == selectedShape)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Delete Selected Object");
            alert.setHeaderText("No selected objects found!");
            alert.showAndWait();
            return;
        }

        for (Node shape : Main.DrawingPane.getChildren())
        {
            if (shape == selectedShape)
            {
                Main.DrawingPane.getChildren().remove(selectedShape);
                Main.DrawingPane.setModified(true);
                return;
            }
        }
    }



    public static void handleMouseMovement(Pane pane){
        pane.setOnMouseMoved(event -> {
            if(event == null) Main.status.setText("");
            else {
                Main.status.setText(String.format("[ DRAWING: %s  (W: %.04f, H: %.04f) ]   X = %.04f, Y = %.04f", Main.DrawingPane.getName(),
                        Double.valueOf(Main.DrawingPane.getWidth()),
                        Double.valueOf(Main.DrawingPane.getHeight()),
                        Double.valueOf(event.getX()),
                        Double.valueOf(event.getY())));
            }
        });
    }


}

