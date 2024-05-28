package GUI;

import GUI.Handler_Listener.BeginningGame_Handler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;


//用于描述永远存在在左侧下面的按钮，未开始游戏为“开始游戏”，开始后为“游戏终止”
public class ForeverButton
{
    private Pane pane;
    private Button button;
    private Clock clock;

    public ForeverButton(Head fact, Clock clock)
    {
        this.clock = clock;
        // 创建 Pane
        pane = new Pane();
        pane.setPrefSize(500, 750);

        // 创建按钮
        int x,y;
        int length = 100;
        int width  = 50 ;
        int area = 50;
        x = (500 - length) / 2;
        y = (750 - width) - area;
        button = new Button("开始游戏");
        button.setStyle("-fx-font-size: 18px; -fx-font-family: 'Arial'; -fx-text-fill: grey;");
        button.setPrefSize(length,width);
        button.setLayoutX(x); // 设置按钮的 x 坐标
        button.setLayoutY(y); // 设置按钮的 y 坐标

        // 将按钮添加到 Pane 中
        pane.getChildren().add(button);
        button.setOnAction(new BeginningGame_Handler(fact, button, clock));
    }

    public Pane getPane() {
        return pane;
    }

    public Button getButton() {
        return button;
    }

}
