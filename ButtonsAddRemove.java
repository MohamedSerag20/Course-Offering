package Project;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class ButtonsAddRemove {
    private TableColumn actionCol;
    private String buttonTitle;
    private Callback<TableColumn<Section, String>, TableCell<Section, String>> cellFactory;
    private VBox vBox;
    private TilePane basket;


    public ButtonsAddRemove(String buttonTitle) {
        this.buttonTitle = buttonTitle;
        build();
    }

    public void build() {
        vBox = new VBox();
        vBox.setPadding(new Insets(15, 15, 15, 15));
        vBox.setSpacing(10);

        basket = new TilePane();
        basket.setPadding(new Insets(15));
        basket.setPrefColumns(2);


        actionCol = new TableColumn("");
        actionCol.setCellValueFactory(new PropertyValueFactory<>(""));

        cellFactory = new Callback<TableColumn<Section, String>, TableCell<Section, String>>() {
            TableCell<Section, String> cell;

            @Override
            public TableCell call(final TableColumn<Section, String> param) {
                cell = new TableCell<Section, String>() {

                    final Button btn = new Button(buttonTitle);

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Section section = getTableRow().getItem();
                                if (buttonTitle == "Add") {
                                    section.setButton(new Button(section.getCourse() + "\n" + section.getTime()));
                                    section.getButton().setPrefSize(200, 100);
                                    basket.getChildren().add(section.getButton());

                                } else {        //"Remove"
                                    if (section.getButton() == null)
                                    {

                                    } else {
                                        section.getButton().setDisable(true);
                                    }
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        actionCol.setCellFactory(cellFactory);
    }

    public TableColumn getActionCol() {
        return actionCol;
    }

    public VBox getvBox() {
        return vBox;
    }

    public TilePane getBasket()
    {
        return basket;
    }
}


