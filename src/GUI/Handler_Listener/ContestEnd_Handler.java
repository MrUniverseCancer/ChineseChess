package GUI.Handler_Listener;

import GUI.Clock;
import GUI.Head;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ContestEnd_Handler implements EventHandler<ActionEvent>
{
    private Head head_inst;
    private Button button;
    private Clock clock;

    public ContestEnd_Handler(Head head_inst, Button button, Clock clock)
    {
        this.button = button;
        this.head_inst = head_inst;
        this.clock = clock;
    }

    public void handle(ActionEvent e)
    {
        //中止比赛的部分逻辑
        System.out.println("Game Finish");
        System.out.println("Game End 6666");
        clock.stopCount();
        head_inst.contestScreenDel();
        button.setText("开始比赛");
        button.setOnAction(new BeginningGame_Handler(head_inst, button, clock));
        head_inst.getBackGround().getRankingList_Button().setVisible(true);
        head_inst.getBackGround().getSetUp_Button().setVisible(true);
        head_inst.getBackGround().getLogin_Button().setVisible(true);
        head_inst.getBackGround().getClock().getPane().setVisible(false);
        head_inst.getBackGround().getRecode_pane().setVisible(false);
        head_inst.getBackGround().getGameRecoder().clearMoves();
    }

    public void SelfEND()
    {
        // 暂停时钟
        clock.stopOnly();

        // 随意点击鼠标后，退出比赛页面
        // 先覆盖一层，使所有选项失效，点击后消除
        Pane tempPane = new Pane();
        tempPane.setPrefSize(1250, 750);
        Background transparentBackground = new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY));

        // 设置Pane和其所有子节点的背景为透明
        tempPane.setBackground(transparentBackground);

        // 将tempPane添加到根结点上
        Pane scene_root = (Pane)head_inst.getPrimaryStage().getScene().getRoot();
        scene_root.getChildren().add(tempPane);
        tempPane.setOnMouseClicked(e -> {
            //删除刚刚新加入的结点tempPane
            System.out.println(1);

            scene_root.getChildren().remove(tempPane);

            //中止比赛的部分逻辑
            handle(null);

        });
    }
}
