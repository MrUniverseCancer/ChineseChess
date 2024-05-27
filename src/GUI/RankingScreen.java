package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import rules.RankingListGet;

import java.util.ArrayList;

public class RankingScreen
{
    private Pane Ranking_Pane;
    private Button RankingList_Button;
    private Stage primaryStage;
    private Head head_inst;

    public RankingScreen(Stage primaryStage, Head head_inst, Button rankingList_Button)
    {
        this.RankingList_Button = rankingList_Button;
        this.primaryStage = primaryStage;
        this.head_inst = head_inst;
        this.Ranking_Pane = new Pane();

        Ranking_Pane.getChildren().add(RankingListPane());
        Ranking_Pane.getChildren().add(ReturnButton());
    }

    public Button ReturnButton()
    {
        //在底部创建返回按钮
        int x,y;
        int length = 150;
        int width  = 50 ;
        int area = 50;
        x = (450 - length) / 2;
        y = (600 - width) - area;
        Button Return_Button = new Button("回到主页");
        Return_Button.setStyle("-fx-font-size: 17px; -fx-font-family: 'Arial'; -fx-text-fill: grey;");
        Return_Button.setPrefSize(length,width);
        Return_Button.setLayoutX(x); // 设置按钮的 x 坐标
        Return_Button.setLayoutY(y); // 设置按钮的 y 坐标
        Return_Button.setOnAction(e ->{
            primaryStage.close();
        });
        return Return_Button;
    }

    public Pane RankingListPane()
    {
        //创建排行榜
        Pane RankingListPane = new Pane();
        RankingListPane.setStyle("-fx-background-color: #f0f8ff;");
        RankingListPane.setPrefSize(450, 600);
        RankingListPane.setLayoutX(0);
        RankingListPane.setLayoutY(0);

        Label label_head = new Label("排行榜");
        label_head.setStyle("-fx-font-size: 40px; -fx-font-family: 'Arial'; -fx-text-fill: grey;");
        label_head.setPrefSize(450,50);
        label_head.setLayoutX(20);
        label_head.setLayoutY(30);
        RankingListPane.getChildren().add(label_head);


        // 从getRankingList()方法中获得排行榜,返回值为一个ArrayList<ArrayList<Object>>的二维数组
        // 每一个数据，第一个参数为一个String类型的用户名，第二个参数为一个Integer类型的分数
        // 例如：[["张三", 100], ["李四", 90], ["王五", 80], ["赵六", 70], ["钱七", 60]]
        // 将用户名和数据分别展示，希望可以通过设计滑轮，上下拖动，查看更多的排行榜数据
        ArrayList<ArrayList<Object>> data = RankingListGet.getRankingList();
        int length = 60;
        int width = 50;
        int x = 150;
        int y = 100;

        // 设计多层文字域，用于展示排行榜数据，最多展示十个，设计滚轮，可以查看更多的排行榜数据
        // 创建一个VBox，用于垂直排列Label
        VBox vbox = new VBox();
        Label name = new Label("用户名");
        name.setLayoutX(100);
        name.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial'; -fx-text-fill: grey;");

        Label scoreLabel1 = new Label("分数");
        scoreLabel1.setLayoutX(300);
        scoreLabel1.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial'; -fx-text-fill: grey;");
        Pane head_pane = new Pane();
        head_pane.getChildren().add(name);
        head_pane.getChildren().add(scoreLabel1);
        head_pane.setPrefSize(450, 50);
        head_pane.setLayoutX(0);
        vbox.getChildren().add(head_pane);

        // 遍历排行榜数据，每个数据创建一个Label，并添加到VBox中
        for (ArrayList<Object> entry : data) {
            String username = (String) entry.get(0);
            Integer score = (Integer) entry.get(1);

            Label nameLabel = new Label(username);
            nameLabel.setLayoutX(100);
            nameLabel.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial'; -fx-text-fill: grey;");

            Label scoreLabel = new Label(score.toString());
            scoreLabel.setLayoutX(300);
            scoreLabel.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial'; -fx-text-fill: grey;");

            Pane temp_pane = new Pane();
            temp_pane.getChildren().add(nameLabel);
            temp_pane.getChildren().add(scoreLabel);
            temp_pane.setPrefSize(450, 50);
            temp_pane.setLayoutX(0);

            vbox.getChildren().add(temp_pane);
        }

        // 创建一个ScrollPane，将VBox放入ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vbox);
        scrollPane.setLayoutX(0);
        scrollPane.setLayoutY(100);
        scrollPane.setPrefSize(450, 400);

        // 将ScrollPane添加到RankingListPane
        RankingListPane.getChildren().add(scrollPane);

        return RankingListPane;
    }

    public Pane getPane()
    {
        return Ranking_Pane;
    }



}
