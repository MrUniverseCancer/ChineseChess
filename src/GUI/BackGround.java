package GUI;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class BackGround
{
    private Pane back_pane;
    private Image image1, image2;

    private ForeverButton button1;
    private Button RankingList_Button;
    private Button SetUp_Button;
    private SetUpScreen setUpScreen;
    private Clock clock;



    public BackGround(Head fact)
    {
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

        //时间界面
        clock = new Clock();
        Pane clock_pane = clock.getClock();
        back_pane.getChildren().add(clock_pane);
        //设置界面
        setUpScreen = new SetUpScreen();
        Pane setUp_pane = setUpScreen.getSetUpScreen();



        //开始按键相关
        button1 = new ForeverButton(fact);
        Pane button1_pane = button1.getPane();
        button1_pane.setPrefSize(500,750);
        button1_pane.setLayoutX(0);
        button1_pane.setLayoutY(0);
        back_pane.getChildren().add(button1_pane);

        OtherButton otherButton = new OtherButton(this.setUpScreen);
        //排行榜按钮
        RankingList_Button = otherButton.getRankingList();
        back_pane.getChildren().add(RankingList_Button);
        //设置按钮
        SetUp_Button = otherButton.getSetUp_Button();
        back_pane.getChildren().add(SetUp_Button);




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
