public class SudokuSolver {
    private static final int grid_Size=9;
    public static void main(String[]args) {
        int[][] board = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}


        };
        if(solver(board)){
            System.out.println("Board was Successfully Solved");
        }
        else{
            System.out.println("Unsolvable Board!!!");
        }
        printBoard(board);
    }
    private static void printBoard(int [][]board){
        for(int i=0;i<grid_Size;i++){
            if(i%3==0 && i!=0){
                System.out.println("---- ----- ----");
            }
            for(int j=0;j<grid_Size;j++){
                if(j%3==0 && j!=0){
                    System.out.print(" | ");
                }
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean rowCheck(int [][] board, int num, int row){
        for(int i=0;i<grid_Size;i++){
            if(board[row][i]==num){
                return true;
            }
        }
        return false;
    }
    public static boolean colCheck(int [][]board, int num, int col){
        for(int i=0;i<grid_Size;i++){
            if(board[i][col]==num){
                return true;
            }
        }
        return false;
    }
    public static boolean boxCheck(int [][]board, int num, int row, int col){
        int localRow=row-row%3;
        int localCol=col-col%3;
        for(int i=localRow;i<localRow+3;i++){
            for(int j=localCol;j<localCol+3;j++){
                if(board[i][j]==num){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isValidPosition(int board[][], int num, int row, int col){
        return !rowCheck(board,num,row) && !colCheck(board,num,col) && !boxCheck(board,num,row,col);
    }
    public static boolean solver(int board[][]){
        for(int i=0;i<grid_Size;i++){
            for(int j=0;j<grid_Size;j++){
                if(board[i][j]==0){
                    for(int tryNum=1;tryNum<=9;tryNum++){
                        if(isValidPosition(board, tryNum,i,j)){
                            board[i][j]=tryNum;
                            if(solver(board)){
                                return true;
                            }
                            else board[i][j]=0;
                        }

                    }
                    return false;
                }
            }
        }
        return true;
    }
}
