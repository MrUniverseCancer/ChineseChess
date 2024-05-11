package GUI;


import GUI.Handler_Listener.PawnMoving_Handler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

//棋子
public class Pawns
{
    ContestScreen contestScreen_inst;



    public Pane Get_Fixed_pawn(int i, int direction)
    {

        Circle  pawn_circle = new Circle(35,35,35);
        pawn_circle.setStroke(null); // 设置圆形的边框为透明
        String path = "file:\\" + System.getProperty("user.dir") + "/src/GUI/Image";
        Image temp_image = new Image(path + "/pawn"+ i +".png");
//        System.out.println(path + "/pawn"+ i +".png");
        ImageView temp_imageView = new ImageView(temp_image);
        temp_imageView.setFitWidth(70);
        temp_imageView.setFitHeight(70);
        temp_imageView.setClip(pawn_circle);
        if(direction == 1)
        {
            temp_imageView.setRotate(180);
        }

        Pane temp_pane = new Pane();
        temp_pane.getChildren().add(temp_imageView);

        return temp_pane;
    }


}
