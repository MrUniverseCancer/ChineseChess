package GUI;

import GUI.Handler_Listener.Login_Handler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import rules.AccountManager;

public class LoginScreen
{
    private Stage stage;
    private Pane pane;
    private Pane Loginpane;
    private Pane Enrollpane;
    private Button Confirm;
    private Label Reply;

    private TextField acconut1;
    private TextField acconut2;
    private PasswordField pass1;
    private PasswordField pass2_1;
    private PasswordField pass2_2;

    private int state;
    public LoginScreen(Stage stage)
    {
        state = 0;
        this.stage = stage;
        pane = new Pane();

        pane.getChildren().add(getLogin());
        pane.getChildren().add(getEnroll());
        pane.getChildren().add(getConfirm());
        pane.getChildren().add(getOther());
        pane.getChildren().add(getReply());


        pane.setLayoutX(0);
        pane.setLayoutY(0);
        pane.setPrefSize(450, 600);
    }

    public Label getReply()
    {
        Reply = new Label();
        Reply.setStyle("-fx-font-size: 25px; -fx-font-family: 'Arial'; -fx-text-fill: grey;");
        Reply.setPrefSize(400,50);
        Reply.setLayoutX(25);
        Reply.setLayoutY(500);
        Reply.setAlignment(Pos.CENTER);
        Reply.setVisible(false);

        return Reply;
    }

    public Button getConfirm()
    {
        Confirm = new Button("确认");
        Confirm.setStyle("-fx-font-size: 25px; -fx-font-family: 'Arial'; -fx-text-fill: grey;");
        Confirm.setPrefSize(150,50);
        Confirm.setLayoutX(150); // 设置按钮的 x 坐标
        Confirm.setLayoutY(450); // 设置按钮的 y 坐标

        Confirm.setOnAction(new ConfirmHandler());
        return Confirm;
    }
    public Button getOther()
    {
        Confirm = new Button("前往注册");
        Confirm.setStyle("-fx-font-size: 25px; -fx-font-family: 'Arial'; -fx-text-fill: grey;");
        Confirm.setPrefSize(140,50);
        Confirm.setLayoutX(300); // 设置按钮的 x 坐标
        Confirm.setLayoutY(130); // 设置按钮的 y 坐标

        Confirm.setOnAction(new Handler());
        return Confirm;
    }

    public Pane getLogin()
    {
        Loginpane = new Pane();
        Loginpane.setPrefSize(450, 600);

        int x,y;
        x = 450;
        y = 100;
        Label label = new Label("登陆");
        label.setStyle("-fx-font-size: 30px; -fx-font-family: 'Arial'; -fx-text-fill: black;");
        label.setPrefSize(x,y);
        label.setAlignment(Pos.CENTER);
        label.setLayoutX((450-x)/2.0);
        label.setLayoutY(0);
        Loginpane.getChildren().add(label);

        Line line = new Line(0,125,450,125);
        line.setStrokeWidth(10);
        line.setStroke(Color.BLACK);
        Loginpane.getChildren().add(line);

        Label accountLabel = new Label("账号：");
        TextField accountField = new TextField();
        accountLabel.setStyle("-fx-font-size: 25px; -fx-font-family: 'Arial'; -fx-text-fill: black;");
        accountField.setPrefHeight(35); // 加一些额外空间
        accountLabel.setLayoutX(100);
        accountLabel.setLayoutY(200);
        accountField.setLayoutX(220);
        accountField.setLayoutY(200);

        Label passwordLabel = new Label("密码：");
        PasswordField passwordField = new PasswordField();
        passwordLabel.setStyle("-fx-font-size: 25px; -fx-font-family: 'Arial'; -fx-text-fill: black;");
        passwordField.setPrefHeight(35); // 加一些额外空间
        passwordLabel.setLayoutX(100);
        passwordLabel.setLayoutY(300);
        passwordField.setLayoutX(220);
        passwordField.setLayoutY(300);

        acconut1 = accountField;
        pass1    = passwordField;

        Loginpane.getChildren().addAll(accountLabel,accountField,passwordLabel,passwordField);
        return Loginpane;
    }

