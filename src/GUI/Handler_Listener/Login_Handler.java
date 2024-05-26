package GUI.Handler_Listener;

import GUI.Head;
import GUI.LoginScreen;
import GUI.dataField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Login_Handler implements EventHandler<ActionEvent>
{
    private Stage primaryStage;
    private Head head_inst;
    private Button fact;
    private int status;

    public Login_Handler(Stage primaryStage, Head head_inst, Button fact) {
        this.primaryStage = primaryStage;
        this.head_inst = head_inst;
        this.fact = fact;
        this.status = 0;
    }

    @Override
    public void handle(ActionEvent actionEvent)
    {
        System.out.println("Login");
        if(status == 0)
        {
            Stage stage = new Stage();

            //设置优先级关系
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);

            LoginScreen loginScreen = new LoginScreen(stage, this.head_inst);
            Scene scene = new Scene(loginScreen.getPane(), 450, 600);
//            Pane scene_add_root = (Pane) scene.getRoot();


            stage.setResizable(false);
            stage.setTitle("Login and Enroll");
            stage.setScene(scene);
            stage.show();
            status = 1;
            this.fact.setText("退出登陆");
        }
        else
        {
            //退出登陆
            status = 0;
            this.fact.setText("登陆");
            head_inst.getBackGround().getLabel().setText("当前用户： " + "游客" );
            dataField temp = head_inst.getDataField();
            temp.setAccount("游客");
        }
    }
}
