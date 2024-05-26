package GUI.Handler_Listener;

import GUI.Head;
import GUI.LoginScreen;
import GUI.RankingScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RankingList_Handler implements EventHandler<ActionEvent>
{
    private int state;
    private Button RankingList_Button;
    private Stage primaryStage;
    private Head head_inst;

    public RankingList_Handler(Button rankingList_Button, Stage primaryStage, Head head_inst) {
        this.state = 0;
        this.RankingList_Button = rankingList_Button;
        this.primaryStage = primaryStage;
        this.head_inst = head_inst;
    }

    @Override
    public void handle(ActionEvent e)
    {
        System.out.println("Ranking Button Successful");
        if(state == 0)
        {
            Stage stage = new Stage();

            //设置优先级关系
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);

            RankingScreen rankingScreen = new RankingScreen(stage, head_inst, this.RankingList_Button);
            Scene scene = new Scene(rankingScreen.getPane(), 450, 600);



            stage.setResizable(false);
            stage.setTitle("Ranking List");
            stage.setScene(scene);
            stage.show();
            //显示“查看排行榜”
            state = 1;
        }
    }
}
