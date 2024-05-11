package GUI.Handler_Listener;

import GUI.Head;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


//依赖注入实现
public class BeginningGame_Handler implements EventHandler<ActionEvent>
{
    private Head head_inst;
    private Button button;

    public BeginningGame_Handler (Head fact)
    {
        head_inst = fact;
    }

    public BeginningGame_Handler(Head fact, Button button) {
        this(fact);
        this.button = button;
    }

    @Override
    public void handle(ActionEvent e)
    {
        //开始比赛对应的逻辑
        System.out.println("Game Begin");
        head_inst.contestScreenInput();
        button.setText("中止比赛");
        button.setOnAction(new ContestEnd_Handler(head_inst,button));

        //将排行榜选项，设置选项设为不可见
        head_inst.getBackGround().getRankingList_Button().setVisible(false);
        head_inst.getBackGround().getSetUp_Button().setVisible(false);
        //将时钟设为可见
        head_inst.getBackGround().getClock().getPane().setVisible(true);
    }
}
