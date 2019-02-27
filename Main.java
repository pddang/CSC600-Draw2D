package com.draw;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import java.util.Optional;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //MARK:: Setting MenuBar
        MenuBar menuBar = new MenuBar();
        // --- Menu File
        Menu menuFile = new Menu("File");
        MenuItem newItem = new MenuItem("New Drawing Pane...");
        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        SeparatorMenuItem separatorMenuItem1 = new SeparatorMenuItem();
        SeparatorMenuItem separatorMenuItem2 = new SeparatorMenuItem();

        MenuItem saveItem = new MenuItem("Save Drawing To Disk...");
        MenuItem loadItem = new MenuItem("Load Drawing From Disk...");
        MenuItem saveItemDatabase = new MenuItem("Save Drawing To Database...");
        MenuItem loadItemDatabase = new MenuItem("Load Drawing From Database...");
        MenuItem deleteItemDatabase = new MenuItem("Delete Drawing From Database...");
        MenuItem exit = new MenuItem("Exit");

        menuFile.getItems().add(newItem);
        menuFile.getItems().add(separatorMenuItem);
        menuFile.getItems().addAll(saveItem, loadItem);
        menuFile.getItems().add(separatorMenuItem1);
        menuFile.getItems().addAll(saveItemDatabase, loadItemDatabase, deleteItemDatabase);
        menuFile.getItems().add(separatorMenuItem2);
        menuFile.getItems().add(exit);

        // --- Menu Edit
        Menu menuEdit = new Menu("Edit");
        MenuItem modify = new MenuItem("Modify");
        MenuItem delete = new MenuItem("Delete");

        menuEdit.getItems().addAll(modify, delete);

        // --- Draw Edit
        Menu menuDraw = new Menu("Draw");
        MenuItem drawCircle = new MenuItem("Circle");
        MenuItem drawRectangle = new MenuItem("Rectangle");
        menuDraw.getItems().addAll(drawCircle, drawRectangle);

        menuBar.getMenus().addAll(menuFile, menuEdit, menuDraw);

        //MARK:: Setting content
        primaryStage.setTitle("Simple Drawing 2D Shapes");
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        Pane pane = new Pane();//Pane to contain elements
        ScrollPane content = new ScrollPane(); // Scroll pane to content all elements
        content.setPadding(new Insets(5,5,5,5));
        content.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        content.setMinSize(500, 500);
        borderPane.setCenter(content);
        Label status = new Label("Status will go here....");
        borderPane.setBottom(status);
        //MARK:: Set actions for draw menu
        drawCircle.setOnAction(event -> {
            Circle circle = new Circle();
            drawCircle myCircle = displayCircleDialog();
            circle.setCenterX(myCircle.getX());
            circle.setCenterY(myCircle.getY());
            circle.setRadius(myCircle.getRadius());
            circle.setFill(Color.valueOf(myCircle.getColor()));
            isSelected(circle);
            pane.getChildren().add(circle);
        });
        drawRectangle.setOnAction(event -> {
            Rectangle rectangle = new Rectangle();
            drawRectangle myRectangle =  displayRectangleDialog();
            rectangle.setX(myRectangle.getX());
            rectangle.setY(myRectangle.getY());
            rectangle.setWidth(myRectangle.getWidth());
            rectangle.setHeight(myRectangle.getHeight());
            rectangle.setFill(Color.valueOf(myRectangle.getColor()));
            isSelected(rectangle);
            pane.getChildren().add(rectangle);
        });
        content.setContent(pane);   // set content to Scroll Pane
        //MARK:: Set actions for Edit menu
        //TODO: setting up delete and modify actions
        delete.setOnAction(event -> {



        });
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }


