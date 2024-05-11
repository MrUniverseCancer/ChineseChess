package GUI.Handler_Listener;

import GUI.ContestScreen;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import rules.temp_Rule;

public class PawnMoving_Handler implements EventHandler<MouseEvent>
{
    private ContestScreen contestScreen_inst;
    private int [][] Pawnplace;
    private int [] last_fact;
    private int state;

    public PawnMoving_Handler(ContestScreen fact)
    {
        this.contestScreen_inst = fact;
        this.state = 0;
        this.last_fact = new int[2];
    }

    @Override
    public void handle(MouseEvent e)
    {
        this.Pawnplace = contestScreen_inst.getPawnplace();
        int [] fact= new int[2];
        //移动棋子对应的逻辑
        System.out.println("Pawns get moved");
        double x = e.getSceneX();
        double y = e.getSceneY();
        System.out.println("x: " + x + " y: " + y);
        fact = coordinates_translate(x, y);
        System.out.println("x: " + fact[0] + " y: " + fact[1]);
        System.out.println("state: " + state);
        if(state == 0)
        {
            //第一次点击
            if(Pawnplace[fact[0]][fact[1]] != -1)
            {
                //点中棋子
                last_fact[0] = fact[0];
                last_fact[1] = fact[1];
                state = 1;

//                Circle temp = new Circle(20);
//                temp.setLayoutX(20);
//                temp.setLayoutY(20);
                double[] place = reverse_translate(fact[0], fact[1]);
                Pane decorate = decorate_get();
                decorate.setLayoutX(place[0]);
                decorate.setLayoutY(place[1]);
                contestScreen_inst.setPawn_pane(decorate);
            }
        }
        else
        {
            //第二次点击
            Pawnplace = temp_Rule.Check(Pawnplace, last_fact[0], last_fact[1], fact[0], fact[1]);
            contestScreen_inst.setPawnplace(Pawnplace);
            state = 0;
        }
        System.out.println("state: " + state);
    }


    public int [] coordinates_translate(double x, double y)
    {
        int[] result = new int[2];
        //将鼠标点击的坐标转换为棋盘上的坐标
        double x1 = (x-500-90+35.9375) / 71.875;
        double y1 = (y + (((y-48-285- (double) 85 /2) > 0) ? -13.75 : 0) - 48 + 35.625) / 71.25;

        result[0] = (int)x1;
        result[1] = 9 - (int)y1;
        return result;
    }
    public double [] reverse_translate(int x, int y1)
    {
        int y = 9 - y1;
        double[] result = new double[2];
        result[0] = (71.875 * x) + 90 - 35.9375;
        result[1] =  (71.25 * y) + 48 - 30.625 + ((y > 4) ? 13.75 : 0);
        return result;
    }
    public Pane decorate_get()
    {
        Pane result = new Pane();
        result.setPrefSize(70,70);
        Path path1 = new Path();
        MoveTo moveto1 = new MoveTo(0,20);
        LineTo line1_1  = new LineTo(0,0);
        LineTo line2_1  = new LineTo(20,0);
        path1.getElements().add(moveto1);
        path1.getElements().addAll(line1_1, line2_1);
        path1.setStrokeWidth(5);

        Path path2 = new Path();
        MoveTo moveto2 = new MoveTo(50,70);
        LineTo line1_2  = new LineTo(70,70);
        LineTo line2_2  = new LineTo(70,50);
        path2.getElements().add(moveto2);
        path2.getElements().addAll(line1_2, line2_2);
        path2.setStrokeWidth(5);

        Path path3 = new Path();
        MoveTo moveto3 = new MoveTo(50,0);
        LineTo line1_3  = new LineTo(70,0);
        LineTo line2_3  = new LineTo(70,20);
        path2.getElements().add(moveto3);
        path2.getElements().addAll(line1_3, line2_3);
        path2.setStrokeWidth(5);

        Path path4 = new Path();
        MoveTo moveto4 = new MoveTo(0,50);
        LineTo line1_4  = new LineTo(0,70);
        LineTo line2_4  = new LineTo(20,70);
        path2.getElements().add(moveto4);
        path2.getElements().addAll(line1_4, line2_4);
        path2.setStrokeWidth(5);

//        result.getChildren().add(path1);
        result.getChildren().addAll(path1,path2, path3, path4);

        return result;

    }
}