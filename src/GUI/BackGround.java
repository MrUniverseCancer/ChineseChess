package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class BackGround
{
    private Pane back_pane;
    private Image image1, image2;

    private ForeverButton button1;
    private Button RankingList_Button;
    private Button SetUp_Button;
    private Button Login_Button;
    private SetUpScreen setUpScreen;
    private Clock clock;

    private Stage primaryStage;
    private dataField dataField;
    private Head head_inst;
    private Label label;

    private GameRecoder gameRecoder;
    private ScrollPane recode_pane;



    public BackGround(Head fact, Clock clock)
    {
        this.primaryStage = fact.getPrimaryStage();
        this.dataField = fact.getDataField();
        this.head_inst = fact;

        back_pane = new Pane();
        back_pane.setPadding(new Insets(0,0,0,0));

        String path = "file:\\" + System.getProperty("user.dir") + "/src/GUI/Image";
        image1 = new Image(path + "/left.jpg");
        image2 = new Image(path + "/right.jpg");
        System.out.println(path + "/left.jpg");

        ImageView imageView1 = new ImageView(image1);
        ImageView imageView2 = new ImageView(image2);
        imageView1.setLayoutX(0);
        imageView1.setLayoutY(0);
        imageView2.setLayoutX(500);
        imageView2.setLayoutY(0);

        back_pane.getChildren().addAll(imageView1, imageView2);


        label = new Label("当前用户： " + dataField.getAccount());
        label.setStyle("-fx-font-size: 40px; -fx-font-family: 'Arial'; -fx-text-fill: grey;");
        label.setPrefSize(450,50);
        label.setLayoutX(0);
        label.setLayoutY(50);
        label.setAlignment(Pos.CENTER);
        back_pane.getChildren().add(label);

        //时间界面
        this.clock = clock;
        Pane clock_pane = clock.getClock();
        back_pane.getChildren().add(clock_pane);
        //设置界面
        setUpScreen = new SetUpScreen();
        Pane setUp_pane = setUpScreen.getSetUpScreen(head_inst.getDataField());

        // 记录界面
        gameRecoder = new GameRecoder();
        recode_pane = gameRecoder.getScrollPane();
        back_pane.getChildren().add(recode_pane);



        //开始按键相关
        button1 = new ForeverButton(fact, clock, dataField);
        Pane button1_pane = button1.getPane();
        button1_pane.setPrefSize(500,750);
        button1_pane.setLayoutX(0);
        button1_pane.setLayoutY(0);
        back_pane.getChildren().add(button1_pane);

        OtherButton otherButton = new OtherButton(this.setUpScreen, this.primaryStage, this.head_inst);
        //排行榜按钮
        RankingList_Button = otherButton.getRankingList();
        back_pane.getChildren().add(RankingList_Button);
        //设置按钮
        SetUp_Button = otherButton.getSetUp_Button();
        back_pane.getChildren().add(SetUp_Button);
        //登陆按钮
        Login_Button = otherButton.getLoginButton();
        back_pane.getChildren().add(Login_Button);




        back_pane.getChildren().add(setUp_pane);
    }

    public Pane getBack_pane()
    {
        return back_pane;
    }

    public Button getRankingList_Button() {
        return RankingList_Button;
    }
    public Button getSetUp_Button() {
        return SetUp_Button;
    }
    public Button getLogin_Button() {
        return Login_Button;
    }
    public Label getLabel() {
        return label;
    }

    public ScrollPane getRecode_pane() {
        return recode_pane;
    }

    public GameRecoder getGameRecoder() {
        return gameRecoder;
    }

    public Clock getClock() {
        return clock;
    }

    public void setBack_pane(Pane back_pane)
    {
        this.back_pane = back_pane;
    }

    public Button getButton1() {
        return button1.getButton();
    }

}
