package GUI;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class PromptImage
{
    private StackPane prompt_pane;
    private Label prompt_label;

    public PromptImage()
    {
        prompt_label = new Label();


        ImageView tempImage = new ImageView("file:\\" + System.getProperty("user.dir") + "/src/GUI/Image/prompt.png");
        tempImage.setLayoutX(0);
        tempImage.setLayoutY(0);
        tempImage.setOpacity(0.9);


        prompt_pane = new StackPane();
        prompt_pane.getChildren().addAll(tempImage, prompt_label);
        prompt_pane.setVisible(false);
        prompt_pane.setPrefSize(250, 150);
        prompt_pane.setLayoutX(500+254);
        prompt_pane.setLayoutY(300);
    }

    public void setPrompt_label(String prompt)
    {
        prompt_label.setText(prompt);
        prompt_label.setStyle("-fx-font-size: 40px; -fx-font-family: 'Arial'; -fx-text-fill: grey;");

        prompt_pane.setVisible(true);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(5), e -> prompt_pane.setVisible(false)) // 5秒后将图层隐藏
        );
        timeline.play();
    }

    public Pane getPrompt_pane()
    {
        return (Pane) prompt_pane;
    }

}
