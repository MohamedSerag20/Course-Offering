package Project;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewPage1{

    private ButtonsAddRemove add, remove;
    private TableView<Section> table;


    public TableViewPage1()
    {
        build();
    }

    public void build()
    {
        table = new TableView<>();

        TableColumn<Section,String> course_sec = new TableColumn<>("Course - Sec");
        course_sec.setCellValueFactory(new PropertyValueFactory<>("Course"));

        TableColumn<Section,String> day = new TableColumn<>("Day");
        day.setCellValueFactory(new PropertyValueFactory<>("Day"));

        TableColumn<Section,String> time = new TableColumn<>("Time");
        time.setCellValueFactory(new PropertyValueFactory<>("Time"));

        TableColumn<Section,String> location = new TableColumn<>("Location");
        location.setCellValueFactory(new PropertyValueFactory<>("Location"));

        add = new ButtonsAddRemove("Add");
        remove = new ButtonsAddRemove("Remove");

        table.getColumns().addAll(course_sec, time, day, location, add.getActionCol(), remove.getActionCol());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public TableView getTable()
    {
        return table;
    }

    public ButtonsAddRemove getAddButton()
    {
        return add;
    }

    public ButtonsAddRemove getRemoveButton()
    {
        return remove;
    }

};