//MARK:: DRAW CIRCLE
    private drawCircle displayCircleDialog() {
        Dialog<drawCircle> dialog = new Dialog<>();
        dialog.setTitle("New circle");
        dialog.setHeaderText("Enter circle information");
        dialog.setResizable(false);
        Label xLabel = new Label("X-Position: ");
        Label yLabel = new Label("Y-Position: ");
        Label radiusLabel = new Label("Radius: ");
        Label colorLabel = new Label("Fill-Color: ");
        TextField x = new TextField();
        TextField y = new TextField();
        TextField radius = new TextField();
        ObservableList<String> colors = FXCollections.observableArrayList(ColorMap.getJavaFXColorMap().keySet());
        FXCollections.sort(colors);
        ComboBox colorComboBox = new ComboBox(colors);
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        GridPane gridPane = new GridPane();
        gridPane.addRow(0, xLabel, x);
        gridPane.addRow(1, yLabel, y);
        gridPane.addRow(2, radiusLabel, radius);
        gridPane.addRow(3, colorLabel, colorComboBox);
        gridPane.setVgap(5);
        gridPane.setVgap(5);
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(8);
        vbox.getChildren().addAll(gridPane, separator);
        dialog.getDialogPane().setContent(vbox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.setResultConverter(param -> {
            if (param != ButtonType.OK) return null;
            double xPosition = Double.parseDouble(x.getText());
            double yPosition = Double.parseDouble(y.getText());
            double getRadius = Double.parseDouble(radius.getText());
            String colorSelected = colorComboBox.getSelectionModel().getSelectedItem().toString();
            drawCircle circle = new drawCircle(xPosition, yPosition,getRadius, colorSelected);
            return circle;
        });
        Optional<drawCircle> result = dialog.showAndWait();
        if (!result.isPresent()) return null;
        return result.get();
    }


    //MARK:: DRAW RECTANGLE
    private drawRectangle displayRectangleDialog() {
        Dialog<drawRectangle> dialog = new Dialog<>();
        dialog.setTitle("New Rectangle");
        dialog.setHeaderText("Enter rectangle information");
        dialog.setResizable(false);
        Label xLabel = new Label("X-Position: ");
        Label yLabel = new Label("Y-Position: ");
        Label widthLabel = new Label("Width: ");
        Label heightLabel = new Label("Height: ");
        Label colorLabel = new Label("Fill-Color: ");
        TextField x = new TextField();
        TextField y = new TextField();
        TextField width = new TextField();
        TextField height = new TextField();
        ObservableList<String> colors = FXCollections.observableArrayList(ColorMap.getJavaFXColorMap().keySet());
        FXCollections.sort(colors);
        ComboBox colorComboBox = new ComboBox(colors);
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        GridPane gridPane = new GridPane();
        gridPane.addRow(0, xLabel, x);
        gridPane.addRow(1, yLabel, y);
        gridPane.addRow(2, widthLabel, width);
        gridPane.addRow(3, heightLabel, height);
        gridPane.addRow(4, colorLabel, colorComboBox);
        gridPane.setVgap(5);
        gridPane.setVgap(5);
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(8);
        vbox.getChildren().addAll(gridPane, separator);
        dialog.getDialogPane().setContent(vbox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.setResultConverter(param -> {
            if (param != ButtonType.OK) return null;
            double xPosition = Double.parseDouble(x.getText());
            double yPosition = Double.parseDouble(y.getText());
            double getWidth = Double.parseDouble(width.getText());
            double getHeight = Double.parseDouble(height.getText());
            String colorSelected = colorComboBox.getSelectionModel().getSelectedItem().toString();
            drawRectangle rectangle = new drawRectangle(xPosition, yPosition,getWidth, getHeight, colorSelected);
            return rectangle;
        });
        Optional<drawRectangle> result = dialog.showAndWait();
        if (!result.isPresent()) return null;
        return result.get();
    }
    //MARK:: Setting Mouse pressed events
    public boolean isSelected(Shape shape){
        EventHandler<MouseEvent> eventHandler;
        if(true){
            eventHandler = event -> {
                shape.setStroke(Color.BLACK);
                shape.setStrokeWidth(3);
            };
            shape.addEventFilter(MouseEvent.MOUSE_CLICKED,eventHandler);
        }
      return false;
    }
}