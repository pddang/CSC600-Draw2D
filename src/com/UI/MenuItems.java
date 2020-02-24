package com.UI;
import com.handler.buttonsHandler;
import javafx.scene.control.*;

public class MenuItems {

    public static MenuBar menuBar() {
        MenuBar menuBar = new MenuBar();
        MenuItem newPaneItem = new MenuItem("New Drawing Pane...");
        MenuItem saveItem = new MenuItem("Save Drawing To Disk...");
        MenuItem loadItem = new MenuItem("Load Drawing From Disk...");
        MenuItem saveItemDatabase = new MenuItem("Save Drawing To Database...");
        MenuItem loadItemDatabase = new MenuItem("Load Drawing From Database...");
        MenuItem deleteItemDatabase = new MenuItem("Delete Drawing From Database...");
        MenuItem exit = new MenuItem("Exit");

        MenuItem drawCircle = new MenuItem("Circle");
        MenuItem drawRectangle = new MenuItem("Rectangle");
        // --- Menu File
        Menu menuFile = new Menu("File");
        menuFile.getItems().addAll(newPaneItem, new SeparatorMenuItem(), saveItem, loadItem,
                new SeparatorMenuItem(), saveItemDatabase, loadItemDatabase, deleteItemDatabase, new SeparatorMenuItem(), exit);
        // --- Menu Edit
        Menu menuEdit = new Menu("Edit");
        MenuItem modify = new MenuItem("Modify");
        MenuItem delete = new MenuItem("Delete");
        menuEdit.getItems().addAll(modify, delete);

        // --- Draw Edit
        Menu menuDraw = new Menu("Draw");
        menuDraw.getItems().addAll(drawCircle, drawRectangle);
        menuBar.getMenus().addAll(menuFile, menuEdit, menuDraw);

        //Handle action items
        newPaneItem.setOnAction(event -> buttonsHandler.handleNewDrawingPane());
        saveItem.setOnAction(event -> buttonsHandler.handleSavingDrawingToDisk());
        loadItem.setOnAction(event -> buttonsHandler.handleLoadingDrawingFromDisk());
        saveItemDatabase.setOnAction(event -> buttonsHandler.handleSavingDrawingToDatabase());
        loadItemDatabase.setOnAction(event -> buttonsHandler.handleLoadingDrawingFromDatabase());
        deleteItemDatabase.setOnAction(event -> buttonsHandler.handleDeletingDrawingFromDatabase());
        exit.setOnAction(event -> buttonsHandler.handleExiting());

        drawCircle.setOnAction(event -> buttonsHandler.handleDrawingCircle());
        drawRectangle.setOnAction(event -> buttonsHandler.handleDrawingRectangle());
        modify.setOnAction(event -> buttonsHandler.handleModifyingObject());
        delete.setOnAction(event -> buttonsHandler.handleDeletingObject());

        return menuBar;
    }

}
