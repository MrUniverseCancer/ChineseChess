package rules;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//enum来表示棋子的颜色和种类
//棋子初始化
enum ChessPieceColor {
    RED, BLACK
}
enum ChessPieceType {
    ROOK,   //車
    HORSE, //馬
    CANNON, //炮
    PAWN,   //卒
    ADVISOR,//士
    ELEPHANT, //象
    KING,   //将
}
//棋子信息
class ChessPiece {
    final ChessPieceType type;
    final ChessPieceColor color;

    public ChessPiece(ChessPieceType type, ChessPieceColor color) {
        this.type = type;
        this.color = color;
    }
    
    public ChessPieceType getType() {
        return type;
    }
    
    public ChessPieceColor getColor() {
        return color;
    }
}

public class ChessBoard {
    private ChessPiece[][] board;
    private int direction;
    private String current_move_record = "";
    //构造函数，初始化对战棋盘  
    public ChessBoard()
    {
        board = new ChessPiece[10][9];  //每边五行，九列
        // 初始化棋盘
        /*              黑
        row0  車 馬 象 士 将 士 象 馬 車
        row1
        row2     炮               炮
        row3  卒    卒    卒    卒    卒
        row4
        --------------------------------
                楚河        汉界
        --------------------------------
        row5
        row6  卒    卒    卒    卒    卒
        row7     炮               炮
        row8
        row9  車 馬 象 士 将 士 象 馬 車
                        红              */

        board[0][0] = new ChessPiece(ChessPieceType.ROOK, ChessPieceColor.BLACK);
        board[0][1] = new ChessPiece(ChessPieceType.HORSE, ChessPieceColor.BLACK);
        board[0][2] = new ChessPiece(ChessPieceType.ELEPHANT, ChessPieceColor.BLACK);
        board[0][3] = new ChessPiece(ChessPieceType.ADVISOR, ChessPieceColor.BLACK);
        board[0][4] = new ChessPiece(ChessPieceType.KING, ChessPieceColor.BLACK);
        board[0][5] = new ChessPiece(ChessPieceType.ADVISOR, ChessPieceColor.BLACK);
        board[0][6] = new ChessPiece(ChessPieceType.ELEPHANT, ChessPieceColor.BLACK);
        board[0][7] = new ChessPiece(ChessPieceType.HORSE, ChessPieceColor.BLACK);
        board[0][8] = new ChessPiece(ChessPieceType.ROOK, ChessPieceColor.BLACK);

        board[2][1] = new ChessPiece(ChessPieceType.CANNON, ChessPieceColor.BLACK);
        board[2][7] = new ChessPiece(ChessPieceType.CANNON, ChessPieceColor.BLACK);

        for (int i = 0; i < 5; i++) {
            board[3][2*i] = new ChessPiece(ChessPieceType.PAWN, ChessPieceColor.BLACK);
        }

        // 初始化红方棋子
        board[9][0] = new ChessPiece(ChessPieceType.ROOK, ChessPieceColor.RED);
        board[9][1] = new ChessPiece(ChessPieceType.HORSE, ChessPieceColor.RED);
        board[9][2] = new ChessPiece(ChessPieceType.ELEPHANT, ChessPieceColor.RED);
        board[9][3] = new ChessPiece(ChessPieceType.ADVISOR, ChessPieceColor.RED);
        board[9][4] = new ChessPiece(ChessPieceType.KING, ChessPieceColor.RED);
        board[9][5] = new ChessPiece(ChessPieceType.ADVISOR, ChessPieceColor.RED);
        board[9][6] = new ChessPiece(ChessPieceType.ELEPHANT, ChessPieceColor.RED);
        board[9][7] = new ChessPiece(ChessPieceType.HORSE, ChessPieceColor.RED);
        board[9][8] = new ChessPiece(ChessPieceType.ROOK, ChessPieceColor.RED);

        board[7][1] = new ChessPiece(ChessPieceType.CANNON, ChessPieceColor.RED);
        board[7][7] = new ChessPiece(ChessPieceType.CANNON, ChessPieceColor.RED);

        for (int i = 0; i < 5; i++) {
            board[6][2*i] = new ChessPiece(ChessPieceType.PAWN, ChessPieceColor.RED);
        }
    }

