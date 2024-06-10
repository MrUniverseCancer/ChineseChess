package GUI.Handler_Listener;

import GUI.Clock;
import GUI.Head;
import GUI.dataField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import rules.AccountManager;
import java.util.Random;    

public class ContestEnd_Handler implements EventHandler<ActionEvent>
{
    private Head head_inst;
    private Button button;
    private Clock clock;
    private dataField DataField;

    public ContestEnd_Handler(Head head_inst, Button button, Clock clock, dataField DataField)
    {
        this.button = button;
        this.head_inst = head_inst;
        this.clock = clock;
        this.DataField = DataField;
    }

    public void handle(ActionEvent e)
    {
        //中止比赛的部分逻辑
        System.out.println("Game Finish");
        System.out.println("Game End 6666");
        clock.stopCount();
        head_inst.contestScreenDel();
        button.setText("开始比赛");
        button.setOnAction(new BeginningGame_Handler(head_inst, button, clock, DataField));
        head_inst.getBackGround().getRankingList_Button().setVisible(true);
        head_inst.getBackGround().getSetUp_Button().setVisible(true);
        head_inst.getBackGround().getLogin_Button().setVisible(true);
        head_inst.getBackGround().getClock().getPane().setVisible(false);
        head_inst.getBackGround().getRecode_pane().setVisible(false);
        head_inst.getBackGround().getGameRecoder().clearMoves();
    }

    public void SelfEND(boolean red_lose, boolean black_lose)
    {
        // 暂停时钟
        clock.stopOnly();
        Random random = new Random();
        int min = 15;
        int max = 30;

        // 生成 15 到 30 之间的随机数
        int randomNumber = random.nextInt((max - min) + 1) + min;
        
        if(DataField.getAccountManager() == 1)  //红色是当前账户
        {
            if(red_lose)  //红方时间没了，账户减分
            {
                AccountManager.changeAccountScore(DataField.getAccount(), -randomNumber);
            }
            else    //黑方时间没了，账户加分
            {
                AccountManager.changeAccountScore(DataField.getAccount(), randomNumber);
            }
        }
        else    //黑色是当前账户
        {
            if(black_lose)  //黑方时间没了，账户减分
            {
                AccountManager.changeAccountScore(DataField.getAccount(), -randomNumber);
            }
            else    //红方时间没了，账户加分
            {
                AccountManager.changeAccountScore(DataField.getAccount(), randomNumber);
            }
        }
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
