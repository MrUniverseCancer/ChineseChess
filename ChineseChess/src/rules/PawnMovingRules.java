package rules;

import javafx.util.Pair;

import java.util.Random;

public class PawnMovingRules
{
//    private static int temp = 0;
    private static ChessBoard temp = new ChessBoard();
    public static Pair<int[][], Boolean> Check(int[][] Pawnplace, int x, int y, int x1, int y1, int direction)
    {
        //检查是否符合规则
        //Pawnplace为棋盘上的棋子分布，x,y为原位置，x1,y1为目标位置
        //返回值为棋子分布
        Boolean result = false;
        boolean tempTruth;
        if( direction == 0)
        {
            tempTruth = temp.Move_Check(reverse(Pawnplace), 8-x, 9-y, 8-x1, 9-y1);
        }
        else
        {
            tempTruth = temp.Move_Check((Pawnplace), x, y, x1, y1);
        }
        if(tempTruth)
        {
            int itemp = Pawnplace[x][y];
            Pawnplace[x][y] = -1;
            Pawnplace[x1][y1] = itemp;
            result = true;
        }
        System.out.println(tempTruth);
        boolean red = temp.isCheckmate(Pawnplace, 1);
        boolean black = temp.isCheckmate(Pawnplace, 0);
        System.out.println("red: " + red + " black: " + black);

        return new Pair<>(Pawnplace, result);
    }

    public static int[][] reverse(int[][] Pawnplace)
    {
        int[][] temp = new int[9][10];
        for(int i = 0 ; i < 9 ; i++)
        {
            for(int j = 0 ; j < 10 ; j++)
            {
                temp[i][j] = Pawnplace[8-i][9-j];
            }
        }
        return temp;
    }
}
