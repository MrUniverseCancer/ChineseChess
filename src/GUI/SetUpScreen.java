package GUI;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class SetUpScreen
{
    private Pane SetUp_Pane;
    private int priority;
    private dataField dataField;
    //红先为1，否则为0；

    private int direction;
    public SetUpScreen() {
    }

    public Pane getSetUpScreen(dataField dataField)
    {
        SetUp_Pane = new Pane();
        this.dataField = dataField;
        SetUp_Pane.setStyle("-fx-background-color: #f0f8ff;");
        SetUp_Pane.setPrefSize(1250, 750);
        SetUp_Pane.setVisible(false);
        ImageView back = setBackGround();
        SetUp_Pane.getChildren().add(back);
        Label label = setlabel();
        SetUp_Pane.getChildren().add(label);
        Button return_Button = setRuturnButton();
        SetUp_Pane.getChildren().add(return_Button);
        Pane userPriority = setPriority();
        SetUp_Pane.getChildren().add(userPriority);



        return SetUp_Pane;
    }

    public Pane setPriority()
    {
        int length = 200;
        Pane userPriority = new Pane();
        ToggleGroup group = new ToggleGroup();

        Label label = new Label("选择下方");
        label.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial'; -fx-text-fill: blue;");
        label.setLayoutX(0);
        label.setLayoutY(0);


        RadioButton button1 = new RadioButton("红下");
        button1.setLayoutX(0);
        button1.setLayoutY(length / 3.0);
        button1.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial'; -fx-text-fill: blue;");
        button1.setSelected(true);
        button1.setContentDisplay(ContentDisplay.LEFT);
        button1.setOnAction(e -> {
            if(button1.isSelected())
            {
                priority = 1;
                dataField.setDirection(1);
            }
        });

        RadioButton button2 = new RadioButton("黑下");
        button2.setLayoutX(0);
        button2.setLayoutY(length / 3.0 * 2.0);
        button2.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial'; -fx-text-fill: blue;");
        button2.setSelected(false);
        button2.setContentDisplay(ContentDisplay.LEFT);
        button2.setOnAction(e -> {
            if(button2.isSelected())
            {
                priority = 0;
                dataField.setDirection(0);
            }
        });

        userPriority.setPrefSize(100, length);
        userPriority.getChildren().addAll(label, button1, button2);
        userPriority.setLayoutX(100);
        userPriority.setLayoutY(300);
        group.getToggles().addAll(button1, button2);

        return userPriority;
    }

    public ImageView setBackGround()
    {
        String path = "file:\\" + System.getProperty("user.dir") + "/src/GUI/Image";
        Image image = new Image(path + "//setupBack.jpg");
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    public Button setRuturnButton()
    {
        Button return_Button = new Button("返回");
        return_Button.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial'; -fx-text-fill: grey;");
        return_Button.setPrefSize(70,40);
        return_Button.setLayoutX(1000);
        return_Button.setLayoutY(650);
        return_Button.setOnAction(e -> {
            SetUp_Pane.setVisible(false);
        });
        return return_Button;
    }

    public  Label setlabel()
    {
        Label label = new Label("设置界面");
        label.setStyle("-fx-font-size: 30px; -fx-font-family: 'Arial'; -fx-text-fill: grey;");
        label.setLayoutX(100);
        label.setLayoutY(100);
        String path = "file:\\" + System.getProperty("user.dir") + "/src/GUI/Image";

        ImageView image1 = new ImageView(new Image(path + "\\pawn0.png"));
        image1.setFitHeight(50);
        image1.setFitWidth(50);

        ImageView image2 = new ImageView(new Image(path + "\\pawn7.png"));
        image2.setFitHeight(50);
        image2.setFitWidth(50);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(image1, image2);
        label.setGraphic(hBox);
        label.setContentDisplay(ContentDisplay.BOTTOM);
        return label;
    }
    public Pane getSetUp_Pane() {
        return SetUp_Pane;
    }
}