    public boolean Move_Check(int[][] chessboard, int chess_row, int chess_col, int target_row, int target_col, int direction)
    {
        board = new ChessPiece[10][9];
        for(int i = 0 ; i < 9 ; i++)
        {
            for(int j = 0 ; j < 10 ; j++)
            {
                if(chessboard[i][j] == -1)
                {
//                    board[i][j] = null;
                    board[9-j][i] = null;
                }
                else
                {
                    ChessPieceColor color = (chessboard[i][j] < 7) ? ChessPieceColor.RED : ChessPieceColor.BLACK;
                    ChessPieceType type = ChessPieceType.KING;
                    switch (chessboard[i][j] % 7) {
                        case 0: type = ChessPieceType.KING; break;
                        case 1: type = ChessPieceType.ADVISOR; break;
                        case 2: type = ChessPieceType.ELEPHANT; break;
                        case 3: type = ChessPieceType.HORSE; break;
                        case 4: type = ChessPieceType.ROOK; break;
                        case 5: type = ChessPieceType.CANNON; break;
                        case 6: type = ChessPieceType.PAWN; break;
                        default:break;
                    }
                    board[9-j][i] = new ChessPiece(type, color);
                }
            }
        }
        record(board,9 - chess_col, chess_row, 9 - target_col, target_row);
        return Move_Check(board,9 - chess_col, chess_row, 9 - target_col, target_row, direction);
    }

