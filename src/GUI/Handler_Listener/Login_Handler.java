package GUI.Handler_Listener;

import GUI.LoginScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Login_Handler implements EventHandler<ActionEvent>
{
    private Stage primaryStage;

    public Login_Handler(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void handle(ActionEvent actionEvent)
    {
        System.out.println("Login");
        Stage stage = new Stage();

        //设置优先级关系
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);

        LoginScreen loginScreen = new LoginScreen(stage);
        Scene scene = new Scene(loginScreen.getPane(), 450, 600);
        Pane scene_add_root = (Pane) scene.getRoot();


        stage.setResizable(false);
        stage.setTitle("Login and Enroll");
        stage.setScene(scene);
        stage.show();
    }
}
