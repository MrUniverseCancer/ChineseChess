package GUI;


import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GameRecoder
{
    private static final int MAX_MOVES = 15;
    private TextArea movesTextArea;
    private ScrollPane scrollPane;
    private VBox vbox;

    public GameRecoder()
    {
        // 创建一个透明的背景
        Background transparentBackground = new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY));


        Pane temp_Pane = new Pane();
        movesTextArea = new TextArea("Hello World!\n");
//        movesTextArea.setEditable(false);
        movesTextArea.setWrapText(true);
        movesTextArea.setPrefRowCount(MAX_MOVES);
//        movesTextArea.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        // 设置了文本区域的属性，使其不可编辑，自动换行，最大行数为10
        // 设置字体和大小
        movesTextArea.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial'; -fx-text-fill: black;");
        movesTextArea.setOpacity(0.5);
        movesTextArea.setBackground(transparentBackground);


        scrollPane = new ScrollPane();
        scrollPane.setLayoutX(0);
        scrollPane.setLayoutY(150);
        scrollPane.setPrefSize(250,400);
        scrollPane.setVisible(false);
        // 增加背景图案
        ImageView temp = new ImageView("file:\\" + System.getProperty("user.dir") + "/src/GUI/Image/recode.jpg");

//        temp.setOpacity(0.1);
        temp_Pane.getChildren().addAll(temp,movesTextArea);
        temp_Pane.setBackground(transparentBackground);
        scrollPane.setContent(temp_Pane);
//        scrollPane.setContent(temp);

        // 设置了滚动面板的属性，使其适应宽度和高度
    }

    public ScrollPane getScrollPane()
    {
        return scrollPane;
    }

    public void addMove(String move)
    {
        String currentText = movesTextArea.getText();
        long moveCount = currentText.lines().count();

        if (moveCount >= MAX_MOVES) {
            // Remove oldest move
            int index = currentText.indexOf('\n');
            currentText = currentText.substring(index + 1);
        }

        movesTextArea.setText(currentText + move + "\n");
        movesTextArea.selectPositionCaret(movesTextArea.getLength());
        movesTextArea.deselect();
        // q: 解释上面三句每句话的意义
        // a: 第一句是获取当前文本区域的内容，第二句是获取当前文本区域的行数，第三句是如果行数超过最大行数，就删除最旧的一行
    }
    public void clearMoves()
    {
        movesTextArea.clear();
    }

}