    public boolean Move_Check(ChessPiece[][] chessboard, int chess_row, int chess_col, int target_row, int target_col, int direction)
    {
        this.direction = direction;
        if(chessboard[chess_row][chess_col] == null)
        {
            //没有棋子，加上对应事件的触发
            return false;
        }

        ChessPiece chess = chessboard[chess_row][chess_col];

        // 检查目标位置是否有己方棋子
        if (chessboard[target_row][target_col] != null && chessboard[target_row][target_col].getColor() == chess.getColor()) {
            //触发有己方棋子的事件
            return false;
        }
        else
        {
            switch(chess.getType())
            {
                case ROOK:
                    if (chess_row == target_row) {
                        // 横向移动
                        int startCol = Math.min(chess_col, target_col) + 1; // 获取起始列
                        int endCol = Math.max(chess_col, target_col); // 获取结束列
                        for (int col = startCol; col < endCol; col++) {
                            // 检查当前列与目标列之间是否存在其他棋子
                            if (chessboard[chess_row][col] != null) {
                                // 路径中间存在棋子，移动不合法
                                return false;
                            }
                        }
                        // 没有其他棋子阻挡，移动合法
                        return true;
                    } 
                    else if (chess_col == target_col) 
                    {
                        // 纵向移动
                        int startRow = Math.min(chess_row, target_row) + 1; // 获取起始行
                        int endRow = Math.max(chess_row, target_row); // 获取结束行
                        for (int row = startRow; row < endRow; row++) {
                            // 检查当前行与目标行之间是否存在其他棋子
                            if (chessboard[row][chess_col] != null) {
                                // 路径中间存在棋子，移动不合法
                                return false;
                            }
                        }
                        // 没有其他棋子阻挡，移动合法
                        return true;
                    } 
                    else 
                    {
                        // 车只能沿着横向或者纵向移动
                        return false;
                    }

                case HORSE:
                    // 马可以横移2格，竖移1格；或者竖移2格，横移1格
                    int rowDistance = Math.abs(target_row - chess_row);
                    int colDistance = Math.abs(target_col - chess_col);
                    if(!((rowDistance == 1 && colDistance == 2) || (rowDistance == 2 && colDistance == 1))) 
                    {
                        return false;
                    }
                    // 目标位置和当前位置符合马的移动规则
                    // 检查马脚是否有棋子阻挡
                    if (rowDistance == 1) {
                        // 竖移1格，横移2格的情况
                        int colOffset = (target_col > chess_col) ? 1 : -1;
                        if (chessboard[chess_row][chess_col + colOffset] == null) {
                            // 马脚上，没有棋子
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    } 
                    else 
                    {
                        // 横移1格，竖移2格的情况
                        int rowOffset = (target_row > chess_row) ? 1 : -1;
                        if (chessboard[chess_row + rowOffset][chess_col] == null) {
                            // 马脚上没有棋子
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }

                case CANNON:
                    if (chess_row == target_row) {
                        // 横向移动
                        int startCol = Math.min(chess_col, target_col) + 1 ; // 获取起始列
                        int endCol = Math.max(chess_col, target_col); // 获取结束列
                        int pieceCount = 0; // 记录目标位置之间的棋子数量
                        for (int col = startCol; col < endCol; col++) {
                            // 检查当前列与目标列之间是否存在其他棋子
                            if (chessboard[chess_row][col] != null) {
                                pieceCount++;
                            }
                        }
                        if (chessboard[target_row][target_col] == null) {
                            // 目标位置为空，判断目标位置之间是否有一个且只有一个棋子
                            return pieceCount == 0;
                        }
                        else
                        {
                            // 目标位置有敌方棋子，判断目标位置之间是否有两个及以上的棋子
                            return pieceCount == 1;
                        }
                    } 
                    else if (chess_col == target_col) {
                        // 纵向移动
                        int startRow = Math.min(chess_row, target_row) + 1; // 获取起始行
                        int endRow = Math.max(chess_row, target_row); // 获取结束行
                        int pieceCount = 0; // 记录目标位置之间的棋子数量
                        for (int row = startRow; row < endRow; row++) {
                            // 检查当前行与目标行之间是否存在其他棋子
                            if (chessboard[row][chess_col] != null) {
                                pieceCount++;
                            }
                        }
                        if (chessboard[target_row][target_col] == null) {
                            // 目标位置为空，判断目标位置之间是否有一个且只有一个棋子
                            return pieceCount == 0;
                        } 
                        else {
                            // 目标位置有敌方棋子，判断目标位置之间是否有两个及以上的棋子
                            return pieceCount == 1;
                        }
                    } 
                    else {
                        // 炮只能沿着横向或者纵向移动
                        return false;
                    }

                case ADVISOR:
                    // 士只能沿着九宫格的斜线移动
                    if (Math.abs(chess_row - target_row) != 1 || Math.abs(chess_col - target_col) != 1) {
                        // 士只能移动一格斜线
                        return false;
                    } else {
                        if (chess.getColor() == ChessPieceColor.BLACK) {
                            // 如果是黑色士，目标位置不能越过楚河汉界
                            if (target_row < 0 || target_row > 2 || target_col < 3 || target_col > 5) {
                                // 目标位置越界，移动不合法
                                return false;
                            }
                        } else if (chess.getColor() == ChessPieceColor.RED) {
                            // 如果是红色士，目标位置不能越过楚河汉界
                            if (target_row < 7 || target_row > 9 || target_col < 3 || target_col > 5) {
                                // 目标位置越界，移动不合法
                                return false;
                            }
                        }
                        // 移动合法
                        return true;
                    }

                case KING:
                    //飞将
                    if(chess_col == target_col && Math.abs(chess_row - target_row) > 1)
                    {
                        if(chess.getColor() != chessboard[target_row][target_col].getColor() && chessboard[target_row][target_col].getType() == ChessPieceType.KING)
                        {
                            int startRow = Math.min(chess_row, target_row) + 1; // 获取起始行
                            int endRow = Math.max(chess_row, target_row); // 获取结束行
                            int pieceCount = 0; // 记录目标位置之间的棋子数量
                            for (int row = startRow; row < endRow; row++) {
                                // 检查当前行与目标行之间是否存在其他棋子
                                if (chessboard[row][chess_col] != null) {
                                    pieceCount++;
                                }
                            }
                            return pieceCount == 0; //将与将之间不能有其它棋子阻隔
                        }
                        else
                        {
                            return false;
                        }
                    }
                    // 将只能在九宫格内移动一步
                    if ((Math.abs(chess_row - target_row) == 1 && chess_col == target_col) || 
                        (Math.abs(chess_col - target_col) == 1 && chess_row == target_row)) {
                        // 判断是否越过楚河汉界
                        if (chess.getColor() == ChessPieceColor.BLACK) 
                        {
                            // 如果是黑将，目标位置不能越过楚河汉界
                            if (target_row < 0 || target_row > 2 || target_col < 3 || target_col > 5) {
                                // 目标位置越界，移动不合法
                                return false;
                            }
                        } 
                        else if (chess.getColor() == ChessPieceColor.RED) 
                        {
                            // 如果是红将，目标位置不能越过楚河汉界
                            if (target_row < 7 || target_row > 9 || target_col < 3 || target_col > 5) {
                                // 目标位置越界，移动不合法
                                return false;
                            }
                        }
                        // 移动合法
                        return true;
                    }
                    else {
                        // 将只能在九宫格内移动一步
                        return false;
                    }
                        
                case ELEPHANT:
                    //判断象的颜色，以限制其活动范围
                    if(chess.getColor() == ChessPieceColor.RED){
                        if(target_row >= 5)
                        {
                            // 象的移动是有规律的，只能是“走日”移动，即每次移动两个格子，一个方向走一步
                            if (Math.abs(chess_row - target_row) == 2 && Math.abs(chess_col - target_col) == 2)
                            {
                                int middleRow = (chess_row + target_row) / 2; // 获取象眼的行坐标
                                int middleCol = (chess_col + target_col) / 2; // 获取象眼的列坐标
                                // 检查象眼是否被堵住
                                if (chessboard[middleRow][middleCol] == null) {
                                    // 象眼没有被堵住，移动合法
                                    return true;
                                }
                                else{
                                    return false;
                                }
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else if(chess.getColor() == ChessPieceColor.BLACK)
                    {
                        if(target_row <= 4)
                        {
                            // 象的移动是有规律的，只能是“走日”移动，即每次移动两个格子，一个方向走一步
                            if (Math.abs(chess_row - target_row) == 2 && Math.abs(chess_col - target_col) == 2) {
                                int middleRow = (chess_row + target_row) / 2; // 获取象眼的行坐标
                                int middleCol = (chess_col + target_col) / 2; // 获取象眼的列坐标
                                // 检查象眼是否被堵住
                                if (chessboard[middleRow][middleCol] == null) {
                                    // 象眼没有被堵住，移动合法
                                    return true;
                                }
                                else {
                                    return false;
                                }
                            }
                            else {
                                return false;
                            }
                        }
                        else {
                            return false;
                        }
                    }
                    else
                    {
                        // 移动不合法
                        return false;
                    }


                case PAWN:
                    // 判断卒的颜色，以确定其可移动的方向
                    if (chess.getColor() == ChessPieceColor.RED) {
                        // 红卒
                        if (chess_row >= 5) {
                            // 未过河
                            if (target_row == chess_row - 1 && target_col == chess_col) {
                                // 向前一步移动
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else {
                            // 已过河
                            if (target_row == chess_row - 1 && target_col == chess_col) {
                                // 向前一步移动
                                return true;
                            }
                            else if (target_row == chess_row && Math.abs(target_col - chess_col) == 1) {
                                // 向左右任意一侧移动一步
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                        }
                    } 
                    else 
                    {
                        // 黑卒
                        if (chess_row <= 4) {
                            // 未过河
                            if (target_row == chess_row + 1 && target_col == chess_col) {
                                // 向前一步移动
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else {
                            // 已过河
                            if (target_row == chess_row + 1 && target_col == chess_col) {
                                // 向前一步移动
                                return true;
                            }
                            else if (target_row == chess_row && Math.abs(target_col - chess_col) == 1) {
                                // 向左右任意一侧移动一步
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                        }
                    }
                     
                default : return false;            
            }
        }
    }

    public boolean isCheckmate(int [][] chessboard, int who)
    {
        board = new ChessPiece[10][9];
        for(int i = 0 ; i < 9 ; i++)
        {
            for(int j = 0 ; j < 10 ; j++)
            {
                if(chessboard[i][j] == -1)
                {
//                    board[i][j] = null;
                    board[9-j][i] = null;
                }
                else
                {
                    ChessPieceColor color = (chessboard[i][j] < 7) ? ChessPieceColor.RED : ChessPieceColor.BLACK;
                    ChessPieceType type = ChessPieceType.KING;
                    switch (chessboard[i][j] % 7) {
                        case 0: type = ChessPieceType.KING; break;
                        case 1: type = ChessPieceType.ADVISOR; break;
                        case 2: type = ChessPieceType.ELEPHANT; break;
                        case 3: type = ChessPieceType.HORSE; break;
                        case 4: type = ChessPieceType.ROOK; break;
                        case 5: type = ChessPieceType.CANNON; break;
                        case 6: type = ChessPieceType.PAWN; break;
                        default:break;
                    }
                    board[9-j][i] = new ChessPiece(type, color);
                }
            }
        }
        ChessPieceColor color = (who == 1) ? ChessPieceColor.RED : ChessPieceColor.BLACK;
        return isCheckmate(board, color);
    }

    public boolean isCheckmate(ChessPiece[][] chessboard, ChessPieceColor color)
    {
        int KING_row = -1;
        int KING_col = -1;
        if(color == ChessPieceColor.BLACK)
        {
            //判断黑色将军是否被将军
            //寻找黑色方将军
            for(int i = 0; i < 3 && KING_row == -1; i++)
            {
                for(int j = 3; j < 6 && KING_col == -1; j++)
                {
                    if(chessboard[i][j] != null && chessboard[i][j].getType() == ChessPieceType.KING)
                    {
                        KING_row = i;
                        KING_col = j;
                    }
                }
            }
        }
        else if(color == ChessPieceColor.RED)
        {
            //判断红色将军是否被将军
            //寻找红色方将军

            for(int i = 7; i < 10 && KING_row == -1; i++)
            {
                for(int j = 3; j < 6 && KING_col == -1; j++)
                {
                    if(chessboard[i][j] != null && chessboard[i][j].getType() == ChessPieceType.KING)
                    {
                        KING_row = i;
                        KING_col = j;
                    }
                }
            }

        }
        //找不到将军
        if(KING_row == -1 && KING_col == -1)
        {
            return false;
        }
        //寻找将军比较简单的遍历实现
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                if (chessboard[i][j] != null && chessboard[i][j].getColor() != color) {
                    // 移动当前棋子，判断是否能够将对方的将/帅
                    if (Move_Check(chessboard, i, j, KING_row, KING_col, direction)) {
                        // 存在可以将对方的将/帅，不算被将军
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private String getPieceName(ChessPiece piece) {
        return switch (piece.getType()) {
            case ROOK -> "车";
            case HORSE -> "马";
            case CANNON -> "炮";
            case PAWN -> piece.getColor() == ChessPieceColor.RED ? "兵" : "卒";
            case ADVISOR -> piece.getColor() == ChessPieceColor.RED ? "仕" : "士";
            case ELEPHANT -> piece.getColor() == ChessPieceColor.RED ? "相" : "象";
            case KING -> piece.getColor() == ChessPieceColor.RED ? "帅" : "将";
            default -> "";
        };
    }

    private void record(ChessPiece[][] chessboard, int chess_row, int chess_col, int target_row, int target_col)
    {
        current_move_record = "";       //清空移动记录字符串
        if (!Move_Check(chessboard, chess_row, chess_col, target_row, target_col, direction)) {
            return;
        } else {
            // 获取移动的棋子名称
            String pieceName = getPieceName(chessboard[chess_row][chess_col]);

            // 按指定格式生成移动记录
            current_move_record += String.format("%s: %d行%d列 -> %d行%d列", pieceName, chess_row, chess_col, target_row, target_col);
        }
    }

    public String getCurrent_move_record() {
        return current_move_record;
    }
}
