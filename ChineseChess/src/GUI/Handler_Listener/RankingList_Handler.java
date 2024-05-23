package GUI.Handler_Listener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class RankingList_Handler implements EventHandler<ActionEvent>
{
    private int state;
    private Button RankingList_Button;

    public RankingList_Handler(Button rankingList_Button) {
        this.state = 0;
        this.RankingList_Button = rankingList_Button;
    }

    @Override
    public void handle(ActionEvent e)
    {
        System.out.println("Ranking Button Successful");
        if(state == 0)
        {
            //显示“查看排行榜”
            RankingList_Button.setText("回到主页");
            state = 1;
        }
        else
        {
            //显示“回到主页”
            RankingList_Button.setText("查看排行榜");
            state = 0;
        }
    }
}
