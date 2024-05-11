package GUI;


import GUI.Handler_Listener.RankingList_Handler;
import GUI.Handler_Listener.SetUP_Handler;
import javafx.scene.control.Button;

public class OtherButton
{
    private Button RankingList_Button;
    private Button SetUp_Button;
    private SetUpScreen setUpScreen;

    public OtherButton() {
    }
    public OtherButton(SetUpScreen setUpScreen)
    {
        this.setUpScreen = setUpScreen;
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
