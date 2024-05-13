package rules;

import java.util.Random;

public class temp_Rule
{
    private static int temp = 0;
    public static int[][] Check(int[][] Pawnplace, int x, int y, int x1, int y1)
    {
        //检查是否符合规则
        //Pawnplace为棋盘上的棋子分布，x,y为原位置，x1,y1为目标位置
        //返回值为棋子分布
//        int yemp = Pawnplace[x][y];
//        Random random = new Random();
//        Pawnplace[x][y] = -1;
//        Pawnplace[x1][y1] = yemp;
//        for(int i = 0 ; i < 9 ;i++)
//        {
//            for(int j = 0 ; j < 10 ; j++)
//            {
//                Pawnplace[i][j] = random.nextInt(13) - 1;
//
//            }
//        }
        ChessBoard  temp = new ChessBoard();
        boolean tempTruth = temp.Move_Check(Pawnplace, x, y, x1, y1);
        if(tempTruth)
        {
            int yemp = Pawnplace[x][y];
            Pawnplace[x][y] = -1;
            Pawnplace[x1][y1] = yemp;
        }
        System.out.println(tempTruth);
        return Pawnplace;
    }
}
