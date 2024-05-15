package GUI;


import GUI.Handler_Listener.Login_Handler;
import GUI.Handler_Listener.RankingList_Handler;
import GUI.Handler_Listener.SetUP_Handler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OtherButton
{
    private Button RankingList_Button;
    private Button SetUp_Button;
    private Button Login_Button;
    private SetUpScreen setUpScreen;

    private Stage primaryStage;
    private Head head_inst;

    public OtherButton() {
    }
    public OtherButton(SetUpScreen setUpScreen, Stage primaryStage,Head head_inst)
    {
        this.setUpScreen = setUpScreen;
        this.primaryStage = primaryStage;
        this.head_inst = head_inst;
    }


    public Button getLoginButton()
    {
        // 创建登陆按钮
        int x,y;
        int length = 100;
        int width  = 50 ;
        int area = 300;
        x = (500 - length) / 2;
        y = (750 - width) - area;
        Login_Button = new Button("登录");
        Login_Button.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial'; -fx-text-fill: grey;");
        Login_Button.setPrefSize(length,width);
        Login_Button.setLayoutX(x); // 设置按钮的 x 坐标
        Login_Button.setLayoutY(y); // 设置按钮的 y 坐标

        Login_Button.setOnAction(new Login_Handler(this.primaryStage, this.head_inst));
        return Login_Button;
    }

    public Button getRankingList()
    {
        //获得排行榜的按钮
        int x,y;
        int length = 150;
        int width  = 50 ;
        int area = 150;
        x = (500 - length) / 2;
        y = (750 - width) - area;
        RankingList_Button = new Button("查看排行榜");
        RankingList_Button.setStyle("-fx-font-size: 17px; -fx-font-family: 'Arial'; -fx-text-fill: grey;");
        RankingList_Button.setPrefSize(length,width);
        RankingList_Button.setLayoutX(x); // 设置按钮的 x 坐标
        RankingList_Button.setLayoutY(y); // 设置按钮的 y 坐标

        RankingList_Button.setOnAction(new RankingList_Handler(this.RankingList_Button));
        return RankingList_Button;
    }

    public Button getSetUp_Button()
    {
        //获得设置的按钮
        int x,y;
        int length = 70;
        int width  = 40 ;
        x = 400;
        y = 700;
        SetUp_Button = new Button("设置");
        SetUp_Button.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial'; -fx-text-fill: grey;");
        SetUp_Button.setPrefSize(length,width);
        SetUp_Button.setLayoutX(x); // 设置按钮的 x 坐标
        SetUp_Button.setLayoutY(y); // 设置按钮的 y 坐标

//        SetUP_Handler temp = new SetUP_Handler(setUpScreen);
        SetUp_Button.setOnAction(new SetUP_Handler(setUpScreen));
        return SetUp_Button;
    }
}
