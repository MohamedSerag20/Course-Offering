package Project;

import javafx.scene.control.Button;

public class Section extends Course {

    //Course
    //Time (From-To) (Days)
    //Location
    private String time;
    private String day;
    private String location;
    private Button button;

    public Section(String course, String time, String day, String location) {
        super(course);
        this.time = time;
        this.day = day;
        this.location = location;
    }

    public Section(String course, String coRequisite, String preRequisite, String time, String day, String location) {
        super(course, coRequisite, preRequisite);
        this.time = time;
        this.location = location;
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Button getButton()
    {
        return button;
    }

    public void setButton(Button button)
    {
        this.button = button;
    }
}
