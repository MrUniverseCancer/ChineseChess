package GUI.Handler_Listener;

import GUI.SetUpScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SetUP_Handler implements EventHandler<ActionEvent>
{
    SetUpScreen setUpScreen;

    public SetUP_Handler(SetUpScreen setUpScreen)
    {
        this.setUpScreen = setUpScreen;
    }

    @Override
    public void handle(ActionEvent e)
    {
        //设置对应的逻辑
        System.out.println("SetUP Button Successful");
        setUpScreen.getSetUp_Pane().setVisible(true);
    }
}
