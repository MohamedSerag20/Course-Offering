package Project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


public class Main extends Application {
    BorderPane pane_2 = new BorderPane();
    Scene scene;
    VBox vBox;
    public void start(Stage primaryStage)
    {
        //Panes
            //Pane1
        BorderPane pane_1 = new BorderPane(); //Label1 + Button1 + Pane2
        pane_1.setPadding(new Insets(15, 15, 15, 15));
            //Pane2
        pane_2.setPadding(new Insets(15, 15, 15, 15));

        //Labels
        Label lb1 = new Label("Add Sections To Basket");
        lb1.setFont(new Font("Calibri", 60));
        pane_1.setTop(lb1);
        BorderPane.setAlignment(lb1,Pos.TOP_CENTER);

        //Buttons
        Button bt1 = new Button("Start With \nA Saved Schedule");
        bt1.setWrapText(true);
        pane_1.setRight(bt1);
        BorderPane.setAlignment(bt1, Pos.TOP_RIGHT);

        Button bt2 = new Button("Next"); //SetOnAction
        pane_1.setBottom(bt2);
        BorderPane.setAlignment(bt2, Pos.BOTTOM_RIGHT);
        bt2.setOnAction(e -> {
            scene = new Scene(pane_2, 1350, 750);
            primaryStage.setTitle("Page2");
            primaryStage.setScene(scene);
        });

        Rectangle rectangle1 = new Rectangle();
        rectangle1.setHeight(350);
        rectangle1.setWidth(200);
        rectangle1.setFill(Color.CYAN);
        rectangle1.setStroke(Color.GRAY);
        rectangle1.setX(-50);
        rectangle1.setY(0);
        Rectangle rectangle2 = new Rectangle();
        rectangle2.setHeight(350);
        rectangle2.setWidth(200);
        rectangle2.setFill(Color.CYAN);
        rectangle2.setStroke(Color.GRAY);
        rectangle2.setX(0);
        rectangle2.setY(0);
        Rectangle rectangle3 = new Rectangle();
        rectangle3.setHeight(350);
        rectangle3.setWidth(200);
        rectangle3.setFill(Color.CYAN);
        rectangle3.setStroke(Color.GRAY);
        rectangle3.setX(650);
        rectangle3.setY(0);
        pane_2.getChildren().addAll(rectangle1, rectangle2, rectangle3);


        //UNDER MAINTENANCE
        /* Button bt5 = new Button("Return"); //Should be deleted
        pane_2.setBottom(bt5);
        BorderPane.setAlignment(bt5, Pos.BOTTOM_RIGHT);
        bt5.setOnAction(e -> {
            scene = new Scene(pane_1, 1000, 800);
            primaryStage.setTitle("Page1");
            primaryStage.setScene(scene);
        });*/

        TableViewPage1 tableBuilder = new TableViewPage1(); //Creating the table view for page1 with its Add, Remove buttons

        pane_2.setRight(tableBuilder.getAddButton().getBasket()); //Adding the vBox created in ButtonsAddRemove in hte pane_2
        pane_1.setCenter(tableBuilder.getTable());

        //Initializing The Files
        File courseOfferingFile = new File("CourseOffering 1.csv");
        File finishedCourseFile = new File("FinishedCourses.csv");
        File degreePlanFile = new File("DegreePlan.csv");

        //Finding the number of items in files by numFile method
        int numCOF = numFile(courseOfferingFile);
        int numFCF = numFile(finishedCourseFile);
        int numDPF = numFile(degreePlanFile);

        //initializing arrays for classes to fill files information inside
        Section[] sections = new Section[numCOF];
        Student[] finishedCourses = new Student[numFCF];
        Course[] courses = new Course[numDPF];


        try(Scanner scannerCOf = new Scanner(courseOfferingFile);
            Scanner scannerFC = new Scanner(finishedCourseFile);
            Scanner scannerDP = new Scanner(degreePlanFile)){

            //Filling degree plan courses in an array
            int index = 0;
            while (scannerDP.hasNext())
            {
                String[] values_p = scannerDP.nextLine().split(","); //course 0 , creditHours 1, preReq 2, coReq 3
                courses[index] = new Course(values_p[0], values_p[2], values_p[3]);
                System.out.println(courses[index].toString());
                index++;
            }

            //Filling finished courses in an array
            index = 0;
            while(scannerFC.hasNext()){
                String[] values_f = scannerFC.nextLine().split(",");
                finishedCourses[index] = new Student(values_f[0]);
                index++;
            }

            //Filling TableView By eligible Courses (matching preRequest and finishing courses conditions ) -Start
            String nothing = scannerCOf.nextLine();
            Section section;
            String line="";
            index = 0;

            while (scannerCOf.hasNext()){

                line = scannerCOf.nextLine();
                String[] values = line.split(","); //[0]:Course-Sec, [1]:Activity, [2]:CRN, [3]:Course Name, [4]:Instructor, [5]:Day, [6]:Time, [7]:Location, [8]:Status, [9]:WaitList

                String[] just_Course_section = values[0].split("-");
                String just_Course = just_Course_section[0];

                //Catching finished Courses and ensure for preRequest USING FLAG1 and FLAG2
                boolean flag1 = true;
                boolean flag2 = false;
                String preReq = returnPreRequisite(courses, just_Course);
                for(int i = 0; i < numFCF; i++) {
                    if (finishedCourses[i].getFinishedCourse().equals(just_Course))
                        flag1 = false;

                    if(finishedCourses[i].getFinishedCourse().equals(preReq))
                        flag2 = true;
                }
                if("None".equals(preReq) && flag1)
                    flag2 = true;

                //Filling the courses that skipped the restrictions successfully
                if(flag1 && flag2)
                {
                    section = new Section(values[0], values[6],values[5],values[7]);
                    sections[index] = section;
                    tableBuilder.getTable().getItems().add(section);
                    index++;
                }
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        //Filling TableView By eligible Courses (matching preRequest and finishing courses conditions ) -Start

        scene = new Scene(pane_1, 1350, 750);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Page1");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    //Method A Compute the number of items in the file
    public static int numFile(File file)
    {
        int num = 0;
        try (Scanner scanner = new Scanner(file))
        {
            while(scanner.hasNext())
            {
                num++;
                scanner.nextLine();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return num;
    }

    //Method B
    //Returning The preRequest OF The Given Course
    public static String returnPreRequisite(Course[] courses, String justCourse)
    {
        for(int i = 0; i < courses.length; i++)
        {
            if(justCourse.equals(courses[i].getCourse())) {
                return courses[i].getPreRequisite();
            }
        }
        return "None";
    }

    public static String returnCoRequisite(Course[] courses, String justCourse)
    {
        for(int i = 0; i < courses.length; i++)
        {
            if(justCourse == courses[i].getCourse())
                return courses[i].getCoRequisite();
        }
        return "None";
    }
}