    public Pane getEnroll()
    {
        Enrollpane = new Pane();
        Enrollpane.setPrefSize(450, 600);

        int x,y;
        x = 450;
        y = 100;
        Label label = new Label("注册");
        label.setStyle("-fx-font-size: 30px; -fx-font-family: 'Arial'; -fx-text-fill: black;");
        label.setPrefSize(x,y);
        label.setAlignment(Pos.CENTER);
        label.setLayoutX((450-x)/2.0);
        label.setLayoutY(0);
        Enrollpane.getChildren().add(label);

        Line line = new Line(0,125,450,125);
        line.setStrokeWidth(10);
        line.setStroke(Color.BLACK);
        Enrollpane.getChildren().add(line);

        Label accountLabel = new Label("账号：");
        TextField accountField = new TextField();
        accountLabel.setStyle("-fx-font-size: 25px; -fx-font-family: 'Arial'; -fx-text-fill: black;");
        accountField.setPrefHeight(35); // 加一些额外空间
        accountLabel.setLayoutX(100);
        accountLabel.setLayoutY(200);
        accountField.setLayoutX(220);
        accountField.setLayoutY(200);

        Label passwordLabel = new Label("密码：");
        PasswordField passwordField = new PasswordField();
        passwordLabel.setStyle("-fx-font-size: 25px; -fx-font-family: 'Arial'; -fx-text-fill: black;");
        passwordField.setPrefHeight(35); // 加一些额外空间
        passwordLabel.setLayoutX(100);
        passwordLabel.setLayoutY(300);
        passwordField.setLayoutX(220);
        passwordField.setLayoutY(300);

        Label passwordLabel_ = new Label("确认密码：");
        PasswordField passwordField_ = new PasswordField();
        passwordLabel_.setStyle("-fx-font-size: 25px; -fx-font-family: 'Arial'; -fx-text-fill: black;");
        passwordField_.setPrefHeight(35); // 加一些额外空间
        passwordLabel_.setLayoutX(100);
        passwordLabel_.setLayoutY(400);
        passwordField_.setLayoutX(220);
        passwordField_.setLayoutY(400);

        acconut2 = accountField;
        pass2_1  = passwordField;
        pass2_2  = passwordField_;

        Enrollpane.getChildren().addAll(accountLabel,accountField,passwordLabel,passwordField,passwordField_,passwordLabel_);
        Enrollpane.setVisible(false);
        return Enrollpane;
    }
    
    public Pane getPane() {
        return pane;
    }

    class Handler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent actionEvent)
        {
            if(state == 0)
            {
                // 登陆页面
                Loginpane.setVisible(false);
                Enrollpane.setVisible(true);
                state = 1;
                Confirm.setText("返回登陆");
            }
            else
            {
                //注册页面
                state = 0;
                Loginpane.setVisible(true);
                Enrollpane.setVisible(false);
                Confirm.setText("注册账号");
            }
        }
    }

    class ConfirmHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent actionEvent)
        {
            if(state == 0)
            {
                // 登陆页面
                int state;
                String account = acconut1.getText();
                String pass    = pass1.getText();
                System.out.println("acconut: " + account + "  pass: " + pass);
                if(account.isEmpty() || pass.isEmpty())
                {
                    Reply.setVisible(true);
                    Reply.setText("禁止为空");
                }
                else if((state = AccountManager.verifyAccount(account, pass)) == 0 )
                {
                    stage.close();
                }
                else
                {
                    Reply.setVisible(true);
                    if(state == -1)
                        Reply.setText("读取错误");
                    else if(state == -2)
                        Reply.setText("密码错误");
                    else if(state == -3)
                        Reply.setText("账号不存在");
                }
            }
            else
            {
                // 注册页面
                Reply.setVisible(true);
                String account = acconut1.getText();
                String pass1   = pass2_1.getText();
                String pass2   = pass2_2.getText();
                int localstate = 0;
                if(account.isEmpty() || pass1.isEmpty() || pass2.isEmpty())
                {
                    Reply.setText("禁止为空");
                }
                else if(pass1.equals(pass2))
                {
                    if(( AccountManager.storeAccount(account, pass1)) != 0)
                    {
                        localstate = 2;
                    }
                }
                else
                {
                    localstate = 1;
                }

                if(localstate == 0)
                {
                    // 注册成功
                    Reply.setText("注册成功");
                }
                else if(localstate == 1)
                {
                    Reply.setText("密码不同");
                }
                else if(localstate == 2)
                {
                    Reply.setText("创建用户失败");
                }
            }
        }
    }
}
