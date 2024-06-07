package GUI.Handler_Listener;

import GUI.Clock;
import GUI.Head;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


//依赖注入实现
public class BeginningGame_Handler implements EventHandler<ActionEvent>
{
    private Head head_inst;
    private Button button;
    private Clock clock;
    public BeginningGame_Handler (Head fact)
    {
        head_inst = fact;
    }

    public BeginningGame_Handler(Head fact, Button button, Clock clock) {
        this(fact);
        this.button = button;
        this.clock = clock;
    }

    @Override
    public void handle(ActionEvent e)
    {
        //开始比赛对应的逻辑
        System.out.println("Game Begin");
        clock.resetTimer(head_inst);
        head_inst.contestScreenInput();
        button.setText("中止比赛");
        button.setOnAction(new ContestEnd_Handler(head_inst,button,clock));

        //将排行榜选项，设置选项设为不可见
        head_inst.getBackGround().getRankingList_Button().setVisible(false);
        head_inst.getBackGround().getSetUp_Button().setVisible(false);
        head_inst.getBackGround().getLogin_Button().setVisible(false);
        //将时钟和排行榜设为可见
        head_inst.getBackGround().getClock().getPane().setVisible(true);
        head_inst.getBackGround().getRecode_pane().setVisible(true);
    }
}
