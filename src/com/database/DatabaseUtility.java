package com.database;

import com.data.CircleData;
import com.data.PaneData;
import com.data.RectangleData;
import com.data.ObjectData;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DatabaseUtility {

    //SQL statements
    private static final String PANE_QUERIES = "select id, width, height from pane where drawing_name = ?";
    private static final String CIRCLE_QUERIES = "select x, y, radius, color_name from circle where pane_id = ?";
    private static final String RECTANGLE_QUERIES = "select x, y, width, height, color_name from rectangle where pane_id = ?";
    private static final String PANE_INSERT_QUERIES = "insert into pane ( width, height, drawing_name) values (?,?,?)";
    private static final String CIRCLE_INSERT_QUERIES = "insert into circle( x, y, radius, color_name, pane_id) values (?,?,?,?,?)";
    private static final String RECTANGLE_INSERT_QUERIES = "insert into rectangle(x, y, width, height, color_name, pane_id) values (?,?,?,?,?,?)";
    private static final String CHECK_DRAWING_QUERIES = "select count(*) as drawing_name from pane where drawing_name = ?";
    private static final String DELETE_DRAWING_QUERIES = "delete from pane where drawing_name = ?";


    public static LinkedList<ObjectData> loadDrawing(String drawingName){

        try {
            LinkedList<ObjectData> drawingList = new LinkedList<>();
            DataSource ds = ConfigurationUtility.getDataSource();
            try (Connection connection = DriverManager.getConnection(ds.getConnection(), ds.getUserName(), ds.getUserPassword())) {
                PreparedStatement statement = connection.prepareStatement(PANE_QUERIES);
                statement.setString(1, drawingName);

                /// PANE DATA
                try (ResultSet resultSet = statement.executeQuery()) {
                    System.out.println("Connected successfully!");
                    while (resultSet.next()) {
                        PaneData paneData = new PaneData();
                        paneData.setDrawingName(drawingName);
                        paneData.setId(resultSet.getLong("ID"));
                        paneData.setWidth(resultSet.getDouble("WIDTH"));
                        paneData.setHeight(resultSet.getDouble("HEIGHT"));
                        drawingList.add(paneData);
                    }
                }
                ///CIRCLE

                PaneData paneData = (PaneData) drawingList.getFirst();
                statement = connection.prepareStatement(CIRCLE_QUERIES);
                statement.setLong(1, paneData.getId());

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        CircleData circleData = new CircleData();
                        circleData.setX(resultSet.getDouble("x"));
                        circleData.setY(resultSet.getDouble("y"));
                        circleData.setRadius(resultSet.getDouble("radius"));
                        circleData.setColor(resultSet.getString("color_name"));
                        circleData.setId(paneData.getId());
                        drawingList.add(circleData);
                    }

                }


                statement = connection.prepareStatement(RECTANGLE_QUERIES);
                statement.setLong(1, paneData.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        RectangleData recData = new RectangleData();
                        recData.setX(resultSet.getDouble("x"));
                        recData.setY(resultSet.getDouble("y"));
                        recData.setWidth(resultSet.getDouble("width"));
                        recData.setHeight(resultSet.getDouble("height"));
                        recData.setColor(resultSet.getString("color_name"));
                        recData.setId(paneData.getId());
                        drawingList.add(recData);
                    }

                }
            }
            return drawingList;
        } catch (Exception ex){
            System.out.print("Something went wrong!");
            ex.printStackTrace();
            return new LinkedList<>();
        }
    }


    public static boolean addDrawingPane(PaneData paneData){
        boolean success;
        try {
            DataSource ds = ConfigurationUtility.getDataSource();
            try {
                Connection connection = DriverManager.getConnection(ds.getConnection(),ds.getUserName(),ds.getUserPassword());
                PreparedStatement statement = connection.prepareStatement(PANE_INSERT_QUERIES, Statement.RETURN_GENERATED_KEYS);
                int index = 1;
                statement.setDouble(index++, paneData.getWidth());
                statement.setDouble(index++, paneData.getHeight());
                statement.setString(index++, paneData.getDrawingName());
                int count = statement.executeUpdate();
                if(count >= 1) success = true;
                else success = false;
                try (ResultSet generatedKeys = statement.getGeneratedKeys()){
                    if(generatedKeys.next()) {
                        paneData.setId(generatedKeys.getLong(1));

                    } else {
                        paneData.setId(-1);
                        throw new SQLException("Error creating pane - No ID");
                    }

                }
            } catch (Exception ex ){
                ex.printStackTrace();
                success = false;
            }
        } catch (Exception ex){
            System.out.print("Something went wrong");
            System.out.print(ex.getMessage());
            success = false;
        }
        return success;
    }

    public static boolean addCircle(CircleData circleData){

        boolean success;

        try {
            DataSource ds = ConfigurationUtility.getDataSource();
            try {
                Connection connection = DriverManager.getConnection(ds.getConnection(),ds.getUserName(),ds.getUserPassword());
                PreparedStatement statement = connection.prepareStatement(CIRCLE_INSERT_QUERIES);
                int index = 1;
                statement.setDouble(index++, circleData.getX());
                statement.setDouble(index++, circleData.getY());
                statement.setDouble(index++,circleData.getRadius());
                statement.setString(index++, circleData.getColor());
                statement.setLong(index++, circleData.getId());
                int count = statement.executeUpdate();
                if(count >= 1) success = true;
                else success = false;
                } catch (Exception ex ){
                ex.printStackTrace();
                success = false;
            }
        } catch (Exception ex){
            System.out.print("Something went wrong");
            System.out.print(ex.getMessage());
            success = false;
        }
        return success;
    }

    public static boolean addRectangle(RectangleData rectangleData){

        boolean success;
        try {
            DataSource ds = ConfigurationUtility.getDataSource();
            try {
                Connection connection = DriverManager.getConnection(ds.getConnection(),ds.getUserName(),ds.getUserPassword());
                PreparedStatement statement = connection.prepareStatement(RECTANGLE_INSERT_QUERIES);
                int index = 1;
                statement.setDouble(index++,rectangleData.getX());
                statement.setDouble(index++, rectangleData.getY());
                statement.setDouble(index++, rectangleData.getWidth());
                statement.setDouble(index++, rectangleData.getHeight());
                statement.setString(index++, rectangleData.getColor());
                statement.setLong(index++, rectangleData.getId());
                int count = statement.executeUpdate();
                if(count >= 1) success = true;
                else success = false;
            } catch (Exception ex ){
                ex.printStackTrace();
                success = false;
            }
        } catch (Exception ex){
            System.out.print("Something went wrong!");
            System.out.print(ex.getMessage());
            success = false;
        }
        return success;
    }

    public static boolean checkDrawingExit(String drawingName){
        boolean exist = false;
        try {
            DataSource ds = ConfigurationUtility.getDataSource();
            try (Connection connection = DriverManager.getConnection(ds.getConnection(),ds.getUserName(),ds.getUserPassword()))
            {
                PreparedStatement statement = connection.prepareStatement(CHECK_DRAWING_QUERIES);
                statement.setString(1,drawingName);
                try (ResultSet resultSet = statement.executeQuery()){
                    if(resultSet.next())
                        exist = resultSet.getInt("drawing_name")> 0 ? true : false;
                }  catch (Exception ex){
                    ex.printStackTrace();
                    exist = false;
                }
            } catch (Exception ex){
                ex.printStackTrace();
                exist = false;
            }
        } catch (Exception ex){
            ex.printStackTrace();
            exist = false;
        }
        return  exist;
    }


    public static boolean deleteDrawing(String drawingName){
        boolean success;
        try {
            DataSource ds = ConfigurationUtility.getDataSource();
            Connection connection = DriverManager.getConnection(ds.getConnection(),ds.getUserName(),ds.getUserPassword());
            PreparedStatement statement = connection.prepareStatement(DELETE_DRAWING_QUERIES);
            statement.setString(1,drawingName);
            statement.setString(1, drawingName);
            success = (statement.executeUpdate() > 0) ? true : false;

        } catch (Exception ex){
            ex.printStackTrace();
            success = false;
        }
        return  success;
    }

    public static List<String> getDrawingName(){
        LinkedList<String> drawingNameList = new LinkedList<>();
        DataSource ds = ConfigurationUtility.getDataSource();
        try(Connection connection = DriverManager.getConnection(ds.getConnection(),ds.getUserName(),ds.getUserPassword()))
        {
            Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery("select drawing_name from pane");
           while (resultSet.next()){
               drawingNameList.add(resultSet.getString("drawing_name"));
           }
        } catch (Exception ex){
            ex.printStackTrace();
            return new LinkedList<>();
        }
        return drawingNameList;
    }

}
