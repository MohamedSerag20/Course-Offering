package Project;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import  javafx.scene.control.Label;

import java.awt.*;

public class GridPaneTest extends Application {
    public static void main(String[] args)
    {
        launch(args);
    }


    public void start(Stage primaryStage)
    {
        ListView listView = new ListView();
        GridPane schedule = new GridPane();
        listView.getItems().add(schedule);
        listView.setPadding(new Insets(15));

        schedule.setPadding(new Insets(15));
        schedule.setGridLinesVisible(true);

        Label[] weekDays = { new Label("Sunday"), new Label("Monday"),new Label("Tuesday"),
            new Label("Wednesday"),new Label("Thursday")};
        Label[] times = {new Label("7:00"), new Label("8:00"), new Label("9:00"), new Label("10:00"),new Label("12:00"),
                new Label("1:00"),new Label("2:00"), new Label("3:00"), new Label("4:00"), new Label("5:00"), new Label("6:00"), new Label("7:00")};

        for(int i = 0; i < weekDays.length; i++)
        {
            //Filling Columns
            weekDays[i].setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 20));
            schedule.add(weekDays[i], i+1, 0);
        }

        //Filling Rows
        for (int i = 0; i < times.length; i++) {
            times[i].setAlignment(Pos.CENTER);
            schedule.add(times[i], 0, i + 1);
            schedule.setMouseTransparent(true);
        }

        ColumnConstraints[] columns = new ColumnConstraints[weekDays.length + 1]; //Plus 1 for the first Empty Column
        RowConstraints[] rows = new RowConstraints[times.length + 1]; //Plus 1 for the first Empty Row

        //First Empty Column
        columns[0] = new ColumnConstraints();
        columns[0].setPercentWidth(3);
        schedule.getColumnConstraints().add(columns[0]);

        for (int i = 0; i < weekDays.length ; i++)
        {
            columns[i+1] = new ColumnConstraints();
            columns[i+1].setPercentWidth(20);
            columns[i+1].setHalignment(HPos.CENTER);
            schedule.getColumnConstraints().add(columns[i+1]);
        }

        //First Empty Row
        rows[0] = new RowConstraints();
        rows[0].setPrefHeight(20);
        schedule.getRowConstraints().add(rows[0]);

        for (int i = 0; i < times.length ; i++)
        {
            rows[i+1] = new RowConstraints();
            rows[i+1].setPrefHeight(50);
            rows[i+1].setValignment(VPos.CENTER);
            rows[i+1].setFillHeight(true);
            schedule.getRowConstraints().add(rows[i+1]);
        }


        schedule.setVgap(50);

        Scene scene = new Scene(listView, 1350, 750);
        scene.setFill(Color.CYAN);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Test");
        primaryStage.show();

    }


}
