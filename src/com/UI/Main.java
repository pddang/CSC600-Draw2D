package com.UI;

import com.data.NewData;
import com.data.PaneEx;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    static final double INITIAL_PANE_WIDTH = 600;
    static final double INITIAL_PANE_HEIGHT = 600;
    static public PaneEx DrawingPane = NewData.newPane();
    static public Label status = new Label("Status will go here....");

    static public Stage getStage()
    {
        return (Stage) DrawingPane.getScene().getWindow();
    }
    @Override
    public void start(Stage primaryStage) {

        //MARK:: Setting content
        primaryStage.setTitle("Simple Drawing 2D Shapes");
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(MenuItems.menuBar());
        DrawingPane.setPrefSize(INITIAL_PANE_WIDTH, INITIAL_PANE_HEIGHT);
        ScrollPane scrollPane = new ScrollPane(); // Scroll pane to content all elements
        scrollPane.setPadding(new Insets(5, 5, 5, 5));
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setMinSize(500, 500);
        scrollPane.setContent(DrawingPane);
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(status);

        //MARK:: SET SCENE
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}

