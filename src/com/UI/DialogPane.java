package com.UI;
import com.data.CircleData;
import com.data.RectangleData;
import com.data.PaneData;
import com.helpers.ColorMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class DialogPane {
    //MARK:: Create drawing pane dialog
    public static Dialog<PaneData> paneDialog() {
        Dialog<PaneData> dialog = new Dialog<>();
        dialog.setTitle("New Drawing Pane");
        dialog.setHeaderText("Enter drawing pane information");
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        Label widthLabel = new Label("Width: ");
        Label heightLabel = new Label("Height: ");
        Label nameLabel = new Label("Drawing Pane Name: ");
        TextField width = new TextField();
        TextField height = new TextField();
        TextField name = new TextField();

        GridPane grid = new GridPane();
        grid.addRow(0, widthLabel, width);
        grid.addRow(1, heightLabel, height);
        grid.addRow(2, nameLabel, name);

        VBox vBox = new VBox(8, grid, separator);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        dialog.getDialogPane().setContent(vBox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.setResultConverter(param -> {
            if (param != ButtonType.OK) return null;
            double getWidth = Double.parseDouble(width.getText());
            double getHeight = Double.parseDouble(height.getText());
            String getName = name.getText();
            PaneData pane = new PaneData(getWidth, getHeight, getName);
            return pane;
        });
     return dialog;
    }


    public static Dialog<CircleData> circleDialog(CircleData data) {
        Dialog<CircleData> dialog = new Dialog<>();
        if (data == null) {
            dialog.setTitle("New Circle");
            dialog.setHeaderText("Enter circle information");
        } else {
            dialog.setTitle("Modify selected circle");
            dialog.setHeaderText("Update information if the selected circle");
        }
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
            return new CircleData(xPosition, yPosition, getRadius, colorSelected );
        });

        if(data != null){
            x.setText(String.valueOf(data.getX()));
            y.setText(String.valueOf(data.getY()));
            radius.setText(String.valueOf(data.getRadius()));
            colorComboBox.getSelectionModel().select(Integer.toHexString(data.getColor().hashCode()));
        }
        return dialog;
    }

    //MARK:: DRAW RECTANGLE
    public static Dialog<RectangleData> rectangleDialog(RectangleData data) {
        Dialog<RectangleData> dialog = new Dialog<>();
        if (data == null) {
            dialog.setTitle("New Rectangle");
            dialog.setHeaderText("Enter Rectangle information");
        } else {
            dialog.setTitle("Modify selected Rectangle");
            dialog.setHeaderText("Update information if the selected Rectangle");
        }
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
            RectangleData rectangle = new RectangleData(xPosition, yPosition, getWidth, getHeight, colorSelected);
            return rectangle;
        });
        if(data != null){
            x.setText(String.valueOf(data.getX()));
            y.setText(String.valueOf(data.getY()));
            width.setText(String.valueOf(data.getWidth()));
            height.setText(String.valueOf(data.getHeight()));
            colorComboBox.getSelectionModel().select(Integer.toHexString(data.getColor().hashCode()));
        }
        return dialog;
    }

    public static void ErrorDialog(String message){
        Alert errorDlg = new Alert(Alert.AlertType.ERROR);
        errorDlg.setTitle("Simple Drawing 2D");
        errorDlg.setContentText(message);
        errorDlg.showAndWait();
    }

    public static void InfoDialog(String message)
    {
        Alert dlg = new Alert(Alert.AlertType.NONE);
        dlg.setTitle("Simple Drawing 2D");
        dlg.setContentText(message);
        dlg.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dlg.showAndWait();
    }

    public static ButtonType ConfirmationDialog(String message)
    {
        Alert alertDlg = new Alert(Alert.AlertType.CONFIRMATION);
        alertDlg.setTitle("Simple Drawing 2D");
        alertDlg.setHeaderText(message);
        alertDlg.setContentText("Press OK to confirm or CANCEL to disregard.");
        alertDlg.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> result = alertDlg.showAndWait();
        return result.get();
    }


}



