package GUI.Handler_Listener;

import GUI.Clock;
import GUI.Head;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

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
        clock.stopCount();
        head_inst.contestScreenDel();
        button.setText("开始比赛");
        button.setOnAction(new BeginningGame_Handler(head_inst, button, clock));
        head_inst.getBackGround().getRankingList_Button().setVisible(true);
        head_inst.getBackGround().getSetUp_Button().setVisible(true);
        head_inst.getBackGround().getLogin_Button().setVisible(true);
        head_inst.getBackGround().getClock().getPane().setVisible(false);
    }
}
