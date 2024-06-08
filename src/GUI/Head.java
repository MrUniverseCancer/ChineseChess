package GUI;

import GUI.Handler_Listener.BeginningGame_Handler;
import GUI.Handler_Listener.ContestEnd_Handler;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Head extends Application
{
    private BackGround backGround;
    private Scene scene              ;


    private Pane  Contest_Screen_inst;

    private Stage primaryStage;

    private dataField dataField;

    private PromptImage promptImage;
    private Pane prompt_pane;

    private ContestEnd_Handler contestEnd_handler;


    @Override
    public void start(Stage stage)
    {
        //初始化数据域
        dataField = new dataField();

        //确定背景界面
        this.primaryStage = stage;
        Pane Back_inst = new Pane();
        backGround = new BackGround(this);
        Back_inst.getChildren().add(backGround.getBack_pane());
        scene = new Scene(Back_inst,1250, 750);
        Pane scene_add_root = (Pane) scene.getRoot();
        promptImage = new PromptImage();
        prompt_pane = promptImage.getPrompt_pane();
        scene_add_root.getChildren().add(prompt_pane);





        stage.setResizable(false);
        stage.setTitle("Chinese Chess Game");
        stage.setScene(scene);
        stage.show();
    }




    public void contestScreenInput()
    {
        //创建新的比赛界面
        Contest_Screen_inst = new Pane();
        Contest_Screen_inst.setPrefSize(750, 750);
        Contest_Screen_inst.setTranslateX(500);
        Contest_Screen_inst.setTranslateY(0);
        ContestScreen contest_inst = new ContestScreen(this.dataField, backGround.getClock(), this);
        Contest_Screen_inst.getChildren().add(contest_inst.getPane());
        Pane scene_add_root = (Pane) scene.getRoot();
        scene_add_root.getChildren().add(Contest_Screen_inst);
        prompt_pane.toFront();
    }

    public void contestScreenDel()
    {
        //删去比赛界面以及记录
        Pane scene_add_root = (Pane) scene.getRoot();
        scene_add_root.getChildren().remove(Contest_Screen_inst);
    }

    public void EndHandlerInitial(Button button, Clock clock)
    {
        contestEnd_handler = new ContestEnd_Handler(this, button, clock);
        return ;
    }

    public ContestEnd_Handler getContestEnd_handler() {
        return contestEnd_handler;
    }

    public Pane getContest_Screen_inst() {
        return Contest_Screen_inst;
    }
    public BackGround getBackGround() {
        return backGround;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public GUI.dataField getDataField() {
        return dataField;
    }

    public PromptImage getPromptImage() {
        return promptImage;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
