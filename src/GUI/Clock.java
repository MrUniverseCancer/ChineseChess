package GUI;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;


public class Clock   {

    private int remainingTime1 = 300; // 剩余时间初始值为300秒
    private int remainingTime2 = 300; // 剩余时间初始值为300秒
    private Label timerLabel1;
    private Label timerLabel2;
    private Pane Pane;


    public Pane getClock()
    {
        int length = 600;
        // 创建一个Label用于显示剩余时间
        timerLabel1 = new Label(formatTime(remainingTime1));
        timerLabel1.setStyle("-fx-font-size: 50px; -fx-font-family: 'Arial'; -fx-text-fill: black;");
        timerLabel1.setLayoutX(250);
        timerLabel1.setLayoutY(250);
        timerLabel2 = new Label(formatTime(remainingTime2));
        timerLabel2.setStyle("-fx-font-size: 50px; -fx-font-family: 'Arial'; -fx-text-fill: black;");
        timerLabel2.setLayoutX(250);
        timerLabel2.setLayoutY(350);

        // 创建一个Pane来放置Label
        Pane = new Pane();
        Pane.setPrefSize(500,750);
        ImageView temp = new ImageView(new Image("file:\\" + System.getProperty("user.dir") + "/src/GUI/Image/clock.jpg"));
        temp.setLayoutX(240);
        temp.setLayoutY(250);
        Pane.setLayoutX(0);
        Pane.setLayoutY(0);
        Pane.getChildren().add(temp);
        Pane.getChildren().add(timerLabel1);
        Pane.getChildren().add(timerLabel2);

        //中间间隔修饰
        Line seporator = new Line(245,325,385,325);
        seporator.setStrokeWidth(10); // 设置直线的宽度
        seporator.setStyle("-fx-stroke: green;"); // 设置直线的颜色
        Pane.getChildren().add(seporator);

        // 创建时间轴（Timeline），每秒更新一次剩余时间
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> updateTimer())
        );
        timeline.setCycleCount(Animation.INDEFINITE); // 设置为无限循环
        timeline.play(); // 启动时间轴

        Pane.setVisible(false);
        return Pane;
    }

    // 更新剩余时间
    private void updateTimer() {
        remainingTime1--;
        if (remainingTime1 >= 0) {
            timerLabel1.setText(formatTime(remainingTime1));
        } else {
            timerLabel1.setText("时间到!");
        }

        remainingTime2--;
        if (remainingTime2 >= 0) {
            timerLabel2.setText(formatTime(remainingTime2));
        } else {
            timerLabel2.setText("时间到!");
        }
    }

    // 将秒数格式化为"mm:ss"的形式
    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d", minutes, secs);
    }

    public javafx.scene.layout.Pane getPane() {
        return Pane;
    }
}