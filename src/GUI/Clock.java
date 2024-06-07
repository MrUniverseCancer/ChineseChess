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
    private final int clk_reset_value = 10;
    private int player1CountEnable = 0;
    private int player2CountEnable = 0;
    private int remainingTime1 = clk_reset_value; // 剩余时间初始值为300秒
    private int remainingTime2 = clk_reset_value; // 剩余时间初始值为300秒
    private boolean countEnd;
    private Label timerLabel1;
    private Label timerLabel2;
    private Pane Pane;
    private int direction;
    Head head_inst;

    public void resetTimer(Head head_inst)
    {
        remainingTime1 = clk_reset_value;
        remainingTime2 = clk_reset_value;
        countEnd = false;
        direction = head_inst.getDataField().getDirection();
        this.head_inst = head_inst;
    }

    public boolean getIsEnd()
    {
        return countEnd;
    }
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

    public void stopCount()
    {
        player1CountEnable = 0;
        player2CountEnable = 0;
        remainingTime1 = clk_reset_value;
        remainingTime2 = clk_reset_value;
        countEnd = true;
        timerLabel1.setText(formatTime(remainingTime1));
        timerLabel2.setText(formatTime(remainingTime2));
    }
    public void stopOnly()
    {
        player1CountEnable = 0;
        player2CountEnable = 0;
    }


    public void stopPlayer1()
    {
        player1CountEnable = 0;
    }

    public void stopPlayer2()
    {
        player2CountEnable = 0;
    }

    public void continuePlayer1()
    {
        if(!countEnd)
        {
            player1CountEnable = 1;
        }

    }

    public void continuePlayer2()
    {
        if(!countEnd)
        {
            player2CountEnable = 1;
        }
    }

    // 更新剩余时间
    private void updateTimer() {
        if (player1CountEnable == 1) {
            if(!countEnd)
            {
                remainingTime1--;
            }

            if(remainingTime1 < 0) {
                countEnd = true;
                stopPlayer1();
            }

            if (!countEnd) {
                timerLabel1.setText(formatTime(remainingTime1));
            }
            else {
                timerLabel1.setText("时间到！");
                timeEndHandle(1);
            }
        }
        if (player2CountEnable == 1){
            if(!countEnd)
            {
                remainingTime2--;
            }

            if(remainingTime2 < 0) {
                countEnd = true;
                stopPlayer2();
            }

            if (!countEnd) {
                timerLabel2.setText(formatTime(remainingTime2));
            }
            else {
                timerLabel2.setText("时间到！");
                timeEndHandle(2);
            }
        }
    }

    // 将秒数格式化为"mm:ss"的形式
    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d", minutes, secs);
    }

    public void timeEndHandle(int player)
    {
        System.out.println("Player " + player + " Time End");
        String move = ((player == 1 && direction == 0) || (player == 2 && direction == 1)) ? "Black Win"  : "Red Win";
        head_inst.getBackGround().getGameRecoder().addMove(move);
        head_inst.getPromptImage().setPrompt_label(move);
        head_inst.getContestEnd_handler().SelfEND();
    }

    public javafx.scene.layout.Pane getPane() {
        return Pane;
    }
}