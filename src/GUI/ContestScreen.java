package GUI;

import GUI.Handler_Listener.PawnMoving_Handler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ContestScreen
{
    private Head head_inst;
    private Pane pane;
    private Image board1;

    private int direction;
    private Pane pawn_pane;
    private Pawns pawn_inst;
    private int [][] Pawnplace;
    private int color;
    //9*10的棋盘，以左下为原点计算
    //0-6 为红方王士象马车炮兵
    //7-13为黑方王士象马车炮兵

    private Clock clock;



    public ContestScreen(dataField dataField, Clock clock, Head head_inst)
    {
        this.clock = clock;
        this.head_inst = head_inst;

        pane = new Pane();
        pane.setPrefSize(750, 750);
        pawn_inst = new Pawns();
        color = 0;


        //装载棋盘
        direction = dataField.getDirection();
        String path = "file:\\" + System.getProperty("user.dir") + "/src/GUI/Image";
        board1 = new Image(path + "\\board.jpg");

        ImageView imageView1 = new ImageView(board1);
        imageView1.setLayoutX(0);
        imageView1.setLayoutY(0);
        pane.getChildren().add(imageView1);


        //装载棋子.位置
        Pawnplace = new int[9][10];
        initial_PawnPlace();
        initial_clock();
        //装载棋盘.棋子.通过位置
        Load_Pawn_with_place(direction);
    }


    public void initial_clock()
    {
        clock.stopPlayer1();
        clock.continuePlayer2();
    }

    public void initial_PawnPlace()
    {
        for(int i = 0 ; i < 9 ; i++)
        {
            for(int j = 0 ; j < 10 ; j++)
            {
                Pawnplace[i][j] = -1;
            }
        }
        //车
        Pawnplace[0][0] = 4 + ((direction == 0) ? 7 : 0);
        Pawnplace[8][0] = 4 + ((direction == 0) ? 7 : 0);
        //马
        Pawnplace[1][0] = 3 + ((direction == 0) ? 7 : 0);
        Pawnplace[7][0] = 3 + ((direction == 0) ? 7 : 0);
        //象
        Pawnplace[2][0] = 2 + ((direction == 0) ? 7 : 0);
        Pawnplace[6][0] = 2 + ((direction == 0) ? 7 : 0);
        //士
        Pawnplace[3][0] = 1 + ((direction == 0) ? 7 : 0);
        Pawnplace[5][0] = 1 + ((direction == 0) ? 7 : 0);
        //将
        Pawnplace[4][0] = 0 + ((direction == 0) ? 7 : 0);
        //炮
        Pawnplace[1][2] = 5 + ((direction == 0) ? 7 : 0);
        Pawnplace[7][2] = 5 + ((direction == 0) ? 7 : 0);
        //兵
        Pawnplace[0][3] = 6 + ((direction == 0) ? 7 : 0);
        Pawnplace[2][3] = 6 + ((direction == 0) ? 7 : 0);
        Pawnplace[4][3] = 6 + ((direction == 0) ? 7 : 0);
        Pawnplace[6][3] = 6 + ((direction == 0) ? 7 : 0);
        Pawnplace[8][3] = 6 + ((direction == 0) ? 7 : 0);

        //车
        Pawnplace[0][9] = 4 + ((direction == 1) ? 7 : 0);
        Pawnplace[8][9] = 4 + ((direction == 1) ? 7 : 0);
        //马
        Pawnplace[1][9] = 3 + ((direction == 1) ? 7 : 0);
        Pawnplace[7][9] = 3 + ((direction == 1) ? 7 : 0);
        //象
        Pawnplace[2][9] = 2 + ((direction == 1) ? 7 : 0);
        Pawnplace[6][9] = 2 + ((direction == 1) ? 7 : 0);
        //士
        Pawnplace[3][9] = 1 + ((direction == 1) ? 7 : 0);
        Pawnplace[5][9] = 1 + ((direction == 1) ? 7 : 0);
        //将
        Pawnplace[4][9] = 0 + ((direction == 1) ? 7 : 0);
        //炮
        Pawnplace[1][7] = 5 + ((direction == 1) ? 7 : 0);
        Pawnplace[7][7] = 5 + ((direction == 1) ? 7 : 0);
        //兵
        Pawnplace[0][6] = 6 + ((direction == 1) ? 7 : 0);
        Pawnplace[2][6] = 6 + ((direction == 1) ? 7 : 0);
        Pawnplace[4][6] = 6 + ((direction == 1) ? 7 : 0);
        Pawnplace[6][6] = 6 + ((direction == 1) ? 7 : 0);
        Pawnplace[8][6] = 6 + ((direction == 1) ? 7 : 0);

        return;

    }

    public void Load_Pawn_with_place(int direction)
    {
        pawn_pane = new Pane();
        pawn_pane.setOnMouseClicked(new PawnMoving_Handler(this, direction, this.clock, this.head_inst));
        pane.getChildren().add(pawn_pane);

        //direction为1，红正向，反之黑正向

        for(int i = 0 ; i < 9 ; i++)
        {
            for(int j = 0 ; j < 10 ; j++)
            {
                int num = Pawnplace[i][j];
                if( num != -1)
                {
                    Pane temp_pane = new Pane();

                    if(num <= 6)
                    {
                        if(direction == 1)
                        {
                            temp_pane = pawn_inst.Get_Fixed_pawn(num, 0);
                        }
                        else
                        {
                            temp_pane = pawn_inst.Get_Fixed_pawn(num, 1);
                        }
                    }
                    else
                    {
                        if(direction == 1)
                        {
                            temp_pane = pawn_inst.Get_Fixed_pawn(num, 1);
                        }
                        else
                        {
                            temp_pane = pawn_inst.Get_Fixed_pawn(num, 0);
                        }
                    }
                    temp_pane.setLayoutX((90+i*71.875-35.9375));
                    temp_pane.setLayoutY(750-40-(j*71.25+35.625) - ((j>4) ? 13.75 : 0));
                    pawn_pane.getChildren().add(temp_pane);
                }

            }
        }

    }
    public void setPawn_pane(Pane decorate)
    {
        this.pawn_pane.getChildren().add(decorate);
    }
    public Pane getPane() {
        return pane;
    }

    public void setPawnplace(int[][] pawnplace) {
        Pawnplace = pawnplace;
        pane.getChildren().remove(pawn_pane);
        Load_Pawn_with_place(direction);
    }

    public int getColor() {
        return color;
    }

    public void setColor() {
        this.color = (this.color == 0) ? 1 : 0;
    }

    public int[][] getPawnplace() {
        return Pawnplace;
    }
}
